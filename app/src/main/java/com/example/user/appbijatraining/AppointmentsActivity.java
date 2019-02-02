package com.example.user.appbijatraining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class AppointmentsActivity extends AppCompatActivity {

   Toolbar toolbar;


    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar_trainer, menu);
        return true;
    }
    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Appointments");

        setSupportActionBar(toolbar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);
        initToolBar();
        Toast.makeText(this, "This is the Appointment Activity", Toast.LENGTH_LONG).show();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.programme_menu:
                Intent intent1 = new Intent(AppointmentsActivity.this, ProgrammeActivity.class);
                startActivity(intent1);
                Toast.makeText(this, "Selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.appt_menu:
                Intent intent2 = new Intent(AppointmentsActivity.this, AppointmentsActivity.class);
                startActivity(intent2);
                return true;
            case R.id.follow_menu:
                Intent intent3 = new Intent(AppointmentsActivity.this, FollowupActivity.class);
                startActivity(intent3);
                return true;
            case R.id.calendar_menu:
                Intent intent4 = new Intent(AppointmentsActivity.this, CalendarActivity.class);
                startActivity(intent4);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
