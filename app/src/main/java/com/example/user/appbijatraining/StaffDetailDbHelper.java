package com.example.user.appbijatraining;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StaffDetailDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "bija.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + StaffContract.FeedEntry.TABLE_NAME + " (" +
                    StaffContract.FeedEntry.COLUMN_NAME_UID + " INTEGER PRIMARY KEY," +
                    StaffContract.FeedEntry.COLUMN_NAME_ID + " TEXT ," +
                    StaffContract.FeedEntry.COLUMN_NAME_NAME + " TEXT," +
                    StaffContract.FeedEntry.COLUMN_NAME_EMAIL + " TEXT," +
                    StaffContract.FeedEntry.COLUMN_NAME_CONTACTNO + " TEXT," +
                    StaffContract.FeedEntry.COLUMN_NAME_LOCATION + " TEXT," +
                    StaffContract.FeedEntry.COLUMN_NAME_ROLE + " TEXT," +
                    StaffContract.FeedEntry.COLUMN_NAME_CITY + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + StaffContract.FeedEntry.TABLE_NAME;

    public StaffDetailDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
