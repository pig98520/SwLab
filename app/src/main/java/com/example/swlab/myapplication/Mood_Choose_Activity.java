package com.example.swlab.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import com.firebase.client.Firebase;

public class Mood_Choose_Activity extends AppCompatActivity {

    private Button submit;
    private ImageButton angry;
    private ImageButton happy;
    private ImageButton heart;
    private ImageButton laugh;
    private ImageButton sad;
    private ImageButton surprise;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mood_choose);
        processView();
        processControl();
    }
    private void processView() {
        submit=(Button)findViewById(R.id.submit);
        happy=(ImageButton)findViewById(R.id.happy_btn);
        angry=(ImageButton)findViewById(R.id.angry_btn);
        heart=(ImageButton)findViewById(R.id.heart_btn);
        laugh=(ImageButton)findViewById(R.id.laugh_btn);
        sad=(ImageButton)findViewById(R.id.sad_btn);
        surprise=(ImageButton)findViewById(R.id.surprise_btn);

    }

    private void processControl() {
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

            }
        });
    }

    private void insertDate(String moodDate,String Content){
        Firebase myFirebaseRef = new Firebase("https://swlabapp.firebaseio.com");
        Firebase userRef = myFirebaseRef.child("moodChoose");
        DB_Mood_Diary data = new DB_Mood_Diary(moodDate,Content);
        userRef.push().setValue(data);
    }
}
