package com.example.user.appbijatraining;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Detail_Extracter {
    String id;
    String name;
    String email;
    String contactno;
    String location;
    String role;
    String city;
    Detail_Extracter(Context cx)
    {
        StaffDetailDbHelper helper=new StaffDetailDbHelper(cx);
        SQLiteDatabase db=helper.getReadableDatabase();
        String[] projection={
                StaffContract.FeedEntry.COLUMN_NAME_NAME,StaffContract.FeedEntry.COLUMN_NAME_EMAIL, StaffContract.FeedEntry.COLUMN_NAME_ROLE, StaffContract.FeedEntry.COLUMN_NAME_CONTACTNO
        };
        Cursor cursor=db.rawQuery("Select * from staff",null);
        cursor.moveToFirst();
        this.id=cursor.getString(1);
        this.name=cursor.getString(2);
        this.email=cursor.getString(3);
        this.contactno=cursor.getString(4);
        this.location=cursor.getString(5);
        this.role=cursor.getString(6);
        this.city=cursor.getString(7);
    }
   public String getId()
    {
        return id;
    }
  public   String getName()
    {
        return  name;
    }
  public String getEmail()
   {
       return email;
   }

    public String getContactno() {
        return contactno;
    }

    public String getLocation() {
        return location;
    }

    public String getRole() {
        return role;
    }

    public String getCity() {
        return city;
    }
}
