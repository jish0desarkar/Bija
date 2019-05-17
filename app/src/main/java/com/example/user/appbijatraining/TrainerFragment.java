package com.example.user.appbijatraining;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class TrainerFragment extends Fragment  implements DatePickerDialog.OnDateSetListener {
    static List<AppointmentList> appointmentLists;
    static ListView listView1;

    public static Handler UIHandler;

    Detail_Extracter detail_extracter;

    String sendDate;

    int fromDay, fromMonth, fromYear, finalfromDate, finalfromMonth, finalfromYear, extradate, extramonth, extrayear;

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

    static ArrayList<ProgrammeList> programmeLists;
    static ListView listView2;
    TextView staff_id1;
    TextView dateText;
    String[] prg_id1 = new String[100];
    String[] title1 = new String[100];
    String[] trainer1 = new String[100];
    String[] addedBy1 = new String[100];
    String[] date1 = new String[100];
    String[] remark1 = new String[100];
    String[] status1 = new String[100];
    String[] trainerEmai1 = new String[100];
    String[] trainerDate1 = new String[100];
    String[] fromDate1 = new String[100];
    String[] toDate1 = new String[100];
    String[] companyPerson1 = new String[100];
    String[] finalStatus1 = new String[100];
    String[] approval1 = new String[100];
    String[] fees1 = new String[100];
    String[] paidOn1 = new String[100];
    String[] paid1 = new String[100];
    String[] due1 = new String[100];
    String[] location1 = new String[100];

    ListView list1, list2;
    List<String> listappt, listprg;
    String[] appt = {"ID: 1  Remark: Appointment Fixed For Mercedes",
            "ID: 2  Remark: Program Fixed For Ashok Layland"};
    String[] program = {"ID: 1  Remark: Program Fixed For Trainer Harinath",
            "ID: 2  Remark: Program Fixed For Trainer Nandhakumar"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.v("Trainer", "Trainer Frsgment");
        return inflater.inflate(R.layout.fragment_trainer, container, false);

    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        dateText = getActivity().findViewById(R.id.date_text);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Home");

        appointmentLists = new ArrayList<>();


        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                fromDay = calendar.get(Calendar.DAY_OF_MONTH);
                fromMonth = calendar.get(Calendar.MONTH);
                fromYear = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        TrainerFragment.this, fromYear, fromMonth, fromDay);
                datePickerDialog.show();
            }
        });

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        java.util.Date cal = Calendar.getInstance().getTime();



        sendDate = dateFormat.format(cal);

        Log.d("senddate", sendDate);

        dateText.setText(dateFormat.format(cal).toString());






         detail_extracter = new Detail_Extracter(getContext());
        if (detail_extracter.getRole().equalsIgnoreCase("trainer")) {

            new FetchProgram().execute(detail_extracter.getId(), dateFormat.format(cal), detail_extracter.getRole());
            new FetchProgram1().execute(detail_extracter.getId(), dateFormat.format(cal), detail_extracter.getRole());
        }
        if (detail_extracter.getRole().equalsIgnoreCase("staff")) {
            new FetchProgram1().execute(detail_extracter.getId(),dateFormat.format(cal), detail_extracter.getRole());
            new FetchProgram().execute(detail_extracter.getId(), dateFormat.format(cal), detail_extracter.getRole());
        }



        listView1 = getActivity().findViewById(R.id.listappt);

        listView1.setClickable(false);

        AppointmentListAdapter adapter = new AppointmentListAdapter(getContext(), R.layout.custom_appointment_list_item, appointmentLists);

        listView1.setAdapter(adapter);

        programmeLists = new ArrayList<>();




        listView2 = getActivity().findViewById(R.id.listprg);
        listView2.setClickable(false);

        ProgramListAdapter adapter1 = new ProgramListAdapter(getContext(), R.layout.custom_programme_listview, programmeLists);

        listView2.setAdapter(adapter1);

        /*list1 = getActivity().findViewById(R.id.listappt);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_expandable_list_item_1, appt);
        list1.setAdapter(adapter);

        list2 = getActivity().findViewById(R.id.listprg);



        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_expandable_list_item_1, program);
        list2.setAdapter(adapter1);*/

        TextView bt1 = getActivity().findViewById(R.id.bt1);
        TextView bt2 = getActivity().findViewById(R.id.bt2);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new AppointmentsFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("intent", "appt");

                bundle.putString("date", String.valueOf(extradate));
                bundle.putString("month", String.valueOf(extramonth+1));
                bundle.putString("year", String.valueOf(extrayear));

                bundle.putString("Date", sendDate);
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ProgrammeFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("intent", "prog");
                Log.w("JJJFFJJJFF", String.valueOf(extrayear));
                bundle.putString("date", String.valueOf(extradate));
                bundle.putString("month", String.valueOf(extramonth+1));
                bundle.putString("year", String.valueOf(extrayear));
                Log.w("asdasdasd", sendDate);
                bundle.putString("Date", sendDate);
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });



    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        finalfromDate = dayOfMonth;
        finalfromMonth = month;
        finalfromYear = year;
        extradate=finalfromDate;
        extramonth=finalfromMonth;
        extrayear=finalfromYear;
        sendDate = String.valueOf(finalfromYear)+"-"+String.valueOf(finalfromMonth+1)+"-"+String.valueOf(finalfromDate);
        dateText.setText(String.valueOf(finalfromYear)+"-"+String.valueOf(finalfromMonth+1)+"-"+String.valueOf(finalfromDate));


        if (detail_extracter.getRole().equalsIgnoreCase("trainer")) {

            new FetchProgram().execute(detail_extracter.getId(), String.valueOf(finalfromYear) + "-" + String.valueOf(finalfromMonth + 1) + "-" + String.valueOf(finalfromDate), detail_extracter.getRole());
            new FetchProgram1().execute(detail_extracter.getId(), String.valueOf(finalfromYear) + "-" + String.valueOf(finalfromMonth + 1) + "-" + String.valueOf(finalfromDate), detail_extracter.getRole());
        }

        if (detail_extracter.getRole().equalsIgnoreCase("staff")) {
            new FetchProgram().execute(detail_extracter.getId(), String.valueOf(finalfromYear) + "-" + String.valueOf(finalfromMonth + 1) + "-" + String.valueOf(finalfromDate), detail_extracter.getRole());

            new FetchProgram1().execute(detail_extracter.getId(), String.valueOf(finalfromYear) + "-" + String.valueOf(finalfromMonth + 1) + "-" + String.valueOf(finalfromDate), detail_extracter.getRole());
        }
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
            if(arg0[2].equalsIgnoreCase("trainer")) {
                data.put("trainer_id", arg0[0]);
                data.put("date", arg0[1]);
                data.put("role", arg0[2]);
            }
            if (arg0[2].equalsIgnoreCase("staff")){
                data.put("staff_id", arg0[0]);
                data.put("date", arg0[1]);
                data.put("role", arg0[2]);

            }

            PostingClass ruc = new PostingClass();

            String result = ruc.sendPostRequest("https://bijatraining.000webhostapp.com/appointment_ret.php", data);

            return result;
        }

        @Override
        protected void onPostExecute(String result) {

            AppointmentListAdapter adapter;
            listView1 = getActivity().findViewById(R.id.listappt);
            listView1.setClickable(false);
            adapter = new AppointmentListAdapter(getContext(), R.layout.custom_programme_listview, appointmentLists);
            adapter.clear();
            appointmentLists.clear();
            super.onPostExecute(result);
            appointmentLists.clear();
            loading.dismiss();
            listView1.setAdapter(adapter);
            Log.i("JSON", result);
            if (result.equalsIgnoreCase("No pending available")) {
                Toast.makeText(getContext(), "Sorry No Appoiontment pending available", Toast.LENGTH_LONG).show();
                appointmentLists.clear();
            } else {
                try {

                    JSONArray jsonArray = new JSONArray(result);
                    for (int i = 0; i < jsonArray.length(); i++) {


                        JSONObject json = jsonArray.getJSONObject(i);

                        app_id[i] = json.getString("app_id");
                        staff_id[i] = json.getString("staff_id");
                        company_id[i] = json.getString("company_id");
                        call_loop_id[i] = json.getString("call_loop_id");
                        date[i] = json.getString("date");
                        remark[i] = json.getString("remark");
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


//                        loading.dismiss();


                        appointmentLists.add(new AppointmentList(app_id[i], date[i], staff_id[i], addedBy[i]));

                    }

                    listView1.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    class FetchProgram1 extends AsyncTask<String, String, String> {

        ProgressDialog loading;
        @Override
        protected void onPreExecute() {



            super.onPreExecute();
           loading = ProgressDialog.show(getContext(),"","Hold on.....",true,false);
        }

        @Override
        protected String doInBackground(String... arg0) {
            HashMap<String, String> data = new HashMap<>();
            if(arg0[2].equalsIgnoreCase("trainer")) {
                data.put("trainer_id", arg0[0]);
                data.put("date", arg0[1]);
                data.put("role", arg0[2]);
            }
            if (arg0[2].equalsIgnoreCase("staff")){
                data.put("staff_id", arg0[0]);
                data.put("date", arg0[1]);
                data.put("role", arg0[2]);

            }
            PostingClass ruc = new PostingClass();

            String result = ruc.sendPostRequest("https://bijatraining.000webhostapp.com/program_ret.php", data);

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            loading.dismiss();
            programmeLists.clear();
            listView2 = getActivity().findViewById(R.id.listprg);
            listView2.setClickable(false);
            ProgramListAdapter adapter1 = new ProgramListAdapter(getContext(), R.layout.custom_programme_listview, programmeLists);
            adapter1.clear();
            programmeLists.clear();
            listView2.setAdapter(adapter1);

            if (result.equalsIgnoreCase("No pending available")) {
                Toast.makeText(getContext(), "Sorry No Program available", Toast.LENGTH_LONG).show();
                programmeLists.clear();
            } else {
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    for (int i = 0; i < jsonArray.length(); i++) {


                        JSONObject json = jsonArray.getJSONObject(i);
                        Log.i("JSON OBJProg", json.getString("prg_id"));


                        prg_id1[i] = json.getString("prg_id");
                        title1[i] = json.getString("title");
                        trainer1[i] = json.getString("trainers");
                        addedBy1[i] = json.getString("added_by");
                        date1[i] = json.getString("date");
                        remark1[i] = json.getString("remark");
                        status1[i] = json.getString("trainer_cnf");
                        trainerEmai1[i] = json.getString("trainer_email");
                        fromDate1[i] = json.getString("fromdate");
                        toDate1[i] = json.getString("todate");
                        companyPerson1[i] = json.getString("company_person");
                        finalStatus1[i] = json.getString("final_status");
                        approval1[i] = json.getString("approval");
                        fees1[i] = json.getString("fees");
                        paid1[i] = json.getString("paid");
                        due1[i] = json.getString("due");
                        location[i] = json.getString("location");
                        paidOn1[i] = json.getString("t_paid_on");


//                        loading.dismiss();



                        programmeLists.add(new ProgrammeList(prg_id1[i], title1[i], trainer1[i], addedBy1[i]));




                    }

                    listView2.setAdapter(adapter1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
