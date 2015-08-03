package com.ride417.ride417;

import com.parse.ParseClassName;
import com.parse.ParseUser;

/**
 * Created by jackietran on 8/3/15.
 */
@ParseClassName("_User")
public class User extends ParseUser{

    public User() {
        super();
    }

    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        put("name", name);
    }

    public String getPhoneNumber() {
        return getString("number");
    }

    public void setPhoneNumber(String phoneNumber) {
        put("number", phoneNumber);
    }
}
