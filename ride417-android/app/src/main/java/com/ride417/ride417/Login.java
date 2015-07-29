package com.ride417.ride417;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.content.Intent;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button loginButton;
    EditText etEmail, etPassword;
    TextView toCreateAccount;
    UserLocalStore userLocalStore;

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

        userLocalStore = new UserLocalStore(this);
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
                User user = new User(name,email,password,phoneNum,driver);
                userLocalStore.storeUserData(user);
                userLocalStore.setUserLoggedIn(true);
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.toCreateAccount:
                startActivity(new Intent(this, Register.class));
                break;
        }
    }

}
