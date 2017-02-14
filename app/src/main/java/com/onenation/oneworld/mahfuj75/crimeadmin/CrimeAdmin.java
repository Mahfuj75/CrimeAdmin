package com.onenation.oneworld.mahfuj75.crimeadmin;

import android.app.Application;
import android.os.SystemClock;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

/**
 * Created by mahfu on 1/31/2017.
 */


public class CrimeAdmin extends Application{



    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(TimeUnit.SECONDS.toMillis(1));

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
