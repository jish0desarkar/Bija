package com.example.user.appbijatraining;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static com.example.user.appbijatraining.NotificationsActivity.notificationLists;

public class AppointmentsFragment extends Fragment {

    static List<AppointmentList> appointmentLists;
    static ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_appointments, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        appointmentLists = new ArrayList<>();

        appointmentLists.add(new AppointmentList("Dummy notification 1"));
        appointmentLists.add(new AppointmentList("Dummy notification 1"));
        appointmentLists.add(new AppointmentList("Dummy notification 1"));
        appointmentLists.add(new AppointmentList("Dummy notification 1"));
        appointmentLists.add(new AppointmentList("Dummy notification 1"));
        appointmentLists.add(new AppointmentList("Dummy notification 1"));
        appointmentLists.add(new AppointmentList("Dummy notification 1"));
        appointmentLists.add(new AppointmentList("Dummy notification 1"));
        appointmentLists.add(new AppointmentList("Dummy notification 1"));
        appointmentLists.add(new AppointmentList("Dummy notification 1"));
        appointmentLists.add(new AppointmentList("Dummy notification 1"));
        appointmentLists.add(new AppointmentList("Dummy notification 1"));



        listView = getActivity().findViewById(R.id.appt_listview);

        AppointmentListAdapter adapter = new AppointmentListAdapter(getContext(), R.layout.custom_appointment_list_item, appointmentLists);

        listView.setAdapter(adapter);
    }
}


