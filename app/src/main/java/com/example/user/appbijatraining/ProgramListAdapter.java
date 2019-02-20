package com.example.user.appbijatraining;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ProgramListAdapter extends ArrayAdapter {


    Context context;
    int resource;
    List<ProgrammeList> programmeLists;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("ViewHolder") View view = inflater.inflate(R.layout.custom_programme_listview, null);
        TextView appointmentText = view.findViewById(R.id.programme_listText);
        Button detailsButton = view.findViewById(R.id.programme_details_button);
        Button historyButton = view.findViewById(R.id.programme_history_button);
        detailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ProgrammeList programmeList = programmeLists.get(position);
        appointmentText.setText(programmeList.getprogText());


        return view;
    }

         public ProgramListAdapter(Context context, int resource, List<ProgrammeList> programmeLists) {
            super(context, resource, programmeLists);
            this.context = context;
            this.resource = resource;
            this.programmeLists = programmeLists;


    }

}
