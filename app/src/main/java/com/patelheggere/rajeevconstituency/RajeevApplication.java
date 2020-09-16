package com.patelheggere.rajeevconstituency;

import android.app.Application;
import android.content.SharedPreferences;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RajeevApplication extends Application {
    private static RajeevApplication mInstance;
    private static DatabaseReference databaseReference;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        FirebaseApp.initializeApp(this);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        // ApiClient.intialise();
       /* if(isDeve()) {
            ApiClient.setBaseUrl(AppConstants.appBaseUrlDev);
        }
        else
        {
            ApiClient.setBaseUrl(AppConstants.appBaseUrl);

        }*/

    }
    public static synchronized DatabaseReference getFireBaseRef()
    {

        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        if(BuildConfig.DEBUG) {
            databaseReference = FirebaseDatabase.getInstance().getReference().child("test");
        }
        else {
            databaseReference = FirebaseDatabase.getInstance().getReference().child("prod");
        }
        return databaseReference;
    }

    public static synchronized RajeevApplication getInstance() {
        return mInstance;
    }

}