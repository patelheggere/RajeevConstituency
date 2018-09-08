package com.patelheggere.rajeevconstituency;

import android.app.Application;
import android.content.SharedPreferences;

public class RajeevApplication extends Application {
    private static RajeevApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        // ApiClient.intialise();
       /* if(isDeve()) {
            ApiClient.setBaseUrl(AppConstants.appBaseUrlDev);
        }
        else
        {
            ApiClient.setBaseUrl(AppConstants.appBaseUrl);

        }*/

    }

    public static synchronized RajeevApplication getInstance() {
        return mInstance;
    }

}