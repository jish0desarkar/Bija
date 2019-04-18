package com.example.user.appbijatraining;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PopUpActivity extends AppCompatActivity {

    Button closeButton;
    TextView popupText;
    String text;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //closeButton = findViewById(R.id.close_pop_button);
        Intent intent = getIntent();

        String activity = intent.getStringExtra("intent");
        Bundle bundle = new Bundle();
        String bundleString = bundle.getString("intent");


        if(activity.equalsIgnoreCase("Follow Up")) {
            //For appt extras

            setContentView(R.layout.activity_pop_up_follow);
            String Date =    intent.getStringExtra("Date");
            String addedBy =    intent.getStringExtra("added_by");
            String  remark =   intent.getStringExtra("remark");
            String flwId =    intent.getStringExtra("flw_id");
            String staffId =    intent.getStringExtra("staff_id");
            String  companyId =   intent.getStringExtra("company_id");
            String  person = intent.getStringExtra("person");
            String  finalStatus = intent.getStringExtra("final_status");
            String   callLoopId = intent.getStringExtra("call_loop_id");
            String  status =   intent.getStringExtra("status");
            String  followupDate=  intent.getStringExtra("followup_date");
            String  postRemark =   intent.getStringExtra("post_remark");
            String  lastCallDate =   intent.getStringExtra("lastcalldate");

            Log.i("sdfsdf", "qwert6yui");

            TextView  FollowIdText      = findViewById(R.id.f_flwid);
            TextView  StaffIdText      = findViewById(R.id.f_staff_id);
            TextView  CompanyIdText      = findViewById(R.id.f_company_id);
            TextView  callLoopIdText      = findViewById(R.id.f_call_loop_id);
            TextView  lastFollowUpText      = findViewById(R.id.f_lflw_id);
            TextView  remarkText      = findViewById(R.id.l_remark);
            TextView  nextFollowUpText      = findViewById(R.id.f_nflw_id);
            TextView  companyPersonText      = findViewById(R.id.company_person);
            TextView  postRemarkText      = findViewById(R.id.post_remark);
            TextView  finalStatusText      = findViewById(R.id.f_f_status);
            TextView  staffText      = findViewById(R.id.f_staff);

            FollowIdText.setText(flwId);
            StaffIdText.setText(staffId);
            CompanyIdText.setText(companyId);
            callLoopIdText.setText(callLoopId);
            lastFollowUpText.setText(lastCallDate);
            remarkText.setText(remark);
            nextFollowUpText.setText(followupDate);
            companyPersonText.setText(person);
            postRemarkText.setText(postRemark);
            finalStatusText.setText(finalStatus);
            staffText.setText(addedBy);







            closeButton = findViewById(R.id.close_pop_button);

            closeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

            getWindow().setLayout((int) (displayMetrics.widthPixels * .86), (int) (displayMetrics.heightPixels * .88));

           /* TextView dateText = findViewById(R.id.date);
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
                locationText.setText("Not provided");*/
        }

        //For Follow up Extras

        if(activity.equalsIgnoreCase("Appt")){

            setContentView(R.layout.activity_pop_up_appointment);
            String app_id, staff_id, company_id, call_loop_id, date, remark, fromDate, toDate, company_person, Post_remark, status, final_status,
                    approval, trainer_id, trainer_cnf, trainers, trainer_email, addedBy, location;

            app_id=intent.getStringExtra("app_id");
            staff_id=intent.getStringExtra("staff_id");
            company_id=intent.getStringExtra("company_id");
            call_loop_id=intent.getStringExtra("call_loop_id");
            date=intent.getStringExtra("date");
            remark=intent.getStringExtra("remark");
            fromDate=intent.getStringExtra("fromdate");
            toDate=intent.getStringExtra("todate");
            company_person=intent.getStringExtra("company_person");
            Post_remark=intent.getStringExtra("Post_remark");
           /* intent.getStringExtra("status");
            intent.getStringExtra("final_status");
            intent.getStringExtra("approval");
            intent.getStringExtra("trainer_id");
            intent.getStringExtra("trainer_cnf");
            intent.getStringExtra("trainers");
            intent.getStringExtra("trainer_email");
            intent.getStringExtra("added_by");
            intent.getStringExtra("location");*/

            TextView appIdText = findViewById(R.id.a_appid);
            TextView staffIdText = findViewById(R.id.a_staff_id);
            TextView companyIdText = findViewById(R.id.a_company_id);
            TextView callloopText = findViewById(R.id.a_call_loop_id);
            TextView dateText = findViewById(R.id.a_date);
            TextView remarkText = findViewById(R.id.a_remark);
            TextView fromDateText = findViewById(R.id.a_fdate);
            TextView toDateText = findViewById(R.id.a_tdate);
            TextView companyPersonText = findViewById(R.id.a_company_person);
            TextView postRemarkText = findViewById(R.id.a_post_remark);


            appIdText.setText(app_id);
            staffIdText.setText(staff_id);
            companyIdText.setText(company_id);
            callloopText.setText(call_loop_id);
            dateText.setText(date);
            remarkText.setText(remark);
            fromDateText.setText(fromDate);
            postRemarkText.setText(Post_remark);
            toDateText.setText(toDate);
            companyPersonText.setText(company_person);


            closeButton = findViewById(R.id.close_pop_button);

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

        if(activity.equalsIgnoreCase("prog")){

            setContentView(R.layout.activity_pop_up);

            String date = intent.getStringExtra("Date");
            String remark = intent.getStringExtra("remark");
            String trainer_email = intent.getStringExtra("trainer_email" );
            Log.i("trainer email", trainer_email);
            String from = intent.getStringExtra("from" );
            String to = intent.getStringExtra("to" );
            String person = intent.getStringExtra("person");
            String final_status = intent.getStringExtra("final_status");
            String approval = intent.getStringExtra("approval");
            String status = intent.getStringExtra("status");
            String fees=intent.getStringExtra("fees");
            String due = intent.getStringExtra("due");
            String paid_on = intent.getStringExtra("paid_on");
            String location = intent.getStringExtra("location");
            String paid = intent.getStringExtra("paid");


            TextView trainer_emailText = findViewById(R.id.trainer_emailll);
            TextView statusText = findViewById(R.id.status);
            TextView final_statusText = findViewById(R.id.Final_status);
            TextView approvalText = findViewById(R.id.approval);
            TextView dateText = findViewById(R.id.date);
            TextView remarkText = findViewById(R.id.remark);
            TextView fromDateText = findViewById(R.id.from);
            TextView toDateText = findViewById(R.id.to);
            TextView companyPersonText = findViewById(R.id.Person);
            TextView feesText = findViewById(R.id.fees);
            TextView dueText = findViewById(R.id.due);
            TextView paidonText = findViewById(R.id.paid_on);
            TextView paidText = findViewById(R.id.paid);
            //TextView callloopText = findViewById(R.id.a_call_loop_id);
            TextView locationText = findViewById(R.id.location);

           trainer_emailText.setText(trainer_email);
            statusText.setText(status);
            final_statusText.setText(final_status);
            approvalText.setText(approval);
            dateText.setText(date);
            remarkText.setText(remark);
            fromDateText.setText(from);
            toDateText.setText(to);
            companyPersonText.setText(person);
            feesText.setText(fees);
            dueText.setText(due);
            paidonText.setText(paid_on);
            paidText.setText(paid);
            locationText.setText(location);
            closeButton = findViewById(R.id.close_pop_button);

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
}
