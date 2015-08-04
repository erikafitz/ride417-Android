package com.ride417.ride417;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.SignUpCallback;


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
                String password = etPassword.getText().toString();
                String passwordConfirmed = etPasswordConfirmation.getText().toString();
                String email = etEmail.getText().toString();
                String name = etName.getText().toString();
                String phoneNumber = etPhoneNum.getText().toString();
                if (password.isEmpty() || email.isEmpty() || name.isEmpty() || phoneNumber.isEmpty()) {
                    //alert
                    showAlert("All fields are required");
                } else if (!password.equals(passwordConfirmed)) {
                    //alert
                    showAlert("Passwords do not match");
                } else if (!email.endsWith(".edu")) {
                    //alert
                    showAlert("Please enter a valid .edu email");
                }
                //if everything is okay...
                else{
                    User user = new User();
                    user.setEmail(email);
                    user.setUsername(email);
                    user.setPassword(password);
                    user.setName(name);
                    user.setPhoneNumber(phoneNumber);
                    user.setIsDriver(false);
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                startActivity(new Intent(Register.this, MainActivity.class));
                            } else {
                                // Sign up didn't succeed. Look at the ParseException
                                // to figure out what went wrong
                                showAlert(e.getMessage());

                            }
                        }
                    });
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
