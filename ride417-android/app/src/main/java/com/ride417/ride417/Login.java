package com.ride417.ride417;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.content.Intent;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button loginButton;
    EditText etEmail, etPassword;
    TextView toCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        loginButton = (Button) findViewById(R.id.loginButton);
        toCreateAccount = (TextView) findViewById(R.id.toCreateAccount);

        loginButton.setOnClickListener(this);
        toCreateAccount.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.loginButton:
                String password = etPassword.getText().toString();
                String email = etEmail.getText().toString();
                Boolean driver = false;
                String name = "";
                String phoneNum = "";
                ParseUser.logInInBackground(email, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        if (parseUser != null) {
                            // If user exist and authenticated, send user to the terms/conditions page
                            startActivity(new Intent(Login.this, MainActivity.class));
                        } else {
                            showAlert(e.getMessage());
                        }
                    }
                });
                break;
            case R.id.toCreateAccount:
                startActivity(new Intent(this, Register.class));
                break;
        }
    }

    public void showAlert(String s){
        new AlertDialog.Builder(this)
                .setTitle("Alert")
                .setMessage(s)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}
