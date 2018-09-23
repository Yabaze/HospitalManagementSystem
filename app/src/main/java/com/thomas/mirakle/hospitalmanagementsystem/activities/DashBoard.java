package com.thomas.mirakle.hospitalmanagementsystem.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.thomas.mirakle.hospitalmanagementsystem.DBHandler;
import com.thomas.mirakle.hospitalmanagementsystem.R;
import com.thomas.mirakle.hospitalmanagementsystem.SelectUserTypeActivity;
import com.thomas.mirakle.hospitalmanagementsystem.activities.fragements.DoctorDetailsFragment;
import com.thomas.mirakle.hospitalmanagementsystem.activities.fragements.DoctorSpecializationListFragment;
import com.thomas.mirakle.hospitalmanagementsystem.activities.fragements.UserProfile;
import com.thomas.mirakle.hospitalmanagementsystem.sharedData.SharedPreferenceData;

public class DashBoard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DBHandler database;
    Toolbar toolbar;
    SharedPreferenceData data;
    private DrawerLayout dashBoardDrawer;
    Drawable actionBarLogo;
    NavigationView navigationView;
    String username;

    FragmentManager fragmentManager;
    UserProfile userProfile;
    DoctorSpecializationListFragment doctorSpecializationListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dash_board_drawer);

        init();
        doctorSpecilization();
    }
    private void init(){

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getResources().getString(R.string.dash_board));

        actionBarLogo=getResources().getDrawable(R.mipmap.ic_launcher_background);

        ActionBar actionbar = getSupportActionBar();

        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setTitle(getResources().getString(R.string.dash_board));
            actionBarLogo = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(((BitmapDrawable) actionBarLogo).getBitmap(), 150, 150, true));
            actionbar.setLogo(actionBarLogo);
        }

        dashBoardDrawer =  findViewById(R.id.dash_board_drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, dashBoardDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        dashBoardDrawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //actionbar.setHomeAsUpIndicator(R.mipmap.ic_launcher_background);

        database=new DBHandler(this);

        Bundle data=getIntent().getExtras();
        username=data.getString(getString(R.string.intent_extra_data));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.dash_board_drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch(id){
            case R.id.logout:
                logout();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.user_logout:
                logout();
                break;
            case R.id.user_home:
                doctorSpecilization();
                break;
            case R.id.user_profile:
                userProfile(username);
                break;
        }

        dashBoardDrawer= findViewById(R.id.dash_board_drawer);
        dashBoardDrawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public void logout(){
        data=new SharedPreferenceData(getApplicationContext());
        AlertDialog.Builder builder = new AlertDialog.Builder(DashBoard.this);
        builder.setTitle(getString(R.string.logout))
                .setMessage("Do you want to logout?")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        data.setPassword("");
                        data.setUsername("");
                        startActivity(new Intent(DashBoard.this, SelectUserTypeActivity.class));
                        finish();
                    }
                })
                .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setIcon(R.mipmap.ic_launcher_background);

        AlertDialog a = builder.create();
        a.show();
    }


    public void userProfile(String data){
        userProfile=new UserProfile(data);

        fragmentManager=getSupportFragmentManager();

        fragmentManager.beginTransaction().remove(doctorSpecializationListFragment);

        fragmentManager.beginTransaction().replace(R.id.fragment_container,userProfile).commit();

    }

    DoctorDetailsFragment doctorDetailsFragment;

    public void doctorSpecilization() {
        doctorSpecializationListFragment=new DoctorSpecializationListFragment();

        //doctorDetailsFragment=new DoctorDetailsFragment();

        fragmentManager=getSupportFragmentManager();



        fragmentManager.beginTransaction().remove(userProfile);

        //fragmentManager.beginTransaction().replace(R.id.fragment_container_two,doctorDetailsFragment).commit();
        fragmentManager.beginTransaction().replace(R.id.fragment_container,doctorSpecializationListFragment).commit();
    }
}
