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

import org.w3c.dom.Text;

import java.util.List;

public class NotificationListAdapter extends ArrayAdapter {


    Context context;
    int resource;
    List<NotificationList> notificationLists;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("ViewHolder") View view = inflater.inflate(R.layout.custom_notification_list_item, null);
        TextView notificationText = view.findViewById(R.id.notification_listText);
        Button deleteButton = view.findViewById(R.id.notification_delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        NotificationList notificationList = notificationLists.get(position);
        notificationText.setText(notificationList.getNotificatonText());


        return view;
    }

    public NotificationListAdapter(Context context, int resource, List<NotificationList> notificationLists) {
        super(context, resource, notificationLists);
        this.context = context;
        this.resource = resource;
        this.notificationLists = notificationLists;




    }
}

