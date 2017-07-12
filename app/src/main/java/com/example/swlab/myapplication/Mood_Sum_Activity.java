package com.example.swlab.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Mood_Sum_Activity extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(Mood_Sum_Activity.this, Mood_Activity.class);
        startActivity(intent);
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mood_sum);
    }
}
