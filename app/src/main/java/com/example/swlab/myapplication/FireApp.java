package com.example.swlab.myapplication;

import android.app.Application;

import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by swlab on 2017/5/2.
 */

public class FireApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        //Previous versions of Firebase
        Firebase.setAndroidContext(this);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
