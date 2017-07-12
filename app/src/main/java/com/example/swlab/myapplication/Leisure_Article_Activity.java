package com.example.swlab.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Leisure_Article_Activity  extends AppCompatActivity{
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(Leisure_Article_Activity.this, Leisure_Activity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leisure_article);
    }
}
