package com.example.user.appbijatraining;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AppointmentsFragment extends Fragment {
    NavigationView navigationView;
    static List<AppointmentList> appointmentLists;
    static ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        navigationView = getActivity().findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.appt_nav_bt);
        return inflater.inflate(R.layout.fragment_appointments, container, false);



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


       Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Appointments");

        appointmentLists = new ArrayList<>();

        appointmentLists.add(new AppointmentList("ID: 1  Remark: Appointment Fixed For Merced"));
        appointmentLists.add(new AppointmentList("ID: 2  Remark: Program Fixed For Ashok Layland"));


        listView = getActivity().findViewById(R.id.appt_listview);

        AppointmentListAdapter adapter = new AppointmentListAdapter(getContext(), R.layout.custom_appointment_list_item, appointmentLists);

        listView.setAdapter(adapter);

    }
}


