package com.example.user.appbijatraining;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class PopUpRemark extends AppCompatActivity {

    HashMap<String, String> data = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_remark);

        EditText remarkTextField = findViewById(R.id.remark_text_field);
        Button addRemarkBtn = findViewById(R.id.add_remark_btn);
        Intent intent = getIntent();
        String tag = intent.getStringExtra("tag");
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

        HashMap<String, String> data = new HashMap<>();

        Log.i("TAGGGG", tag);

       addRemarkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newRemark = remarkTextField.getText().toString();

                if(tag.equals("Follow up")){
                    new sendInfo().execute("73", "2017-04-09", date, newRemark, trainerEmai, fromDate, toDate, companyPerson,
                            finalStatus, approval, status, fees, due, paidOn, location, paid, "Follow up");

                }


            }
        });










        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        getWindow().setLayout((int) (displayMetrics.widthPixels * .7), (int) (displayMetrics.heightPixels * .5));
    }

    class sendInfo extends AsyncTask<String, String, String> {

        ProgressDialog loading;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = ProgressDialog.show(PopUpRemark.this, "", "Sending Information, Please wait..", true, false);
        }

        @Override
        protected String doInBackground(String... arg0) {

            data.put("staff_id", arg0[0]);
            data.put("date1", arg0[1]);
            data.put("date2", arg0[2]);
            data.put("remark", arg0[3]);
            data.put("trainer_email", arg0[4]);
            data.put("from", arg0[5]);
            data.put("to", arg0[6]);
            data.put("person", arg0[7]);
            data.put("final_status", arg0[8]);
            data.put("approval", arg0[9]);
            data.put("status", arg0[10]);
            data.put("fees", arg0[11]);
            data.put("due", arg0[12]);
            data.put("paid_on", arg0[13]);
            data.put("location", arg0[14]);
            data.put("paid", arg0[15]);
            data.put("tag", arg0[16]);

            Log.i("INFORMATION", data.get("fees"));
            Log.i("REMARK", data.get("remark"));



            //PostingClass ruc = new PostingClass();

            //String result = ruc.sendPostRequest("URL HERE", data);

            return "Sent success";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            Log.i("Thread", result);

            loading.dismiss();

            Toast.makeText(getApplicationContext(), "Added successfully", Toast.LENGTH_SHORT).show();

            finish();

            /*try {
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0; i < jsonArray.length(); i++) {


                    JSONObject json = jsonArray.getJSONObject(i);
                    Log.i("JSON OBJ", json.getString("prg_id"));


                   /* prg_id = json.getString("prg_id");
                    title = json.getString("title");
                    trainer = json.getString("trainers");
                    addedBy = json.getString("added_by");
                    date = json.getString("date");
                    remark = json.getString("remark");
                    status = json.getString("trainer_cnf");
                    trainerEmai = json.getString("trainer_email");
                    fromDate = json.getString("fromdate");
                    toDate = json.getString("todate");
                    companyPerson = json.getString("company_person");
                    finalStatus = json.getString("final_status");
                    approval = json.getString("approval");
                    fees = json.getString("fees");
                    paid = json.getString("paid");
                    due = json.getString("due");
                    location = json.getString("location");
                    paidOn = json.getString("t_paid_on");


                    loading.dismiss();

                    appointmentLists.add(new AppointmentList(prg_id, title, trainer, addedBy));

                    listView = getActivity().findViewById(R.id.appointment_list);

                    AppointmentListAdapter adapter = new AppointmentListAdapter(getContext(), R.layout.custom_programme_listview, appointmentLists);

                    listView.setAdapter(adapter);


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }*/
        }
    }
}
