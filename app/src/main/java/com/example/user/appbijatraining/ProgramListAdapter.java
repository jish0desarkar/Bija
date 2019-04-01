package com.example.user.appbijatraining;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
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

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        view = inflater.inflate(R.layout.custom_programme_listview, null);
        //TextView prg_idText = view.findViewById(R.id.prg_text);
        TextView prg_idText = view.findViewById(R.id.prg_id);
        TextView titleText = view.findViewById(R.id.title);


        TextView trainerText = view.findViewById(R.id.trainer);


        ProgrammeList programmeList = programmeLists.get(position);
        prg_idText.setText(programmeList.getPrg_id());
        titleText.setText(programmeList.getTitle());
        trainerText.setText(programmeList.getTrainer());


        return view;
    }

         public ProgramListAdapter(Context context, int resource, List<ProgrammeList> programmeLists) {
            super(context, resource, programmeLists);
            this.context = context;
            this.resource = resource;
            this.programmeLists = programmeLists;


    }

}
