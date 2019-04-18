package com.example.user.appbijatraining;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.extensions.CalendarViewPager;
import com.applandeo.materialcalendarview.listeners.OnCalendarPageChangeListener;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.SocketImpl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;




public class CalendarFragment extends Fragment {


    String[] fromdateJSON = new String[100];
    String[] todateJSON = new String[100];
    String[] nameJSON = new String[100];
    String[] company_nameJSON = new String[100];

    HashMap<String, String> data = new HashMap<>() ;

    Calendar[] calendar = new Calendar[50];

    Calendar calendar1 = Calendar.getInstance();

    List<EventDay> events = new ArrayList<>();

    int temp;

    Date[] fetchedDate = new Date[50];
    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");


    CalendarView calendarView;

    NavigationView navigationView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        navigationView = getActivity().findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.calendar_nav_btn);
        return inflater.inflate(R.layout.activity_calendar, container, false);



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        for (int i = 0; i<50; i++){

            fetchedDate[i] = new Date();
            calendar[i] = Calendar.getInstance();

        }

        calendarView = getActivity().findViewById(R.id.calendar_view);

        calendar1 = calendarView.getCurrentPageDate();

        Date date = calendar1.getTime();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

        String fromDate = simpleDateFormat.format(date);

        int max = calendar1.getActualMaximum(Calendar.DAY_OF_MONTH);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("MM/"+String.valueOf(max)+"/yyyy");

        String toDate  = simpleDateFormat1.format(date);

        data.put("staff_id", "73");
        data.put("fromdate", fromDate);
        data.put("todate", toDate);

        new FetchProgram().execute();



        calendarView.setOnForwardPageChangeListener(new OnCalendarPageChangeListener() {
            @Override
            public void onChange() {
                Calendar calendar = calendarView.getCurrentPageDate();

                Date date = calendar.getTime();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

                String fromDate = simpleDateFormat.format(date);

                int max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("MM/"+String.valueOf(max)+"/yyyy");

                String toDate  = simpleDateFormat1.format(date);

                data.clear();

                data.put("staff_id", "73");
                data.put("fromdate", fromDate);
                data.put("todate", toDate);

                Log.w("Forward", fromDate+"---"+toDate);

                new FetchProgram().execute();





            }
        });

        calendarView.setOnPreviousPageChangeListener(new OnCalendarPageChangeListener() {
            @Override
            public void onChange() {
                Calendar calendar = calendarView.getCurrentPageDate();

                Date date = calendar.getTime();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

                String fromDate = simpleDateFormat.format(date);

                int max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("MM/"+String.valueOf(max)+"/yyyy");

                String toDate  = simpleDateFormat1.format(date);


                data.clear();

                data.put("staff_id", "73");
                data.put("fromdate", fromDate);
                data.put("todate", toDate);

                new FetchProgram().execute();

                Log.i("backward", fromDate+"---"+toDate);


            }
        });



    }

    class FetchProgram extends AsyncTask<String, String, String>  {

        int loopCount = 0;

        //ProgressDialog loading;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //loading = ProgressDialog.show(getContext(), "", "Hold on.....", true, false);
        }

        @Override
        protected String doInBackground(String... arg0) {


            PostingClass ruc = new PostingClass();

            String result = ruc.sendPostRequest("https://bijatraining.000webhostapp.com/calender_ret.php", data);

            Log.i("Staff", data.get("staff_id"));
            Log.i("Staff", data.get("fromdate"));
            Log.i("Staff", data.get("todate"));


            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            for (int x = 0; x<50; x++){
                fetchedDate[x] = null;
            }



            super.onPostExecute(result);
            Log.i("JSON", result);
            try {
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0; i < jsonArray.length(); i++) {

                    loopCount++;

                    JSONObject json = jsonArray.getJSONObject(i);

                    fromdateJSON[i] = json.getString("fromdate");
                    todateJSON[i]=json.getString("todate");
                    nameJSON[i]=json.getString("name");
                    company_nameJSON[i]=json.getString("company_name");




                }

            } catch (JSONException e1) {
                e1.printStackTrace();
            }

                    temp = loopCount;

                    for(int x=0; x<loopCount; x++ ) {


                        try {
                            fetchedDate[x] = simpleDateFormat2.parse(fromdateJSON[x]);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        calendar[x].setTime(fetchedDate[x]);
                        events.add(new EventDay(calendar[x], R.drawable.appointment));
                    }

            calendarView.setEvents(events);




                    calendarView.setOnDayClickListener(new OnDayClickListener() {
                        @Override
                        public void onDayClick(EventDay eventDay) {

                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                            Date selectdate = eventDay.getCalendar().getTime();
                            String strDateSelect = simpleDateFormat.format(selectdate);
                            String fetchetSTRdate = "";
                            String fetchedDate = "";

                            int lol;

                            for(int k = 0; k<temp; k++){
                                fetchedDate = fromdateJSON[k];
                                try {
                                    Date temp = simpleDateFormat1.parse(fetchedDate);
                                    fetchetSTRdate = simpleDateFormat.format(temp);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }



                                if (strDateSelect.equals(fetchetSTRdate)){
                                    Log.w("Equal", "yes");

                                    Log.w("Diplay is", fromdateJSON[k]);

                                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                    builder.setMessage("Company:   "+company_nameJSON[k]+"\n\n"+"Name:  "+nameJSON[k])
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {

                                                }
                                            });
                                    builder.create().show();






                                    break;
                                }
                                else
                                    continue;

                            }


                            }
                    });





            loopCount = 0;


            }


        }
    }


