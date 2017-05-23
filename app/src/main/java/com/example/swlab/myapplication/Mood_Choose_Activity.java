package com.example.swlab.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.firebase.client.Firebase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Mood_Choose_Activity extends AppCompatActivity {

    private SimpleDateFormat dtFormat;
    private String nowTime;
    private Date date;
    private ImageButton angry;
    private ImageButton happy;
    private ImageButton heart;
    private ImageButton laugh;
    private ImageButton sad;
    private ImageButton surprise;
    private String moods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mood_choose);
        processView();
        processControl();
    }
    private void processView() {
        happy=(ImageButton)findViewById(R.id.happy_btn);
        angry=(ImageButton)findViewById(R.id.angry_btn);
        heart=(ImageButton)findViewById(R.id.heart_btn);
        laugh=(ImageButton)findViewById(R.id.laugh_btn);
        sad=(ImageButton)findViewById(R.id.sad_btn);
        surprise=(ImageButton)findViewById(R.id.surprise_btn);
    }

    private void processControl() {
        happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moods="Happy";
                insert(moods);
            }
        });
        angry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moods="Angry";
                insert(moods);
            }
        });
       heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moods="Heart";
                insert(moods);
            }
        });
        laugh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moods="Lanugh";
                insert(moods);
            }
        });
        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moods="Sad";
                insert(moods);
            }
        });
        surprise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moods="Surprise";
                insert(moods);
            }
        });
    }

   private void insert(String moods)  {
       dtFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
       date = new Date();
       nowTime = dtFormat.format(date);
        Firebase myFirebaseRef = new Firebase("https://swlabapp.firebaseio.com").child("moodChoose");
        DB_Mood_Choose data = new DB_Mood_Choose(moods,nowTime);
        myFirebaseRef.push().setValue(data);
    }
}
