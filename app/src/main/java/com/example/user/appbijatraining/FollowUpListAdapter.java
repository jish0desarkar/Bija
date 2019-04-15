package com.example.user.appbijatraining;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class FollowUpListAdapter extends ArrayAdapter {


    Context context;
    String text;
    int resource;
    List<FollowUpListy> followUpListies;
    Dialog dialog;


    public FollowUpListAdapter(Context context, int resource, List<FollowUpListy> followUpListies) {
        super(context, resource, followUpListies);
        this.context = context;
        this.resource = resource;
        this.followUpListies = followUpListies;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("ViewHolder") View view = inflater.inflate(R.layout.custom_followups_list_item, null);

        TextView prg_idText = view.findViewById(R.id.prg_id);
        TextView titleText = view.findViewById(R.id.title);


        TextView trainerText = view.findViewById(R.id.trainer);


        FollowUpListy followUpListy = followUpListies.get(position);
        String prgid = followUpListy.getPrg_id() + ".";
        prg_idText.setText(prgid);
        titleText.setText(followUpListy.getTitle());
        trainerText.setText(followUpListy.getTrainer());

        return view;
    }
}



