package com.ride417.ride417;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.*;
import android.view.*;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button logoutButton;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL,true);

        logoutButton = (Button) findViewById(R.id.logoutButton);

        logoutButton.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);
    }

    private boolean authenticate(){
        return userLocalStore.getUserLoggedIn();
    }

    @Override
    public void onStart(){
        super.onStart();
        if(this.authenticate()){

        }else{
            startActivity(new Intent(this, Login.class));
            //TODO terms and conditions page stuff
        }
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.logoutButton:
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);
                ParseUser.logOutInBackground();
                finish();
                startActivity(new Intent(this, Login.class));
                break;
        }
    }


}
