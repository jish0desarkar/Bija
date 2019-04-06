package com.example.user.appbijatraining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

public class LongPressPopUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_press_pop_up);

        Button addToFollowUp = findViewById(R.id.flwup_add_btn);
        Button addToProgram = findViewById(R.id.prg_add_btn);
        Button addToAppt = findViewById(R.id.appt_add_btn);
        Button closeButton = findViewById(R.id.close_btn);


        Intent intent = getIntent();
        String date = intent.getStringExtra("Date");
        String remark =intent.getStringExtra("remark");
        String  trainerEmai=intent.getStringExtra("trainer_email");
        String  fromDate=intent.getStringExtra("from");
        String toDate =intent.getStringExtra("to");
        String companyPerson =intent.getStringExtra("person");
        String finalStatus =intent.getStringExtra("final_status");
        String  approval=intent.getStringExtra("approval");
        String status =intent.getStringExtra("status");
        String fees =intent.getStringExtra("fees");
        String due =intent.getStringExtra("due");
        String paidOn =intent.getStringExtra("paid_on");
        String  location=intent.getStringExtra("location");
        String paid =intent.getStringExtra("paid");

        addToFollowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LongPressPopUpActivity.this, PopUpRemark.class);
                intent.putExtra("tag", "Follow up");
                intent.putExtra("Date", date);
                intent.putExtra("remark", remark);
                intent.putExtra("trainer_email", trainerEmai);
                intent.putExtra("from", fromDate);
                intent.putExtra("to", toDate);
                intent.putExtra("person", companyPerson);
                intent.putExtra("final_status", finalStatus);
                intent.putExtra("approval", approval);
                intent.putExtra("status", status);
                intent.putExtra("fees", fees);
                intent.putExtra("due", due);
                intent.putExtra("paid_on", paidOn);
                intent.putExtra("location", location);
                intent.putExtra("paid", paid);
                startActivity(intent);
                finish();
            }
        });

        addToAppt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LongPressPopUpActivity.this, PopUpRemark.class);
                intent.putExtra("tag", "Appointment");
                startActivity(intent);
                intent.putExtra("Date", date);
                intent.putExtra("remark", remark);
                intent.putExtra("trainer_email", trainerEmai);
                intent.putExtra("from", fromDate);
                intent.putExtra("to", toDate);
                intent.putExtra("person", companyPerson);
                intent.putExtra("final_status", finalStatus);
                intent.putExtra("approval", approval);
                intent.putExtra("status", status);
                intent.putExtra("fees", fees);
                intent.putExtra("due", due);
                intent.putExtra("paid_on", paidOn);
                intent.putExtra("location", location);
                intent.putExtra("paid", paid);
                finish();
            }
        });

        addToProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LongPressPopUpActivity.this, PopUpRemark.class);
                intent.putExtra("tag", "Program");
                startActivity(intent);
                intent.putExtra("Date", date);
                intent.putExtra("remark", remark);
                intent.putExtra("trainer_email", trainerEmai);
                intent.putExtra("from", fromDate);
                intent.putExtra("to", toDate);
                intent.putExtra("person", companyPerson);
                intent.putExtra("final_status", finalStatus);
                intent.putExtra("approval", approval);
                intent.putExtra("status", status);
                intent.putExtra("fees", fees);
                intent.putExtra("due", due);
                intent.putExtra("paid_on", paidOn);
                intent.putExtra("location", location);
                intent.putExtra("paid", paid);
                finish();
            }
        });

            closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });














        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        getWindow().setLayout((int) (displayMetrics.widthPixels * .7), (int) (displayMetrics.heightPixels * .5));
    }
}
