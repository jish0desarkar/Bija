package com.example.user.appbijatraining;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AppointmentsFragment extends Fragment {
    NavigationView navigationView;
    static List<AppointmentList> appointmentLists;
    static ListView listView;


    String[] app_id = new String[30];
    String[] staff_id = new String[30];
    String[] company_id = new String[30];
    String[] call_loop_id = new String[30];
    String[] date = new String[30];
    String[] remark = new String[30];
    String[] fromDate= new String[30];
    String[] toDate = new String[30];
    String[] company_person = new String[30];
    String[]  Post_remark = new String[30];
    String[] status= new String[30];
    String[] final_status = new String[30];
    String[] approval= new String[30];
    String[] trainer_id = new String[30];
    String[] trainer_cnf = new String[30];
    String[] trainers = new String[30];
    String[] trainer_email = new String[30];
    String[] addedBy = new String[30];
    String[] location = new String[30];
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        navigationView = getActivity().findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.appt_nav_bt);
        return inflater.inflate(R.layout.fragment_appointments, container, false);



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


       Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Appointments");

        appointmentLists = new ArrayList<>();

        HashMap<String, String> hashMap = new HashMap<>();
        PostingClass postingClass = new PostingClass();
        hashMap.put("staff_id", "73");
        hashMap.put("date", "2071-04-09");

        new FetchProgram().execute("73", "2017-04-12");




        listView = getActivity().findViewById(R.id.appointment_list);

        AppointmentListAdapter adapter = new AppointmentListAdapter(getContext(), R.layout.custom_appointment_list_item, appointmentLists);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getContext(), PopUpActivity.class);
                intent.putExtra("intent", "Appt");
                intent.putExtra("app_id", app_id[position]);
                intent.putExtra("staff_id", staff_id[position]);
                intent.putExtra("company_id", company_id[position]);
                intent.putExtra("call_loop_id", call_loop_id[position]);
                intent.putExtra("date", date[position]);
                intent.putExtra("remark", remark[position]);
                intent.putExtra("fromdate", fromDate[position]);
                intent.putExtra("todate", toDate[position]);
                intent.putExtra("company_person", company_person[position]);
                intent.putExtra("Post_remark", Post_remark[position]);
                intent.putExtra("status", status[position]);
                intent.putExtra("final_status", final_status[position]);
                intent.putExtra("approval", approval[position]);
                intent.putExtra("trainer_id", trainer_id[position]);
                intent.putExtra("trainer_cnf", trainer_cnf[position]);
                intent.putExtra("trainers", trainers);
                intent.putExtra("trainer_email", trainer_email[position]);
                intent.putExtra("added_by", addedBy[position]);
                intent.putExtra("location", location[position]);



                startActivity(intent);


            }
        });

        listView.setLongClickable(true);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), LongPressPopUpActivity.class);
                intent.putExtra("tag", "Appt");
                intent.putExtra("app_id", app_id[position]);
                intent.putExtra("staff_id", staff_id[position]);
                intent.putExtra("company_id", company_id[position]);
                intent.putExtra("call_loop_id", call_loop_id[position]);
                intent.putExtra("date", date[position]);
                intent.putExtra("remark", remark[position]);
                intent.putExtra("fromdate", fromDate[position]);
                intent.putExtra("todate", toDate[position]);
                intent.putExtra("company_person", company_person[position]);
                intent.putExtra("Post_remark", Post_remark[position]);
                intent.putExtra("status", status[position]);
                intent.putExtra("final_status", final_status[position]);
                intent.putExtra("approval", approval[position]);
                intent.putExtra("trainer_id", trainer_id[position]);
                intent.putExtra("trainer_cnf", trainer_cnf[position]);
                intent.putExtra("trainers", trainers[position]);
                intent.putExtra("trainer_email", trainer_email[position]);
                intent.putExtra("added_by", addedBy[position]);
                intent.putExtra("location", location[position]);
                startActivity(intent);
                return true;
            }
        });

    }

    class FetchProgram extends AsyncTask<String, String, String> {

        ProgressDialog loading;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = ProgressDialog.show(getContext(), "", "Hold on.....", true, false);
        }

        @Override
        protected String doInBackground(String... arg0) {
            HashMap<String, String> data = new HashMap<>();
            data.put("staff_id", arg0[0]);
            data.put("date", arg0[1]);

            PostingClass ruc = new PostingClass();

            String result = ruc.sendPostRequest("https://bijatraining.000webhostapp.com/appointment_ret.php", data);

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.i("JSON", result);
            try {
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0; i < jsonArray.length(); i++) {


                    JSONObject json = jsonArray.getJSONObject(i);
                    Log.i("JSON OBJ", json.getString("app_id"));


                    app_id[i] = json.getString("app_id");
                    staff_id[i] = json.getString("staff_id");
                    company_id[i] = json.getString("company_id");
                    call_loop_id[i] = json.getString("call_loop_id");
                    date[i] = json.getString("date");
                    remark[i]= json.getString("remark");
                    fromDate[i] = json.getString("fromdate");
                    toDate[i] = json.getString("todate");
                    company_person[i] = json.getString("company_person");
                    Post_remark[i] = json.getString("Post_remark");
                    status[i] = json.getString("status");
                    final_status[i] = json.getString("final_status");
                    approval[i] = json.getString("approval");
                    trainer_id[i] = json.getString("trainer_id");
                    trainer_cnf[i] = json.getString("trainer_cnf");
                    trainers[i] = json.getString("trainers");
                    location[i] = json.getString("location");
                    trainer_email[i] = json.getString("trainer_email");
                    addedBy[i] = json.getString("added_by");


                    loading.dismiss();

                    appointmentLists.add(new AppointmentList(app_id[i], date[i], staff_id[i], addedBy[i]));

                    listView = getActivity().findViewById(R.id.appointment_list);

                    AppointmentListAdapter adapter = new AppointmentListAdapter(getContext(), R.layout.custom_programme_listview, appointmentLists);

                    listView.setAdapter(adapter);


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}


