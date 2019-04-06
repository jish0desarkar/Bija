package com.example.user.appbijatraining;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PopUpActivity extends AppCompatActivity {

    Button closeButton;
    TextView popupText;
    String text;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);
        closeButton = findViewById(R.id.close_pop_button);
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

        TextView dateText = findViewById(R.id.date);
        TextView remarkText = findViewById(R.id.remark);
        TextView statusText = findViewById(R.id.status);
        TextView trainerEmailText = findViewById(R.id.trainer_email);
        TextView fromText = findViewById(R.id.from);
        TextView toText = findViewById(R.id.to);
        TextView personText = findViewById(R.id.Person);
        TextView finalStatusText = findViewById(R.id.Final_status);
        TextView approvalText = findViewById(R.id.approval);
        TextView feesText = findViewById(R.id.fees);
        TextView paidOnText = findViewById(R.id.paid_on);
        TextView paidText = findViewById(R.id.paid);
        TextView locationText = findViewById(R.id.location);
        TextView dueText = findViewById(R.id.due);


        if(date != null)
            dateText.setText(date);
        else
            dateText.setText("Not provided");
        if(remark != null)
            remarkText.setText(remark);
        else
            remarkText.setText("Not provided");


        if(status != null)
            statusText.setText(status);
        else
            statusText.setText("Not provided");


        if(trainerEmai != null)
            trainerEmailText.setText(trainerEmai);
        else
            trainerEmailText.setText("Not provided");


        if(fromDate != null)
             fromText.setText(fromDate);

        else
            fromText.setText("Not provided");


        if(toDate != null)
            toText.setText(toDate);
        else
            toText.setText("Not provided");



        if(companyPerson != null)
            personText.setText(companyPerson);
        else
            personText.setText("Not provided");



        if(finalStatus != null)
            finalStatusText.setText(finalStatus);
        else
            finalStatusText.setText("Not provided");


        if(approval != null)
             approvalText.setText(approval);
        else
            approvalText.setText("Not provided");



        if(fees != null)
            feesText.setText(fees);

        else
            feesText.setText("Not provided");

        if(paid != null)
            paidText.setText(paid);

        else
            paidText.setText("Not provided");


        if(paidOn != null)
            paidOnText.setText(paidOn);
        else
            paidOnText.setText("Not provided");




        if(due != null)
            dueText.setText(due);
        else
            dueText.setText("Not provided");



        if(location != null)
            locationText.setText(location);
        else
            locationText.setText("Not provided");







        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        getWindow().setLayout((int) (displayMetrics.widthPixels * .86), (int) (displayMetrics.heightPixels * .88));


    }
}
