package com.example.swlab.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Music_Exercise extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_exercise);
        Button nextPageBtn = (Button)findViewById(R.id.back_btn);
        nextPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Music_Exercise.this  , Music_Activity.class);
                startActivity(intent);
            }
        });
        Button setBtn = (Button)findViewById(R.id.set_btn);
        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Music_Exercise.this  , Music_Set.class);
                startActivity(intent);
            }
        });
    }
}
