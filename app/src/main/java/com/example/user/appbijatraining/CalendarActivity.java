package com.example.user.appbijatraining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.applandeo.materialcalendarview.CalendarView;

import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {

    CalendarView calendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

         calendarView = findViewById(R.id.calendar_view);
        calendarView.showCurrentMonthPage();
    }
}
