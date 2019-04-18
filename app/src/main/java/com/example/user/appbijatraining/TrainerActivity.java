package com.example.user.appbijatraining;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.example.user.appbijatraining.StaffContract.FeedEntry.TABLE_NAME;

public class TrainerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ListView listView1, listView2;
    NavigationView navigationView;
    TextView textView1, textView2;
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.home_nav_bt:
                Intent intent = new Intent(TrainerActivity.this, TrainerActivity.class);
                startActivity(intent);
                navigationView.setCheckedItem(R.id.home_nav_bt);
                finish();
                Log.v("home", "home is clicked");
                break;
            case R.id.notification_nav_bt:
                Intent intent1 = new Intent(getApplicationContext(), NotificationsActivity.class);
                startActivity(intent1);

                break;
          /*  case R.id.send_nav_bt:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SendFragment())
                        .addToBackStack(null).commit();
                navigationView.setCheckedItem(R.id.send_nav_bt);*/

            case R.id.profile_nav_bt:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment())
                        .addToBackStack(null).commit();
                navigationView.setCheckedItem(R.id.profile_nav_bt);
                break;
            case R.id.appt_nav_bt:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AppointmentsFragment())
                        .addToBackStack(null).commit();
                navigationView.setCheckedItem(R.id.appt_nav_bt);
                break;
            case R.id.prog_nav_btn:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProgrammeFragment())
                        .addToBackStack(null).commit();
                navigationView.setCheckedItem(R.id.prog_nav_btn);
                break;
            /*case R.id.history_nav_btn:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HistoryFragment())
                        .addToBackStack(null).commit();
                navigationView.setCheckedItem(R.id.history_nav_btn);
                break;*/
            case R.id.flwup_nav_btn:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FollowUpsFragment())
                        .addToBackStack(null).commit();
                navigationView.setCheckedItem(R.id.flwup_nav_btn);
                break;
           /* case R.id.settings_nav_btn:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment())
                        .addToBackStack(null).commit();
                navigationView.setCheckedItem(R.id.settings_nav_btn);*/
            case R.id.calendar_nav_btn:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CalendarFragment())
                        .addToBackStack(null).commit();
                navigationView.setCheckedItem(R.id.calendar_nav_btn);
                //Intent intent = new Intent(TrainerActivity.this, CalendarActivity.class);
                // startActivity(intent);
                break;
            case R.id.signout_nav_bt:
                Toast.makeText(this, "Signed out", Toast.LENGTH_SHORT).show();
                 final String SQL_DELETE_ENTRIES =
                        "DROP TABLE IF EXISTS " + TABLE_NAME;
                StaffDetailDbHelper dbHelper=new StaffDetailDbHelper(getApplicationContext());
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                db.execSQL("delete from "+ TABLE_NAME);
                finish();
                Intent intent3 = new Intent(TrainerActivity.this, Login.class);
                startActivity(intent3);
                navigationView.setCheckedItem(R.id.signout_nav_bt);
                break;
           /* case R.id.passChange_nav_bt:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ChangePassFragment())
                        .addToBackStack(null).commit();
                navigationView.setCheckedItem(R.id.passChange_nav_bt);*/

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }


    @Override
    public void onBackPressed() {
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        for (int i = 0; i < navigationView.getMenu().size(); i++) {
            navigationView.getMenu().getItem(i).setChecked(false);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_trainer);
        initToolBar();
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.home_nav_bt);
        View headerLayout = navigationView.getHeaderView(0);
        TextView email_nav = headerLayout.findViewById(R.id.nav_email);
        TextView name_nav = headerLayout.findViewById(R.id.name_nav);
        Detail_Extracter detail_extracter = new Detail_Extracter(getApplicationContext());
        email_nav.setText(detail_extracter.getEmail());
        name_nav.setText(detail_extracter.getName());

        /*List<String> list = new ArrayList<>();
        list.add("dummy1");
        list.add("dummy2");
        list.add("dummy3");
        list.add("dummy4");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, list);
        listView1.setAdapter(arrayAdapter);
        listView2.setAdapter(arrayAdapter);*/
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.Nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TrainerFragment()).commit();
            navigationView.setCheckedItem(R.id.home_nav_bt);
        }
       // textView1.setText("Programmes");
        //textView2.setText("Appointment");

    }

    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Bija Training");

        setSupportActionBar(toolbar);

    }


}