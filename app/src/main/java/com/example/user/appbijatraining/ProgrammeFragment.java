package com.example.user.appbijatraining;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ProgrammeFragment extends Fragment {

    NavigationView navigationView;
    static List<ProgrammeList> programmeLists;
    static ListView listView;

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


        programmeLists = new ArrayList<>();

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
        programmeLists.add(new ProgrammeList("Dummy notification 1"));
        programmeLists.add(new ProgrammeList("Dummy notification 1"));



        listView = getActivity().findViewById(R.id.program_list);

        ProgramListAdapter adapter = new ProgramListAdapter(getContext(), R.layout.custom_programme_listview, programmeLists);

        listView.setAdapter(adapter);
    }
}
