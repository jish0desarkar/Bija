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

public class HistoryListAdapter extends ArrayAdapter {

    Context context;
    String text;
    int resource;
    List<HistoryList> historyLists;
    Dialog dialog;



    public HistoryListAdapter(Context context, int resource, List<HistoryList> historyLists) {
        super(context, resource, historyLists);
        this.context = context;
        this.resource = resource;
        this.historyLists = historyLists;



    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("ViewHolder") View view = inflater.inflate(R.layout.custom_history_listitem, null);
        TextView histtext = view.findViewById(R.id.hist_text);
        Button detailsButton = view.findViewById(R.id.Details_button);
        detailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "Details about this event";
                Intent intent = new Intent(getContext(), PopUpActivity.class);
                Bundle bundle =  new Bundle();
                bundle.putString("Text", text);
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });

        HistoryList historyList = historyLists.get(position);
        histtext.setText(historyList.getText());


        return view;
    }








}
