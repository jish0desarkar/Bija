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

public class NotificationsFragment extends Fragment {

    List<NotificationList> notificationLists;
    ListView listView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notifications, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        notificationLists = new ArrayList<>();

        notificationLists.add(new NotificationList("Dummy notification 1"));
        notificationLists.add(new NotificationList("Dummy notification 1"));
        notificationLists.add(new NotificationList("Dummy notification 1"));
        notificationLists.add(new NotificationList("Dummy notification 1"));
        notificationLists.add(new NotificationList("Dummy notification 1"));
        notificationLists.add(new NotificationList("Dummy notification 1"));
        notificationLists.add(new NotificationList("Dummy notification 1"));
        notificationLists.add(new NotificationList("Dummy notification 1"));
        notificationLists.add(new NotificationList("Dummy notification 1"));
        notificationLists.add(new NotificationList("Dummy notification 1"));


        listView = getView().findViewById(R.id.listview_notification);

        NotificationListAdapter adapter = new NotificationListAdapter(getContext(), R.layout.custom_notification_list_item, notificationLists);

        listView.setAdapter(adapter);


    }


}
