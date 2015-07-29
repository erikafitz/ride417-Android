package com.ride417.myapplication;

import com.parse.Parse;
import com.parse.*;
import android.app.Application;

/**
 * Created by erikafitz on 7/14/15.
 */
public class Ride417Application extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        // Required - Initialize the Parse SDK
        Parse.initialize(this, "Rj5yrt1LSQ3mBSNxyl393ti9LTQnq5shAw3sevAO", "DrC5xwSrSiJ67QeeHgOb7K3nfZ4EkHtbrmCSNMi8");

    }
}
