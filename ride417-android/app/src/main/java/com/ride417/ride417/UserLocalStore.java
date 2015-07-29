package com.ride417.ride417;

import android.content.SharedPreferences;
import android.content.Context;

/**
 * Created by erikafitz on 7/29/15.
 */
public class UserLocalStore {

    public static final String SP_Name = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context){
        userLocalDatabase = context.getSharedPreferences(SP_Name, 0);
    }

    public void storeUserData(User user){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("email",user.email);
        spEditor.putString("password",user.password);
        spEditor.putBoolean("driver",user.driver);
    }

    public User getLoggedInUser(){
        String email = userLocalDatabase.getString("email", "");
        String password = userLocalDatabase.getString("password","");
        boolean driver = userLocalDatabase.getBoolean("driver", false);
        return new User("",email,password,"",driver);
    }

    public void setUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.remove("loggedIn");
        spEditor.putBoolean("loggedIn",true);
        spEditor.commit();
    }

    public void clearUserData(){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }

    public boolean getUserLoggedIn(){
        return userLocalDatabase.getBoolean("loggedIn",false);
    }

}
