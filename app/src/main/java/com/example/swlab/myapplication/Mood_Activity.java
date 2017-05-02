package com.example.swlab.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Mood_Activity extends AppCompatActivity {
    private Button decetion;
    private Button mood;
    private Button diary;
    private Button sum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mood_index);
        processViews();
        processControllers();
    }
    private void processViews(){
        decetion=(Button)findViewById(R.id.btnDetection);
        mood=(Button)findViewById(R.id.btnMood);
        diary=(Button)findViewById(R.id.btnDiary);
        sum=(Button)findViewById(R.id.btnSum);
    }
    private void processControllers(){
        decetion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Mood_Activity.this,Mood_Detection_Activity.class);
                startActivity(intent);
            }
        });

      mood.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Mood_Activity.this,Mood_Choose_Activity.class);
                startActivity(intent);
            }
        });
        diary.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Mood_Activity.this,Mood_Diary_Activity.class);
                startActivity(intent);
            }
        });
        sum.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Mood_Activity.this,Mood_Sum_Activity.class);
                startActivity(intent);
            }
        });
    }
}
