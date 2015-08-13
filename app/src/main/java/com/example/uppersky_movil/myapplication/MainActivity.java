package com.example.uppersky_movil.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.uppersky_movil.myapplication.utils.GPSTracker;
import com.example.uppersky_movil.myapplication.utils.PreferenceUtils;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends BaseActivity {

    private GPSTracker gps;
    private GoogleMap mGoogleMap;
    private static final String TAG = "Main";
    private PreferenceUtils mPreferences;
    @InjectView(R.id.tool_bar)  Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        init();

    }

    private void init() {

        final LatLng location;

        // init Preferences

        mPreferences = PreferenceUtils.getInstance(this);

        // Init GPS Location

        initGPSLocation();

        // Inflate Activity View

        RelativeLayout rLayout = (RelativeLayout)findViewById(R.id.activity_frame);

        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View activityView = layoutInflater.inflate(R.layout.activity_main, null,false);

        rLayout.addView(activityView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        // Butterknife
        ButterKnife.inject(this);

        // Init Toolbar

        setToolbar(mToolbar, "Main", 3);

        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);


        final View actionB = findViewById(R.id.action_b);

        FloatingActionButton actionC = new FloatingActionButton(getBaseContext());
        actionC.setTitle("Hide/Show Action above");
        actionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionB.setVisibility(actionB.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            }
        });

        final FloatingActionsMenu menuMultipleActions = (FloatingActionsMenu) findViewById(R.id.multiple_actions);
        menuMultipleActions.addButton(actionC);


        // Google Maps


        if (mGoogleMap == null) {

            mGoogleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        }

        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        location = new LatLng(mPreferences.getLatitude(), mPreferences.getLongitude());

        moveToLocation(location);

    }

    private void initGPSLocation() {

        gps = new GPSTracker(MainActivity.this);

        // check if GPS enabled

        if(gps.canGetLocation()) {

            mPreferences.setLongitude((float) gps.getLongitude());

            mPreferences.setLatitude((float) gps.getLatitude());

        } else {

            // Ask user to enable GPS/network in settings

            gps.showSettingsAlert();

        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                openDrawer();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setUpMapIfNeeded() {

        if (mGoogleMap == null) {

            mGoogleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        }
    }

    private void moveToLocation(LatLng currentLocation)
    {

        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));

        mGoogleMap.animateCamera(CameraUpdateFactory.zoomIn());

        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

    }
}