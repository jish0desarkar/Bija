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

public class FollowUpsFragment extends Fragment {

    NavigationView navigationView;
    static List<FollowUpListy> followUpListies;
    static ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_followups, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        navigationView = getActivity().findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.flwup_nav_btn);


        followUpListies = new ArrayList<>();

        followUpListies.add(new FollowUpListy("Dummy notification 1"));
        followUpListies.add(new FollowUpListy("Dummy notification 1"));
        followUpListies.add(new FollowUpListy("Dummy notification 1"));
        followUpListies.add(new FollowUpListy("Dummy notification 1"));
        followUpListies.add(new FollowUpListy("Dummy notification 1"));
        followUpListies.add(new FollowUpListy("Dummy notification 1"));
        followUpListies.add(new FollowUpListy("Dummy notification 1"));
        followUpListies.add(new FollowUpListy("Dummy notification 1"));
        followUpListies.add(new FollowUpListy("Dummy notification 1"));
        followUpListies.add(new FollowUpListy("Dummy notification 1"));
        followUpListies.add(new FollowUpListy("Dummy notification 1"));
        followUpListies.add(new FollowUpListy("Dummy notification 1"));



        listView = getActivity().findViewById(R.id.flw_listview);

        FollowUpListAdapter adapter = new FollowUpListAdapter(getContext(), R.layout.custom_followups_list_item, followUpListies);

        listView.setAdapter(adapter);


        super.onViewCreated(view, savedInstanceState);
    }
}
