package com.example.user.appbijatraining;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class Login extends AppCompatActivity implements View.OnClickListener{
    private static final String LOGIN_URL = "http://bijatraining.epizy.com/login.php";
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
                if(s.equalsIgnoreCase("success")){
                    Intent intent = new Intent(Login.this,HomeActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Login.this,"Not OK",Toast.LENGTH_LONG).show();
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
