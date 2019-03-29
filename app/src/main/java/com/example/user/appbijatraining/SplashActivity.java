package com.example.user.appbijatraining;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        System.out.println("Testing");
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
            @Override
            public void run() {
                finish();
                StaffDetailDbHelper dbHelper=new StaffDetailDbHelper(getApplicationContext());
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                Cursor cursor=db.rawQuery("Select * from staff",null);
                if (cursor.getCount()==0)
                startActivity(new Intent(SplashActivity.this,TrainerActivity.class));
                else
                    startActivity(new Intent(SplashActivity.this,TrainerActivity.class));
            }
        }, SPLASH_TIME_OUT);
            }
    }
