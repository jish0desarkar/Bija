package com.example.user.appbijatraining;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ProgrammeFragment extends Fragment {

    NavigationView navigationView;
    static ArrayList<ProgrammeList> programmeLists;
    static ListView listView;
    TextView staff_id;
    String[] prg_id = new String[100];
    String[] title = new String[100];
    String[] trainer = new String[100];
    String[] addedBy = new String[100];
    String[] date = new String[100];
    String[] remark = new String[100];
    String[] status = new String[100];
    String[] trainerEmai = new String[100];
    String[] trainerDate = new String[100];
    String[] fromDate = new String[100];
    String[] toDate = new String[100];
    String[] companyPerson = new String[100];
    String[] finalStatus = new String[100];
    String[] approval = new String[100];
    String[] fees = new String[100];
    String[] paidOn = new String[100];
    String[] paid = new String[100];
    String[] due = new String[100];
    String[] location = new String[100];
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        navigationView = getActivity().findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.prog_nav_btn);
        return inflater.inflate(R.layout.fragment_programme, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Programme");

        Detail_Extracter detail_extracter = new Detail_Extracter(getContext());

        Calendar calendar = Calendar.getInstance();

        Date dateclass = calendar.getTime();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String strDate = simpleDateFormat.format(dateclass);

        if (detail_extracter.getRole().equalsIgnoreCase("trainer"))

            new FetchProgram().execute(detail_extracter.getId(), strDate, detail_extracter.getRole());

        if (detail_extracter.getRole().equalsIgnoreCase("staff"))
            new FetchProgram().execute(detail_extracter.getId(), strDate, detail_extracter.getRole());

        programmeLists = new ArrayList<>();


        /*programmeLists.add(new ProgrammeList(prg_id, title, trainer, addedBy));
        Log.i("Prog id", prg_id);
        Log.i("TItle", title);*/

        /*
        programmeLists.add(new ProgrammeList("Dummy notification 1"));
        programmeLists.add(new ProgrammeList("Dummy notification 1"));
        programmeLists.add(new ProgrammeList("Dummy notification 1"));
        programmeLists.add(new ProgrammeList("Dummy notification 1"));
        programmeLists.add(new ProgrammeList("Dummy notification 1"));
        programmeLists.add(new ProgrammeList("Dummy notification 1"));
        programmeLists.add(new ProgrammeList("Dummy notification 1"));
        programmeLists.add(new ProgrammeList("Dummy notification 1"));
        programmeLists.add(new ProgrammeList("Dummy notification 1"));
        programmeLists.add(new ProgrammeList("Dummy notification 1"));
        programmeLists.add(new ProgrammeList("Dummy notification 1"));
        programmeLists.add(new ProgrammeList("Dummy notification 1"));
        programmeLists.add(new ProgrammeList("Dummy notification 1"));
`           */

       listView = getActivity().findViewById(R.id.program_list);

        ProgramListAdapter adapter = new ProgramListAdapter(getContext(), R.layout.custom_programme_listview, programmeLists);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                Intent intent = new Intent(getContext(), PopUpActivity.class);
                intent.putExtra("intent", "prog");
                intent.putExtra("Date", date[position]);
                intent.putExtra("remark", remark[position]);
                intent.putExtra("trainer_email", trainerEmai[position]);
                intent.putExtra("from", fromDate[position]);
                intent.putExtra("to", toDate[position]);
                intent.putExtra("person", companyPerson[position]);
                intent.putExtra("final_status", finalStatus[position]);
                intent.putExtra("approval", approval[position]);
                intent.putExtra("status", status[position]);
                intent.putExtra("fees", fees[position]);
                intent.putExtra("due", due[position]);
                intent.putExtra("paid_on", paidOn[position]);
                intent.putExtra("location", location[position]);
                intent.putExtra("paid", paid[position]);

                startActivity(intent);


            }
        });
    }

    class FetchProgram extends AsyncTask<String, String, String> {

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
            Log.i("JSON", result);
            loading.dismiss();

            if (result.equalsIgnoreCase("No pending available")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("NO PENDING AVAILABLE")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // FIRE ZE MISSILES!
                            }
                        });
                builder.create().show();
                // Create the AlertDialog object and return i
            } else {

                try {
                    JSONArray jsonArray = new JSONArray(result);
                    for (int i = 0; i < jsonArray.length(); i++) {


                        JSONObject json = jsonArray.getJSONObject(i);
                        Log.i("JSON OBJ", json.getString("prg_id"));


                        prg_id[i] = json.getString("prg_id");
                        title[i] = json.getString("title");
                        trainer[i] = json.getString("trainers");
                        addedBy[i] = json.getString("added_by");
                        date[i] = json.getString("date");
                        remark[i] = json.getString("remark");
                        status[i] = json.getString("trainer_cnf");
                        ;
                        trainerEmai[i] = json.getString("trainer_email");
                        fromDate[i] = json.getString("fromdate");
                        toDate[i] = json.getString("todate");
                        companyPerson[i] = json.getString("company_person");
                        finalStatus[i] = json.getString("final_status");
                        approval[i] = json.getString("approval");
                        fees[i] = json.getString("fees");
                        paid[i] = json.getString("paid");
                        due[i] = json.getString("due");
                        location[i] = json.getString("location");
                        paidOn[i] = json.getString("t_paid_on");


                        loading.dismiss();

                        programmeLists.add(new ProgrammeList(prg_id[i], title[i], trainer[i], addedBy[i]));

                        listView = getActivity().findViewById(R.id.program_list);

                        ProgramListAdapter adapter = new ProgramListAdapter(getContext(), R.layout.custom_programme_listview, programmeLists);

                        listView.setAdapter(adapter);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        }


    }