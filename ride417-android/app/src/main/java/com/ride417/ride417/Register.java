package com.ride417.ride417;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.app.AlertDialog;
import android.content.DialogInterface;

import org.w3c.dom.Text;


public class Register extends AppCompatActivity implements View.OnClickListener {

    Button registerButton;
    EditText etEmail, etPassword, etPasswordConfirmation, etName, etPhoneNum;
    TextView backToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etPasswordConfirmation = (EditText) findViewById(R.id.etPasswordConfirmation);
        etName = (EditText) findViewById(R .id.etName);
        etPhoneNum = (EditText) findViewById(R.id.etPhoneNum);
        registerButton = (Button) findViewById(R.id.registerButton);
        backToLogin = (TextView) findViewById(R.id.backToLogin);

        registerButton.setOnClickListener(this);
        backToLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()) {
            case R.id.registerButton:
                //validation
                String password = etPassword.getText().toString();
                String passwordConfirmed = etPasswordConfirmation.getText().toString();
                String email = etEmail.getText().toString();
                String name = etName.getText().toString();
                String phoneNumber = etPhoneNum.getText().toString();
                if (!email.endsWith(".edu") || !email.contains("@")) {
                    //alert
                    showAlert("Please enter a valid .edu email");
                } else if (!password.equals(passwordConfirmed)) {
                    //alert
                    showAlert("Passwords do not match");
                } else if (password.isEmpty() || email.isEmpty() || name.isEmpty() || phoneNumber.isEmpty()) {
                    //alert
                    showAlert("All fields are required");
                }
                //if everything is okay...
                else{
                    User registeredData = new User(name,email,password,phoneNumber,false);
                    startActivity(new Intent(this, MainActivity.class));
                }
                break;
            case R.id.backToLogin:
                startActivity(new Intent(this, Login.class));
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