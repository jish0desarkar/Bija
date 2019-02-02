package com.example.user.appbijatraining;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class TrainerActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar_trainer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.programme_menu:
                Intent intent1 = new Intent(TrainerActivity.this, ProgrammeActivity.class);
                startActivity(intent1);
                Toast.makeText(this, "Selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.appt_menu:
                Intent intent2 = new Intent(TrainerActivity.this, AppointmentsActivity.class);
                startActivity(intent2);
                return true;
            case R.id.follow_menu:
                Intent intent3 = new Intent(TrainerActivity.this, FollowupActivity.class);
                startActivity(intent3);
                return true;
            case R.id.calendar_menu:
                Intent intent4 = new Intent(TrainerActivity.this, CalendarActivity.class);
                startActivity(intent4);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer);
        initToolBar();
    }

    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Bija Training");

        setSupportActionBar(toolbar);


    }
}