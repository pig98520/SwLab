package com.example.swlab.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Music_Activity extends AppCompatActivity {
    public static char returnFlag = ' ';
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_index);
        Button nextPageBtn = (Button)findViewById(R.id.relax_btn);
        nextPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                returnFlag='r';
                intent.setClass(Music_Activity.this  , Music_Relax.class);
                startActivity(intent);
            }
        });
        Button nextPageBtn2 = (Button)findViewById(R.id.concentrate_btn);
        nextPageBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                returnFlag='c';
                intent.setClass(Music_Activity.this  , Music_Concentrate.class);
                startActivity(intent);
            }
        });
        Button nextPageBtn3 = (Button)findViewById(R.id.sleep_btn);
        nextPageBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                returnFlag='s';
                intent.setClass(Music_Activity.this  , Music_Sleep.class);
                startActivity(intent);
            }
        });
        Button nextPageBtn4 = (Button)findViewById(R.id.exercise_btn);
        nextPageBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                returnFlag='e';
                intent.setClass(Music_Activity.this  , Music_Exercise.class);
                startActivity(intent);
            }
        });
        Button nextPageBtn5 = (Button)findViewById(R.id.back_btn);
        nextPageBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Music_Activity.this  , Index_Activity.class);
                startActivity(intent);
            }
        });
    }
}
