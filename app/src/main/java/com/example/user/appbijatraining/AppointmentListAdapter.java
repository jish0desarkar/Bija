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

public class AppointmentListAdapter extends ArrayAdapter {


    Context context;
    String text;
    int resource;
    List<AppointmentList> appoitmentLists;
    Dialog dialog;



         public AppointmentListAdapter(Context context, int resource, List<AppointmentList> appoitmentLists) {
            super(context, resource, appoitmentLists);
            this.context = context;
            this.resource = resource;
            this.appoitmentLists = appoitmentLists;



    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("ViewHolder") View view = inflater.inflate(R.layout.custom_appointment_list_item, null);
        TextView appointmentText = view.findViewById(R.id.hist_text);
        Button detailsButton = view.findViewById(R.id.Details_button);
        Button historyButton = view.findViewById(R.id.History_button);
        Button statusButton = view.findViewById(R.id.button_status);
        detailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "Date: 2019-04-06 fees: 25000,paid: 25000";
                Intent intent = new Intent(getContext(), PopUpActivity.class);
                Bundle bundle =  new Bundle();
                bundle.putString("Text", text);
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "Yet to complete";
                Intent intent = new Intent(getContext(), PopUpActivity.class);
                Bundle bundle =  new Bundle();
                bundle.putString("Text", text);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        statusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "Status: Confirmed";
                Intent intent = new Intent(getContext(), PopUpActivity.class);
                Bundle bundle =  new Bundle();
                bundle.putString("Text", text);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        AppointmentList appointmentList = appoitmentLists.get(position);
        appointmentText.setText(appointmentList.getApptText());


        return view;
    }

}
