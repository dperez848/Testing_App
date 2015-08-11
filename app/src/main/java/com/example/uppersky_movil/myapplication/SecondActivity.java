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

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by UPPERSKY-MOVIL on 11/08/2015.
 */
public class SecondActivity extends BaseActivity{

    @InjectView(R.id.tool_bar)
    Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        init();

    }

    private void init() {

        // Inflate Activity View

        RelativeLayout rLayout = (RelativeLayout)findViewById(R.id.activity_frame);

        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View activityView = layoutInflater.inflate(R.layout.activity_second, null,false);

        rLayout.addView(activityView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));


        // Butterknife
        ButterKnife.inject(this);

        // Init Toolbar

        setToolbar(mToolbar, "Second", 3);

        navigationView.getMenu().findItem(R.id.nav_productos).setChecked(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


}
