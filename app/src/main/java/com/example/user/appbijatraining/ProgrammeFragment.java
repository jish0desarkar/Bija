package com.example.user.appbijatraining;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProgrammeFragment extends Fragment {

    NavigationView navigationView;
    static ArrayList<ProgrammeList> programmeLists;
    static ListView listView;
    TextView staff_id;
    String prg_id, title, trainer, addedBy;
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


        HashMap<String, String> hashMap = new HashMap<>();
        PostingClass postingClass = new PostingClass();
        hashMap.put("staff_id", "73");
        hashMap.put("date", "2071-04-09");

        new FetchProgram().execute("73", "2017-04-09");

        programmeLists = new ArrayList<>();

        programmeLists.add(new ProgrammeList("dummy", "dummy2", "dummy3", "dummy4"));

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

      /*  listView = getActivity().findViewById(R.id.program_list);

        ProgramListAdapter adapter = new ProgramListAdapter(getContext(), R.layout.custom_programme_listview, programmeLists);

        listView.setAdapter(adapter);*/
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
            data.put("staff_id", arg0[0]);
            data.put("date", arg0[1]);

            PostingClass ruc = new PostingClass();

            String result = ruc.sendPostRequest("https://bijatraining.000webhostapp.com/program_ret.php", data);

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
                    Log.i("JSON OBJ", json.getString("prg_id"));


                    prg_id = json.getString("prg_id");
                    title = json.getString("title");
                    trainer = json.getString("trainers");
                    addedBy = json.getString("added_by");



                    loading.dismiss();

                    programmeLists.add(new ProgrammeList(prg_id, title, trainer, addedBy));

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