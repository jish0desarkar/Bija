package com.example.user.appbijatraining;

import android.app.ProgressDialog;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class FollowUpsFragment extends Fragment {

    NavigationView navigationView;
    static List<FollowUpListy> followUpListies;
    static ListView listView;
    String[] flw_id = new String[30];
    String[] staff_id = new String[30];
    String[] company_id = new String[30];
    String[] call_loop_id = new String[30];
    String[] followup_date = new String[30];
    String[] post_remark= new String[30];
    String[] lastcalldate = new String[30];
    String[] title = new String[30];
    String[]  trainer = new String[30];
    String[] addedBy= new String[30];
    String[] date = new String[30];
    String[] remark= new String[30];
    String[] status = new String[30];
    String[] trainerEmai = new String[30];
    String[] trainerDate = new String[30];
    String[] fromDate = new String[30];
    String[] toDate = new String[30];
    String[] companyPerson = new String[30];

    String[] finalStatus = new String[30];
    String[] approval = new String[30];
    String[] fees = new String[30];
    String[] paid= new String[30];
    String[] due =  new String[30];
    String[] paidOn= new String[30];
    String[] location = new String[30];;

    int countJSON, pos;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_followups, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        navigationView = getActivity().findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.flwup_nav_btn);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Follow Up");

        followUpListies = new ArrayList<>();

        Detail_Extracter detail_extracter = new Detail_Extracter(getContext());

        Calendar calendar = Calendar.getInstance();

        Date dateclass = calendar.getTime();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String strDate = simpleDateFormat.format(dateclass);

        new FetchProgram().execute(detail_extracter.getId(), strDate);

        listView = getActivity().findViewById(R.id.followup_list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                pos = position;

                Intent intent = new Intent(getContext(), PopUpActivity.class);
                intent.putExtra("intent", "Follow Up");
                intent.putExtra("Date", date[pos]);

                Log.i("POS OF DATE", date[pos]);
                Log.i("POS REMARK", remark[pos]);
                Log.i("COMPDAF", companyPerson[pos]);
                intent.putExtra("added_by", addedBy[pos]);
                intent.putExtra("remark", remark[pos]);
                intent.putExtra("flw_id", flw_id[pos]);
                intent.putExtra("staff_id", staff_id[pos]);
                intent.putExtra("company_id", company_id[pos]);
                intent.putExtra("person", companyPerson[pos]);
                intent.putExtra("final_status", finalStatus[pos]);
                intent.putExtra("call_loop_id", call_loop_id[pos]);
                intent.putExtra("status", status[pos]);
                intent.putExtra("followup_date", followup_date[pos]);
                intent.putExtra("post_remark", post_remark[pos]);
                intent.putExtra("lastcalldate", lastcalldate[pos]);

                startActivity(intent);


            }
        });

        listView.setLongClickable(true);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), LongPressPopUpActivity.class);
                pos = position;
                intent.putExtra("tag", "Follow Up");
                intent.putExtra("Date", date[pos]);
                intent.putExtra("added_by", addedBy[pos]);
                intent.putExtra("remark", remark[pos]);
                intent.putExtra("flw_id", flw_id[pos]);
                intent.putExtra("staff_id", staff_id[pos]);
                intent.putExtra("company_id", company_id[pos]);
                intent.putExtra("person", companyPerson[pos]);
                intent.putExtra("final_status", finalStatus[pos]);
                intent.putExtra("call_loop_id", call_loop_id[pos]);
                intent.putExtra("status", status[pos]);
                intent.putExtra("followup_date", followup_date[pos]);
                intent.putExtra("post_remark", post_remark[pos]);
                intent.putExtra("lastcalldate", lastcalldate[pos]);
                startActivity(intent);
                return true;
            }
        });






        super.onViewCreated(view, savedInstanceState);
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

            String result = ruc.sendPostRequest("https://bijatraining.000webhostapp.com/followup_ret.php", data);

            return result;
        }

        @Override
        protected void onPostExecute(String result) {



            super.onPostExecute(result);


            loading.dismiss();

            if(result.equalsIgnoreCase("No pending available")){
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("NO PENDING AVAILABLE")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // FIRE ZE MISSILES!
                            }
                        });
                builder.create().show();
                // Create the AlertDialog object and return i
            }

            else {
                Log.i("JSON", result);
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    for (int i = 0; i < jsonArray.length(); i++) {

                        countJSON++;

                        JSONObject json = jsonArray.getJSONObject(i);
                        Log.i("JSON OBJ", json.getString("flw_id"));
                        // String flw_id, staff_id, company_id,call_loop_id, followup_date, post_remark, lastcalldate, title, trainer, addedBy, date, remark, status, trainerEmai, trainerDate, fromDate, toDate, companyPerson,
                        //  finalStatus, approval, fees, paid, due, paidOn, location;
                        flw_id[i] = json.getString("flw_id");
                        addedBy[i] = json.getString("added_by");
                        staff_id[i] = json.getString("staff_id");
                        company_id[i] = json.getString("company_id");
                        call_loop_id[i] = json.getString("call_loop_id");
                        followup_date[i] = json.getString("followup_date");
                        remark[i] = json.getString("remark");
                        status[i] = json.getString("status");
                        post_remark[i] = json.getString("Post_remark");
                        lastcalldate[i] = json.getString("lastcalldate");
                        date[i] = json.getString("date");
                        companyPerson[i] = json.getString("company_person");
                        finalStatus[i] = json.getString("final_status");
                   /* fees = json.getString("fees");
                    paid = json.getString("paid");
                    due = json.getString("due");
                    location = json.getString("location");
                    paidOn = json.getString("t_paid_on");*/


                        loading.dismiss();

                        followUpListies.add(new FollowUpListy(flw_id[i], date[i], addedBy[i], finalStatus[i]));

                        listView = getActivity().findViewById(R.id.followup_list);

                        FollowUpListAdapter adapter = new FollowUpListAdapter(getContext(), R.layout.custom_programme_listview, followUpListies);

                        listView.setAdapter(adapter);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
