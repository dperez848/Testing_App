package com.example.uppersky_movil.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.uppersky_movil.myapplication.utils.PreferenceUtils;


public class BaseActivity extends AppCompatActivity  {

    private static final String TAG = "Base";
    /**
     * Instancia del drawer
     */
    private DrawerLayout drawerLayout;

    /**
     * Titulo inicial del drawer
     */


    //Preferences
    protected PreferenceUtils mPreferences;

    protected NavigationView navigationView;

    String name;



    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.base_activity);

        init();






    }


    // Inits

    private void init() {

       // mToolbar = (Toolbar) findViewById(R.id.tool_bar);

        mPreferences = PreferenceUtils.getInstance(this);


        name = mPreferences.getName();


        // Init Navigation Drawer

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        if (navigationView != null) {

            setupDrawerContent(navigationView);
        }


    }

    public void openDrawer(){
        drawerLayout.openDrawer(GravityCompat.START);

    }

    private void setupDrawerContent(final NavigationView navigationView) {

        this.navigationView = navigationView;

        navigationView.setNavigationItemSelectedListener(

                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        selectItem(menuItem.getItemId(), menuItem);

                        drawerLayout.closeDrawers();

                        return true;
                    }
                }
        );

    }


    protected void setToolbar(Toolbar mToolbar, CharSequence mTitle, int type){

        if (mToolbar != null) {



            switch (type){

                case 0: {

                    toolbarBasicConfig(mTitle, getResources().getDrawable(R.drawable.ic_menu),mToolbar);

                    break;

                }

                case 1: {

                    toolbarBasicConfig(mTitle, getResources().getDrawable(R.drawable.ic_menu),mToolbar);

                    break;

                }

                case 3: {

                    toolbarBasicConfig(mTitle, getResources().getDrawable(R.drawable.ic_menu), mToolbar);


                    break;
                }
            }
        }
    }


    public void toolbarBasicConfig(CharSequence title, Drawable icon, Toolbar mToolbar){

        setSupportActionBar(mToolbar);

        getSupportActionBar().setElevation(0);

        getSupportActionBar().setHomeButtonEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeAsUpIndicator(icon);

        getSupportActionBar().setTitle(title);

    }
    private void selectItem(int id, MenuItem menuItem) {

        switch (id){
           case R.id.nav_home:
                callActivity(MainActivity.class);
                break;

            case R.id.nav_productos:
                callActivity(SecondActivity.class);

                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    // Call Activities

    protected void callActivity(Class c) {

        Intent intent = new Intent(this, c);

        startActivity(intent);

    }

}
