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
        TextView flwText = view.findViewById(R.id.hist_text);
        Button detailsButton = view.findViewById(R.id.Details_button);
        Button historyButton = view.findViewById(R.id.status_btn);
        detailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "Details about this event";
                Intent intent = new Intent(getContext(), PopUpActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Text", text);
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "Details about this event";
                Intent intent = new Intent(getContext(), PopUpActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Text", text);
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });

        FollowUpListy followUpListy = followUpListies.get(position);
        flwText.setText(followUpListy.getFlwText());


        return view;
    }
}



