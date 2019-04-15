package com.example.user.appbijatraining;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import static android.view.View.GONE;

public class LongPressPopUpActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    DatePickerDialog.OnDateSetListener fromDateListner, toDateListner;
    TimePickerDialog.OnTimeSetListener fromTimeListner, toTimeListner;

    String dateSetter;


    private Spinner spinner;
    private static final String[] paths = {"Appointment", "Follow Up", "Program"};

    int fromYear, fromMonth, fromDay, fromHour, fromMin, fromDayFinal, fromMonthFinal, fromYearFinal, fromHourFinal, fromMinFinal,
            t_datesYear, t_datesMonth, t_datesDay, t_datesHour, t_datesMin, t_datesDayFinal, t_datesMonthFinal, t_datesfromYearFinal, t_datesfromHourFinal, t_datesfromMinFinal,
            toYear, toMonth, toDay, toHour, toMin, toDayFinal, toMonthFinal, toYearFinal, toHourFinal, toMinFinal,
            t_paidYear, t_paidMonth, t_paidDay, t_paidHour, t_paidMin, t_paidDayFinal, t_paidMonthFinal, t_paidYearFinal, t_paidHourFinal, t_paidMinFinal,
            flw_dateYear, flw_dateMonth, flw_dateDay, flw_dateHour, flw_dateMin, flw_dateDayFinal, flw_dateMonthFinal, flw_dateYearFinal, flw_dateHourFinal, flw_dateMinFinal;
    HashMap<String, String> data = new HashMap<>();

    int eventType;
    String tag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_press_pop_up);

        java.util.Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c);
        Log.i("lastcalldate", formattedDate);


        Button closeButton = findViewById(R.id.close_btn);

        Button addEvent = findViewById(R.id.add_event_btn);

        Button fromDate = findViewById(R.id.from_date);

        Button toDate = findViewById(R.id.to_date);

        Button t_date_btn = findViewById(R.id.t_paid_date);
        float YY = t_date_btn.getY();

        Button flwDateBtn = findViewById(R.id.flw_up_date);

        EditText titleField = findViewById(R.id.title_field);
        EditText postRemarkEdiText = findViewById(R.id.postremark_field);
        EditText trainerIDField = findViewById(R.id.trainer_id_field);
        EditText trainerCNFField = findViewById(R.id.trainer_cnf_field);
        EditText trainersField = findViewById(R.id.trainers_field);
        EditText trainerEmailField = findViewById(R.id.trainer_email_field);
        EditText locationField = findViewById(R.id.location_field);
        EditText trainerFeesField = findViewById(R.id.trainer_fees_field);
        EditText feesField = findViewById(R.id.fees_field);
        EditText paidField = findViewById(R.id.paid_field);
        EditText invoiceField = findViewById(R.id.invoice_field);
        EditText t_feesField = findViewById(R.id.t_fee_field);
        EditText t_paidField = findViewById(R.id.t_paid_field);


        //FROM DATE BUTTON
        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                dateSetter = "from";
                fromDay = calendar.get(Calendar.DAY_OF_MONTH);
                fromMonth = calendar.get(Calendar.MONTH);
                fromYear = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(LongPressPopUpActivity.this,
                        LongPressPopUpActivity.this, fromYear, fromMonth, fromDay);
                datePickerDialog.show();
            }
        });

        //TO DATE BUTTON
        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                dateSetter = "to";
                toDay = calendar.get(Calendar.DAY_OF_MONTH);
                toMonth = calendar.get(Calendar.MONTH);
                toYear = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(LongPressPopUpActivity.this,
                        LongPressPopUpActivity.this, toYear, toMonth, toDay);

                datePickerDialog.show();
            }
        });

        //FOLLOW DATE BUTTTON
        flwDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                dateSetter = "follow up";
                flw_dateDay = calendar.get(Calendar.DAY_OF_MONTH);
                flw_dateMonth = calendar.get(Calendar.MONTH);
                flw_dateYear = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(LongPressPopUpActivity.this,
                        LongPressPopUpActivity.this, flw_dateYear, flw_dateMonth, flw_dateDay);

                datePickerDialog.show();
            }
        });

        //T_DATE BUTTON
        t_date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                dateSetter = "t_paid_on";
                t_datesDay = calendar.get(Calendar.DAY_OF_MONTH);
                t_datesMonth = calendar.get(Calendar.MONTH);
                t_datesYear = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(LongPressPopUpActivity.this,
                        LongPressPopUpActivity.this, t_datesYear, t_datesMonth, t_datesDay);

                datePickerDialog.show();
            }
        });


        Intent intent = getIntent();
        tag = intent.getStringExtra("tag");

        Log.i("tag", tag);

        /*String date = intent.getStringExtra("Date");
        String remark = intent.getStringExtra("remark");
        String trainerEmai = intent.getStringExtra("trainer_email");
        String fromDate = intent.getStringExtra("from");
        String toDate = intent.getStringExtra("to");
        String companyPerson = intent.getStringExtra("person");
        String finalStatus = intent.getStringExtra("final_status");
        String approval = intent.getStringExtra("approval");
        String status = intent.getStringExtra("status");
        String fees = intent.getStringExtra("fees");
        String due = intent.getStringExtra("due");
        String paidOn = intent.getStringExtra("paid_on");
        String location = intent.getStringExtra("location");
        String paid = intent.getStringExtra("paid");
        String flw_id = intent.getStringExtra("flw_id");*/

        //DROP DOWN MENU SPINNER
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(LongPressPopUpActivity.this,
                android.R.layout.simple_spinner_item, paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        //DROPDOWN ITEM CLICK LISTNER
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                float Y = flwDateBtn.getY();

                //CHECKING THE POSITION OF THE ITEM CLICKED
                switch (position) {

                    case 0:


                        postRemarkEdiText.setVisibility(View.VISIBLE);
                        trainerIDField.setVisibility(View.VISIBLE);
                        trainerCNFField.setVisibility(View.VISIBLE);
                        trainerEmailField.setVisibility(View.VISIBLE);
                        trainersField.setVisibility(View.VISIBLE);
                        locationField.setVisibility(View.VISIBLE);
                        fromDate.setVisibility(View.VISIBLE);
                        toDate.setVisibility(View.VISIBLE);


                        titleField.setVisibility(GONE);
                        t_feesField.setVisibility(GONE);
                        t_paidField.setVisibility(GONE);
                        t_date_btn.setVisibility(GONE);
                        flwDateBtn.setVisibility(GONE);
                        invoiceField.setVisibility(GONE);
                        trainerFeesField.setVisibility(GONE);
                        feesField.setVisibility(GONE);
                        paidField.setVisibility(GONE);

                        //IF APPOINTMENT THEN EVEN TYPE IS 0
                        eventType = 0;

                        break;

                    case 1:

                        postRemarkEdiText.setVisibility(View.VISIBLE);
                        flwDateBtn.setVisibility(View.VISIBLE);
                        flwDateBtn.setText("Follow up dates");
                        flwDateBtn.setY(700);


                        fromDate.setVisibility(GONE);
                        toDate.setVisibility(GONE);
                        titleField.setVisibility(GONE);
                        t_feesField.setVisibility(GONE);
                        t_paidField.setVisibility(GONE);
                        t_date_btn.setVisibility(GONE);
                        invoiceField.setVisibility(GONE);
                        trainerFeesField.setVisibility(GONE);
                        feesField.setVisibility(GONE);
                        paidField.setVisibility(GONE);
                        trainerCNFField.setVisibility(GONE);
                        trainerEmailField.setVisibility(GONE);
                        trainerFeesField.setVisibility(GONE);
                        trainerIDField.setVisibility(GONE);
                        locationField.setVisibility(GONE);
                        t_date_btn.setVisibility(GONE);
                        t_feesField.setVisibility(GONE);
                        t_paidField.setVisibility(GONE);
                        paidField.setVisibility(GONE);
                        locationField.setVisibility(GONE);
                        trainersField.setVisibility(GONE);

                        //IF THE EVENT IS FOLLOW UP, EVENT TYPE IS 1
                        eventType = 1;
                        break;

                    case 2:

                        flwDateBtn.setVisibility(View.VISIBLE);
                        flwDateBtn.setText("trainer dates");
                        flwDateBtn.setY(1065);
                        fromDate.setVisibility(View.VISIBLE);
                        toDate.setVisibility(View.VISIBLE);
                        postRemarkEdiText.setVisibility(View.VISIBLE);
                        titleField.setVisibility(View.VISIBLE);
                        t_feesField.setVisibility(View.VISIBLE);
                        t_paidField.setVisibility(View.VISIBLE);
                        t_date_btn.setVisibility(View.VISIBLE);
                        invoiceField.setVisibility(View.VISIBLE);
                        trainerFeesField.setVisibility(View.VISIBLE);
                        feesField.setVisibility(View.VISIBLE);
                        paidField.setVisibility(View.VISIBLE);
                        trainerCNFField.setVisibility(View.VISIBLE);
                        trainerEmailField.setVisibility(View.VISIBLE);
                        trainerFeesField.setVisibility(View.VISIBLE);
                        trainerIDField.setVisibility(View.VISIBLE);
                        locationField.setVisibility(View.VISIBLE);
                        t_date_btn.setVisibility(View.VISIBLE);
                        t_feesField.setVisibility(View.VISIBLE);
                        t_paidField.setVisibility(View.VISIBLE);
                        paidField.setVisibility(View.VISIBLE);
                        locationField.setVisibility(View.VISIBLE);
                        trainersField.setVisibility(View.VISIBLE);


                        //FOR PROGRAM EVENT TYPE IS 2
                        eventType = 2;

                        break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


        //CLOSE BUTTON
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //ADD EVENT BUTTON
        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //CHECKING WHICH INTENT THE LONGPRESSINTENT WAS CLICKED FROM
                if (tag.equalsIgnoreCase("Follow Up")) {

                    //CHECKING THE EVENT TYPE FOR "FOLLOW UP"
                    if (eventType == 1) {


                        String Date = intent.getStringExtra("Date");
                        String addedBy = intent.getStringExtra("added_by");
                        String remark = intent.getStringExtra("remark");
                        String flwId = intent.getStringExtra("flw_id");
                        String staffId = intent.getStringExtra("staff_id");
                        String companyId = intent.getStringExtra("company_id");
                        String person = intent.getStringExtra("person");
                        String finalStatus = intent.getStringExtra("final_status");
                        String callLoopId = intent.getStringExtra("call_loop_id");
                        String status = intent.getStringExtra("status");
                        String followupDate = intent.getStringExtra("followup_date");
                        String postRemark = intent.getStringExtra("post_remark");
                        String lastCallDate = intent.getStringExtra("lastcalldate");

                        data.put("flw_id", flwId);
                        data.put("staff_id", staffId);
                        data.put("company_id", companyId);
                        data.put("call_loop_id", callLoopId);
                        data.put("remark", remark);
                        Log.i("date", String.valueOf(flw_dateYearFinal) + String.valueOf(flw_dateDayFinal));
                        Log.i("dsdfate", String.valueOf(toDayFinal) + String.valueOf(toYearFinal));
                        data.put("followup_date", String.valueOf(flw_dateYearFinal) + "-" + String.valueOf(flw_dateMonthFinal) + "-" + String.valueOf(flw_dateDayFinal));
                        data.put("company_person", person);
                        data.put("added_by", addedBy);

                        data.put("lastcalldate", formattedDate);
                        data.put("Post_remark", postRemarkEdiText.getText().toString());
                        data.put("final_status", "follow-up");


                        new sendRequestFollow().execute("Follow Up", "Follow up");
                    }
                    if (eventType == 0) {

                        Log.i("eventType in add btn", String.valueOf(eventType));
                        String Date = intent.getStringExtra("Date");
                        String addedBy = intent.getStringExtra("added_by");
                        String remark = intent.getStringExtra("remark");
                        String flwId = intent.getStringExtra("flw_id");
                        String staffId = intent.getStringExtra("staff_id");
                        String companyId = intent.getStringExtra("company_id");
                        String person = intent.getStringExtra("person");
                        String finalStatus = intent.getStringExtra("final_status");
                        String callLoopId = intent.getStringExtra("call_loop_id");
                        String status = intent.getStringExtra("status");
                        String followupDate = intent.getStringExtra("followup_date");
                        String postRemark = intent.getStringExtra("post_remark");
                        String lastCallDate = intent.getStringExtra("lastcalldate");

                        data.put("flw_id", flwId);
                        data.put("staff_id", staffId);
                        data.put("company_id", companyId);
                        data.put("call_loop_id", callLoopId);
                        data.put("remark", remark);
                        data.put("todate", String.valueOf(toYearFinal) + "-" + String.valueOf(toMonthFinal) + "-" + String.valueOf(toDayFinal));
                        data.put("fromdate", String.valueOf(fromYearFinal) + "-" + String.valueOf(fromMonthFinal) + "-" + String.valueOf(fromDayFinal));
                        data.put("company_person", person);
                        data.put("added_by", addedBy);
                        data.put("final_status", "appointment");
                        data.put("approval", "approved");
                        data.put("trainer_id", trainerIDField.getText().toString());
                        data.put("trainer_cnf", trainerCNFField.getText().toString());
                        data.put("trainers", trainersField.getText().toString());
                        data.put("trainer_email", trainerEmailField.getText().toString());
                        data.put("location", locationField.getText().toString());
                        java.util.Date c = Calendar.getInstance().getTime();

                        data.put("lastcalldate", formattedDate);

                        data.put("Post_remark", postRemarkEdiText.getText().toString());


                        new sendRequestAppt().execute("Appt", "Follow up");
                    }

                    if (eventType == 2) {

                        Log.i("eventtysdfspe", String.valueOf(eventType));
                        String Date = intent.getStringExtra("Date");
                        String addedBy = intent.getStringExtra("added_by");
                        String remark = intent.getStringExtra("remark");
                        String flwId = intent.getStringExtra("flw_id");
                        String staffId = intent.getStringExtra("staff_id");
                        String companyId = intent.getStringExtra("company_id");
                        String person = intent.getStringExtra("person");
                        String finalStatus = intent.getStringExtra("final_status");
                        String callLoopId = intent.getStringExtra("call_loop_id");
                        String status = intent.getStringExtra("status");
                        String followupDate = intent.getStringExtra("followup_date");
                        String postRemark = intent.getStringExtra("post_remark");
                        String lastCallDate = intent.getStringExtra("lastcalldate");


                        data.put("flw_id", flwId);
                        data.put("title", titleField.getText().toString());
                        data.put("staff_id", staffId);
                        data.put("company_id", companyId);
                        data.put("call_loop_id", callLoopId);
                        data.put("remark", remark);
                        data.put("trainer_id", trainerIDField.getText().toString());
                        data.put("trainer_cnf", trainerCNFField.getText().toString());
                        data.put("trainers", trainersField.getText().toString());
                        data.put("trainer_email", trainerEmailField.getText().toString());
                        data.put("trainer_dates", String.valueOf(flw_dateYearFinal) + "-" + String.valueOf(flw_dateMonthFinal) + "-" + String.valueOf(flw_dateDayFinal));

                        data.put("trainer_fees", trainerFeesField.getText().toString());
                        data.put("todate", String.valueOf(toYearFinal) + "-" + String.valueOf(toMonthFinal) + "-" + String.valueOf(toDayFinal));

                        data.put("fromdate", String.valueOf(fromYearFinal) + "-" + String.valueOf(fromMonthFinal) + "-" + String.valueOf(fromDayFinal));
                        Log.i("fromdate", data.get("fromdate"));
                        data.put("company_person", person);
                        data.put("Post_remark", postRemarkEdiText.getText().toString());
                        data.put("final_status", "program");
                        data.put("approval", "approved");
                        data.put("fees", feesField.getText().toString());
                        Log.i("fees", data.get("fees"));
                        data.put("paid", paidField.getText().toString());
                        Log.i("paid", data.get("paid"));
                        data.put("due", String.valueOf(Integer.parseInt(feesField.getText().toString()) - Integer.parseInt(paidField.getText().toString())));
                        Log.i("due", data.get("due"));
                        data.put("t_fee", t_feesField.getText().toString());
                        Log.i("t_fee", data.get("t_fee"));
                        data.put("t_paid", t_paidField.getText().toString());
                        Log.i("t_paid", data.get("t_paid"));
                        data.put("t_due", String.valueOf(Integer.parseInt(t_feesField.getText().toString()) - Integer.parseInt(t_paidField.getText().toString())));
                        Log.i("t_due", data.get("t_due"));
                        data.put("t_paid_on", String.valueOf(t_paidYearFinal) + "-" + String.valueOf(t_paidMonthFinal) + "-" + String.valueOf(t_paidDayFinal));
                        Log.i("t_paid_on", String.valueOf(t_paidYearFinal) + String.valueOf(t_paidMonthFinal) + String.valueOf(t_paidDayFinal));
                        data.put("added_by", addedBy);
                        data.put("location", locationField.getText().toString());
                        data.put("invoice_id", invoiceField.getText().toString());
                        data.put("lastcalldate", formattedDate);
                        Log.i("REACHER", "BEFORE");
                        new sendRequestProg().execute("prog", "Follow up");

                    }

                }


                if (tag.equalsIgnoreCase("Appt")) {
                    String app_id, staff_id, company_id, call_loop_id, date, remark, fromDate, toDate, company_person, Post_remark, status, final_status,
                            approval, trainer_id, trainer_cnf, trainers, trainer_email, addedBy, location;

                    //EVENT TYPE IS APPT
                    if (eventType == 0) {


                        Log.i("taagg", tag);
                        app_id = intent.getStringExtra("app_id");
                        staff_id = intent.getStringExtra("staff_id");
                        company_id = intent.getStringExtra("company_id");
                        call_loop_id = intent.getStringExtra("call_loop_id");
                        date = intent.getStringExtra("date");
                        remark = intent.getStringExtra("remark");
                        fromDate = intent.getStringExtra("fromdate");
                        toDate = intent.getStringExtra("todate");
                        company_person = intent.getStringExtra("company_person");
                        Post_remark = intent.getStringExtra("Post_remark");
                        status = intent.getStringExtra("status");
                        trainer_id = intent.getStringExtra("trainer_id");
                        trainer_cnf = intent.getStringExtra("trainer_cnf");
                        trainer_email = intent.getStringExtra("trainer_email");
                        trainers = intent.getStringExtra("trainers");
                        location = intent.getStringExtra("location");
                        addedBy = intent.getStringExtra("added_by");

                        data.put("app_id", app_id);
                        data.put("staff_id", staff_id);
                        data.put("company_id", company_id);
                        data.put("call_loop_id", call_loop_id);
                       // data.put("remark", remark);
                        data.put("todate", String.valueOf(toYearFinal) + "-" + String.valueOf(toMonthFinal) + "-" + String.valueOf(toDayFinal));
                        data.put("fromdate", String.valueOf(fromYearFinal) + "-" + String.valueOf(fromMonthFinal) + "-" + String.valueOf(fromDayFinal));
                        data.put("company_person", company_person);
                        data.put("added_by", addedBy);
                        data.put("final_status", "appointment");
                        data.put("approval", "approved");
                        data.put("trainer_id", trainerIDField.getText().toString());
                        data.put("trainer_cnf", trainerCNFField.getText().toString());
                        data.put("trainers", trainersField.getText().toString());
                        data.put("trainer_email", trainerEmailField.getText().toString());
                        data.put("location", locationField.getText().toString());
                        data.put("lastcalldate", formattedDate);
                        data.put("Post_remark", postRemarkEdiText.getText().toString());

                        new sendRequestAppt().execute("appt", "Appointment");





                    }

                    if(eventType == 1){
                        app_id = intent.getStringExtra("app_id");
                        staff_id = intent.getStringExtra("staff_id");
                        company_id = intent.getStringExtra("company_id");
                        call_loop_id = intent.getStringExtra("call_loop_id");
                        date = intent.getStringExtra("date");
                        remark = intent.getStringExtra("remark");
                        fromDate = intent.getStringExtra("fromdate");
                        toDate = intent.getStringExtra("todate");
                        company_person = intent.getStringExtra("company_person");
                        Post_remark = intent.getStringExtra("Post_remark");
                        status = intent.getStringExtra("status");
                        trainer_id = intent.getStringExtra("trainer_id");
                        trainer_cnf = intent.getStringExtra("trainer_cnf");
                        trainer_email = intent.getStringExtra("trainer_email");
                        trainers = intent.getStringExtra("trainers");
                        location = intent.getStringExtra("location");
                        addedBy = intent.getStringExtra("added_by");


                        data.put("app_id", app_id);
                        data.put("staff_id", staff_id);
                        data.put("company_id", company_id);
                        data.put("call_loop_id", call_loop_id);
                        //data.put("remark", remark);
                        Log.i("date", String.valueOf(flw_dateYearFinal) + String.valueOf(flw_dateDayFinal));
                        Log.i("dsdfate", String.valueOf(toDayFinal) + String.valueOf(toYearFinal));
                        data.put("followup_date", String.valueOf(flw_dateYearFinal) + "-" + String.valueOf(flw_dateMonthFinal) + "-" + String.valueOf(flw_dateDayFinal));
                        data.put("company_person", company_person);
                        data.put("added_by", addedBy);

                        data.put("lastcalldate", formattedDate);
                        data.put("Post_remark", postRemarkEdiText.getText().toString());
                        data.put("final_status", "follow-up");

                        new sendRequestFollow().execute("Follow up", "appointment");

                    }

                    if (eventType == 2){
                        app_id = intent.getStringExtra("app_id");
                        staff_id = intent.getStringExtra("staff_id");
                        company_id = intent.getStringExtra("company_id");
                        call_loop_id = intent.getStringExtra("call_loop_id");
                        date = intent.getStringExtra("date");
                        remark = intent.getStringExtra("remark");
                        fromDate = intent.getStringExtra("fromdate");
                        toDate = intent.getStringExtra("todate");
                        company_person = intent.getStringExtra("company_person");
                        Post_remark = intent.getStringExtra("Post_remark");
                        status = intent.getStringExtra("status");
                        trainer_id = intent.getStringExtra("trainer_id");
                        trainer_cnf = intent.getStringExtra("trainer_cnf");
                        trainer_email = intent.getStringExtra("trainer_email");
                        trainers = intent.getStringExtra("trainers");
                        location = intent.getStringExtra("location");
                        addedBy = intent.getStringExtra("added_by");


                        data.put("app_id", app_id);
                        data.put("title", titleField.getText().toString());
                        data.put("staff_id", staff_id);
                        data.put("company_id", company_id);
                        data.put("call_loop_id", call_loop_id);
                        //data.put("remark", remark);
                        data.put("trainer_id", trainerIDField.getText().toString());
                        data.put("trainer_cnf", trainerCNFField.getText().toString());
                        data.put("trainers", trainersField.getText().toString());
                        data.put("trainer_email", trainerEmailField.getText().toString());
                        data.put("trainer_dates", String.valueOf(flw_dateYearFinal) + "-" + String.valueOf(flw_dateMonthFinal) + "-" + String.valueOf(flw_dateDayFinal));

                        data.put("trainer_fees", trainerFeesField.getText().toString());
                        data.put("todate", String.valueOf(toYearFinal) + "-" + String.valueOf(toMonthFinal) + "-" + String.valueOf(toDayFinal));

                        data.put("fromdate", String.valueOf(fromYearFinal) + "-" + String.valueOf(fromMonthFinal) + "-" + String.valueOf(fromDayFinal));
                        Log.i("fromdate", data.get("fromdate"));
                        data.put("company_person", company_person);
                        data.put("Post_remark", postRemarkEdiText.getText().toString());
                        data.put("final_status", "program");
                        data.put("approval", "approved");
                        data.put("fees", feesField.getText().toString());
                        Log.i("fees", data.get("fees"));
                        data.put("paid", paidField.getText().toString());
                        Log.i("paid", data.get("paid"));
                        data.put("due", String.valueOf(Integer.parseInt(feesField.getText().toString()) - Integer.parseInt(paidField.getText().toString())));
                        Log.i("due", data.get("due"));
                        data.put("t_fee", t_feesField.getText().toString());
                        Log.i("t_fee", data.get("t_fee"));
                        data.put("t_paid", t_paidField.getText().toString());
                        Log.i("t_paid", data.get("t_paid"));
                        data.put("t_due", String.valueOf(Integer.parseInt(t_feesField.getText().toString()) - Integer.parseInt(t_paidField.getText().toString())));
                        Log.i("t_due", data.get("t_due"));
                        data.put("t_paid_on", String.valueOf(t_paidYearFinal) + "-" + String.valueOf(t_paidMonthFinal) + "-" + String.valueOf(t_paidDayFinal));
                        Log.i("t_paid_on", String.valueOf(t_paidYearFinal) + String.valueOf(t_paidMonthFinal) + String.valueOf(t_paidDayFinal));
                        data.put("added_by", addedBy);
                        data.put("location", locationField.getText().toString());
                        data.put("invoice_id", invoiceField.getText().toString());
                        data.put("lastcalldate", formattedDate);
                        Log.i("REACHER", "BEFORE");
                        new sendRequestProg().execute("prog", "Appointment");

                    }
                }
               /*if(tag.equalsIgnoreCase("Prog"))
                   new sendRequestFollow().execute(eventType);*/
            }
        });


        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        getWindow().setLayout((int) (displayMetrics.widthPixels * .9777), (int) (displayMetrics.heightPixels * .95));
    }

    //SETTING THE DATE AND TIME DIALOG
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {


        //CHECKING WHICH DATE, TIME BUTTON WAS PRESSED
        if (dateSetter.equalsIgnoreCase("from")) {
            Log.i("tertert0", "1");
            Log.i("tertert0", "2");
            fromYearFinal = year;
            fromMonthFinal = month + 1;
            fromDayFinal = dayOfMonth;

            Calendar calendar = Calendar.getInstance();

            fromHour = calendar.get(Calendar.HOUR_OF_DAY);
            fromMin = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(LongPressPopUpActivity.this, LongPressPopUpActivity.this,
                    fromHour, fromMin, DateFormat.is24HourFormat(this));
            Log.i("tertert0", "3");
            timePickerDialog.show();

        }
        if (dateSetter.equalsIgnoreCase("to")) {
            toYearFinal = year;
            toMonthFinal = month + 1;
            toDayFinal = dayOfMonth;

            Calendar calendar = Calendar.getInstance();

            toHour = calendar.get(Calendar.HOUR_OF_DAY);
            toMin = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(LongPressPopUpActivity.this, LongPressPopUpActivity.this,
                    toHour, toMin, DateFormat.is24HourFormat(this));
            timePickerDialog.show();
        }
        if (dateSetter.equalsIgnoreCase("follow up")) {
            flw_dateDayFinal = dayOfMonth;
            flw_dateMonthFinal = month + 1;
            flw_dateYearFinal = year;

            Calendar calendar = Calendar.getInstance();

            //toHour = calendar.get(Calendar.HOUR_OF_DAY);
            //toMin = calendar.get(Calendar.MINUTE);

            /*TimePickerDialog timePickerDialog = new TimePickerDialog(LongPressPopUpActivity.this, LongPressPopUpActivity.this,
                    toHour, toMin, DateFormat.is24HourFormat(this));
            timePickerDialog.show();*/
        }

        if (dateSetter.equalsIgnoreCase("t_paid_on")) {
            t_paidDayFinal = dayOfMonth;
            t_paidMonthFinal = month + 1;
            t_paidYearFinal = year;

            Calendar calendar = Calendar.getInstance();

            //toHour = calendar.get(Calendar.HOUR_OF_DAY);
            //toMin = calendar.get(Calendar.MINUTE);

            /*TimePickerDialog timePickerDialog = new TimePickerDialog(LongPressPopUpActivity.this, LongPressPopUpActivity.this,
                    toHour, toMin, DateFormat.is24HourFormat(this));
            timePickerDialog.show();*/
        }
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Log.i("tertert0", "4");

        if (dateSetter.equalsIgnoreCase("from")) {
            Log.i("tertert0", "5");
            fromHourFinal = hourOfDay;
            fromMinFinal = minute;

            Log.i("tertert0", "6");
            Log.i("TIME DATE", String.valueOf(fromYearFinal) + String.valueOf(fromMonthFinal) + String.valueOf(fromDayFinal)
                    + String.valueOf(fromMinFinal));
        }
        if (dateSetter.equalsIgnoreCase("to")) {
            Log.i("tertert0", "9");
            toHourFinal = hourOfDay;
            toMinFinal = minute;

            Log.i("tertert0", "6");
            Log.i("TIME DATE", String.valueOf(toYearFinal) + String.valueOf(toMonthFinal) + String.valueOf(toDayFinal)
                    + String.valueOf(toMinFinal));
        }

    }


    //---------------------------  THE POSTING CLASSES ---------------------------///


    //FOR FOLLOW UP
    class sendRequestFollow extends AsyncTask<String, String, String> {

        ProgressDialog loading;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = ProgressDialog.show(LongPressPopUpActivity.this, "", "Sending Information, Please wait..", true, false);
        }

        @Override
        protected String doInBackground(String... arg0) {


            PostingClass ruc = new PostingClass();

            Log.i("EventType flw", arg0[0]);

            /*if (arg0[0].equalsIgnoreCase("Appt")) {

                String result = ruc.sendPostRequest("URL HERE", data);

                return result;
            }*/
            if (arg0[0].equalsIgnoreCase("Follow Up")) {

                Log.i("sdfsdf ", "reached follow");
                if (arg0[1].equalsIgnoreCase("Follow up")) {

                    String result = ruc.sendPostRequest("http://bijatraining.000webhostapp.com/followrequest/followup_push.php", data);

                    return result;
                }

                if (arg0[1].equalsIgnoreCase("appointment")) {

                    Log.i("new line", "reached");

                    String result = ruc.sendPostRequest("http://bijatraining.000webhostapp.com/appointmentrequest/followup_push.php", data);

                    return result;
                }
                else{
                    return  "Error";
                }
            }
           /* if(arg0[0].equalsIgnoreCase("Prog")) {

                String result = ruc.sendPostRequest("URL HERE", data);

                return result;
            }*/
            else
                return "Error";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            Log.i("res of f to f", result);

            loading.dismiss();

            if (result.equalsIgnoreCase("success")) {


                Toast.makeText(getApplicationContext(), "Added successfully", Toast.LENGTH_SHORT).show();

                finish();


            }
            else {
                Toast.makeText(getApplicationContext(), "Something went wrong, enter the values correcty or the database is down", Toast.LENGTH_LONG).show();
            }

        }
    }


    //FOR APPOINTMENT
    class sendRequestAppt extends AsyncTask<String, String, String> {

        ProgressDialog loading;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = ProgressDialog.show(LongPressPopUpActivity.this, "", "Sending Information, Please wait..", true, false);
        }

        @Override
        protected String doInBackground(String... arg0) {


            PostingClass ruc = new PostingClass();

            Log.i("EventType appt", arg0[0]);

            if (arg0[0].equalsIgnoreCase("Appt")) {
                if (arg0[1].equalsIgnoreCase("Follow up")) {

                    String result = ruc.sendPostRequest("http://bijatraining.000webhostapp.com/followrequest/appointment_push.php ", data);

                    return result;
                }
                Log.i("Appt send", "reached");
                if (arg0[1].equalsIgnoreCase("Appointment")) {
                    String result = ruc.sendPostRequest("http://bijatraining.000webhostapp.com/appointmentrequest/appointment_push.php ", data);
                    Log.i("Appt send", "reached to class");
                    return result;
                } else {

                    Log.i("Appt send", "reachewerfwerd");
                    return "error";
                }
            }
            /*if(arg0[0].equalsIgnoreCase("Follow Up")) {

                String result = ruc.sendPostRequest("URL HERE", data);
                data.clear();
                return result;
            }
            if(arg0[0].equalsIgnoreCase("Prog")) {

                String result = ruc.sendPostRequest("URL HERE", data);

                return result;
            }*/
            else
                return "Error";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            Log.i("SentAPPT", result);

            loading.dismiss();

            if (result.equalsIgnoreCase("success")) {


                Toast.makeText(getApplicationContext(), "Added successfully", Toast.LENGTH_SHORT).show();

                finish();


            }
            else {
                Toast.makeText(getApplicationContext(), "Something went wrong, enter the values correcty or the database is down", Toast.LENGTH_LONG).show();
            }

        }
    }


    //FOR PROGRAM
    class sendRequestProg extends AsyncTask<String, String, String> {

        ProgressDialog loading;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = ProgressDialog.show(LongPressPopUpActivity.this, "", "Sending Information, Please wait..", true, false);
        }

        @Override
        protected String doInBackground(String... arg0) {


            PostingClass ruc = new PostingClass();

            Log.i("EventType", arg0[0]);

            if (arg0[0].equalsIgnoreCase("Appt")) {
                if (arg0[1].equalsIgnoreCase("Follow up")) {

                    String result = ruc.sendPostRequest("URL HERE", data);

                    return result;
                }
                else
                    return "Error";
            }
            if (arg0[0].equalsIgnoreCase("Follow Up")) {

                String result = ruc.sendPostRequest("URL HERE", data);

                return result;
            }
            if (arg0[0].equalsIgnoreCase("prog")) {

                if (arg0[1].equalsIgnoreCase("Follow up")) {

                    Log.i("REACHER", "BEFORE 12");

                    String result = ruc.sendPostRequest("http://bijatraining.000webhostapp.com/followrequest/program_push.php", data);

                    return result;
                }
                if (arg0[1].equalsIgnoreCase("Appointment")) {
                    String result = ruc.sendPostRequest("http://bijatraining.000webhostapp.com/appointmentrequest/program_push.php", data);

                    return result;
                }
                else
                    return "error";
            } else
                return "Error";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            Log.i("PROGRAM RET", result);
            loading.dismiss();

            if (result.equalsIgnoreCase("success")) {


                Toast.makeText(getApplicationContext(), "Added successfully", Toast.LENGTH_SHORT).show();

                finish();


            }
            else {
                Toast.makeText(getApplicationContext(), "Something went wrong, enter the values correcty or the database is down", Toast.LENGTH_LONG).show();
            }
        }
    }
}
