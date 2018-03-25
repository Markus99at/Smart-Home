package htl.at.smart_home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static  Toolbar toolbar;
public static String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Systemstatus");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //get IP Address from the Settings
        loadIP();

        Fragment fragment = new Status();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.content_main, fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment=null;
if(connection) {

    if (id == R.id.nav_status) {
        fragment = new Status();
        getSupportActionBar().setTitle("Systemstatus");
    } else if (id == R.id.nav_wohnzimmer) {
        fragment = new Wohnzimmer();
        getSupportActionBar().setTitle("Wohnzimmer");
    } else if (id == R.id.nav_kuche) {
        fragment = new Kuche();
        getSupportActionBar().setTitle("Küche");
    } else if (id == R.id.nav_schlafzimmer) {
        fragment = new Schlafzimmer();
        getSupportActionBar().setTitle("Schlafzimmer");
    } else if (id == R.id.nav_arbeitszimmer) {
        fragment = new Arbeitszimmer();
        getSupportActionBar().setTitle("Arbeitszimmer");
    } else if (id == R.id.nav_garage) {
        fragment = new Garage();
        getSupportActionBar().setTitle("Garage");
    } else if (id == R.id.nav_badezimmer) {
        fragment = new Badezimmer();
        getSupportActionBar().setTitle("Badezimmer");
    } else if (id == R.id.nav_debugging) {
        fragment = new Debugging();
        getSupportActionBar().setTitle("Debugging");
    }
}else{
    showDialog();
}
        if(fragment!=null){
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.content_main, fragment);
            transaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void loadIP(){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ip = settings.getString("ipAddress", null);
    }

    public void wohnzimmerMainLightOn(View view){
Wohnzimmer.mainlightOn(view);
    }

    public void wohnzimmerMainLightOff(View view){
        Wohnzimmer.mainlightOff(view);
    }

    public void wohnzimmerWorkingLightOn(View view){
        Wohnzimmer.workinglightOn(view);
    }

    public void wohnzimmerWorkingLightOff(View view){Wohnzimmer.workinglightOff(view);
    }


    public void closeTimeoutDialog(View view){
        Fragment fragment=null;
        fragment = new Status();
        if(fragment!=null){
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.content_main, fragment);
            transaction.commit();
        }
        dialogFragment.dismiss();

    }

public TimeoutDialog dialogFragment;

    public static boolean connection = false;

    public void showDialog(){

    android.app.FragmentManager fm = getFragmentManager();
    dialogFragment = new TimeoutDialog ();
    dialogFragment.show(fm, "Sample Fragment");
}
}