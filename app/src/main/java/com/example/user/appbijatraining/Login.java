package com.example.user.appbijatraining;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

import static java.security.AccessController.getContext;

public class Login extends AppCompatActivity implements View.OnClickListener{
    private static final String LOGIN_URL = "http://bijatraining.000webhostapp.com/test.php";
    private EditText editTextusername;
    private EditText editTextPassword;

    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginc);
        editTextusername= (EditText) findViewById(R.id.et_username);
        editTextPassword = (EditText) findViewById(R.id.et_password);
        buttonLogin = (Button) findViewById(R.id.btn_login);

        buttonLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == buttonLogin)
            login();
    }

    private void login(){
        String username = editTextusername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        if(username.equals("")) {
            Toast.makeText(Login.this, "Please Enter the Username", Toast.LENGTH_SHORT).show();
        }
        else if(password.equals(""))
            Toast.makeText(Login.this, "Please Enter the password", Toast.LENGTH_SHORT).show();
        else
        userLogin(username,password);
    }


    private void userLogin(final String username, final String password){
        class UserLoginClass extends AsyncTask<String,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Login.this,"We welcome you back! ","Hold on.....",true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                System.err.println(s);
                StaffDetailDbHelper dbHelper = new StaffDetailDbHelper(Login.this);
                if(s.charAt(0)=='{'){
                    try {
                        JSONObject jsonObject = new JSONObject(s);
                        if(jsonObject.get("role").toString().equals("staff"))
                        {
                            String staff_email=jsonObject.get("staff_email").toString();
                            String staff_id=jsonObject.get("staff_id").toString();
                            String staff_contactno=jsonObject.get("staff_contactno").toString();
                            String staff_location=jsonObject.get("staff_location").toString();
                            String staff_city=jsonObject.get("staff_city").toString();
                            String staff_name=jsonObject.get("staff_name").toString();
                            String staff_role=jsonObject.get("role").toString();
                            SQLiteDatabase db = dbHelper.getWritableDatabase();

// Create a new map of values, where column names are the keys
                            ContentValues values = new ContentValues();
                            values.put(StaffContract.FeedEntry.COLUMN_NAME_UID, 1);
                            values.put(StaffContract.FeedEntry.COLUMN_NAME_ID, staff_id);
                            values.put(StaffContract.FeedEntry.COLUMN_NAME_EMAIL, staff_email);
                            values.put(StaffContract.FeedEntry.COLUMN_NAME_CONTACTNO, staff_contactno);
                            values.put(StaffContract.FeedEntry.COLUMN_NAME_LOCATION, staff_location);
                            values.put(StaffContract.FeedEntry.COLUMN_NAME_CITY, staff_city);
                            values.put(StaffContract.FeedEntry.COLUMN_NAME_NAME, staff_name);
                            values.put(StaffContract.FeedEntry.COLUMN_NAME_ID, staff_id);
                            values.put(StaffContract.FeedEntry.COLUMN_NAME_ROLE, staff_role);

// Insert the new row, returning the primary key value of the new row
                            long newRowId = db.insert(StaffContract.FeedEntry.TABLE_NAME, null, values);

                        }



                    }
                    catch (Exception e)
                    {
                        System.err.println(e);
                    }
                    Intent intent = new Intent(Login.this, TrainerActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Login.this,"Wrong Password or Username",Toast.LENGTH_LONG).show();
                    System.err.println("HELLO");
                }
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String,String> data = new HashMap<>();
                data.put("username",params[0]);
                data.put("password",params[1]);

                PostingClass ruc = new PostingClass();

                String result = ruc.sendPostRequest(LOGIN_URL,data);

                return result;
            }
        }
        UserLoginClass ulc = new UserLoginClass();
        ulc.execute(username,password);
    }
}
