package com.example.user.appbijatraining;

import android.provider.BaseColumns;

public class StaffContract {
    private StaffContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "staff";
        public static final String COLUMN_NAME_UID = "uid";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_CONTACTNO = "contactno";
        public static final String COLUMN_NAME_LOCATION = "location";
        public static final String COLUMN_NAME_CITY = "city";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_ROLE = "role";
    }
}
