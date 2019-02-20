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

import static com.example.user.appbijatraining.NotificationsActivity.notificationLists;

public class AppointmentListAdapter extends ArrayAdapter {


    Context context;
    int resource;
    List<AppointmentList> appoitmentLists;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("ViewHolder") View view = inflater.inflate(R.layout.custom_appointment_list_item, null);
        TextView appointmentText = view.findViewById(R.id.appt_text);
        Button detailsButton = view.findViewById(R.id.Details_button);
        Button historyButton = view.findViewById(R.id.History_button);
        Button statusButton = view.findViewById(R.id.button_status);
        detailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        AppointmentList appointmentList = appoitmentLists.get(position);
        appointmentText.setText(appointmentList.getApptText());


        return view;
    }

         public AppointmentListAdapter(Context context, int resource, List<AppointmentList> appoitmentLists) {
            super(context, resource, appoitmentLists);
            this.context = context;
            this.resource = resource;
            this.appoitmentLists = appoitmentLists;


    }

}
