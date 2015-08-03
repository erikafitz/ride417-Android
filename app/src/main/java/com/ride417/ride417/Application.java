package com.ride417.ride417;

import com.parse.Parse;

/**
 * Created by erikafitz on 7/30/15.
 */
public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "Rj5yrt1LSQ3mBSNxyl393ti9LTQnq5shAw3sevAO", "DrC5xwSrSiJ67QeeHgOb7K3nfZ4EkHtbrmCSNMi8");
    }
}
