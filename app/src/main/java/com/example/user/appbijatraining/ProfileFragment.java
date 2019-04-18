package com.example.user.appbijatraining;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileFragment extends Fragment {

    ImageView profilePhoto;
    NavigationView navigationView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        navigationView = getActivity().findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.profile_nav_bt);
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Profile");


    profilePhoto = getActivity().findViewById(R.id.profile_photo);
    TextView emailText = getActivity().findViewById(R.id.textView_email);
        TextView rollText = getActivity().findViewById(R.id.textView_roll);
        TextView nameText = getActivity().findViewById(R.id.name);
        TextView phoneText = getActivity().findViewById(R.id.phone_id);
    StaffDetailDbHelper helper=new StaffDetailDbHelper(getContext());
        Detail_Extracter de=new Detail_Extracter(getContext());

        rollText.setText(de.getRole());
        nameText.setText(de.getName());
        emailText.setText(de.getEmail());
        phoneText.setText(de.getContactno());


    }



}
