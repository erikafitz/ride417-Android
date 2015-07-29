package com.ride417.ride417;

/**
 * Created by erikafitz on 7/29/15.
 */
public class User {
    public String name, email, password, phoneNum, school;
    public boolean driver;


    public User(String n, String e, String p, String pN, Boolean d){
        e.replace(" ", "");
        name =n;
        email=e;
        password=p;
        phoneNum=pN;
        driver=d;
        if(e.endsWith("@evangel.edu")){
            school = "Evangel University";
        } else if(e.endsWith("@missouri.edu")){
            school = "Missouri State Universty";
        } else{
            school = "unknown";
        }
    }
}
