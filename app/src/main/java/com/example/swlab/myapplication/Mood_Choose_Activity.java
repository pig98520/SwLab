package com.example.swlab.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

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
    private FirebaseAuth auth;
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(Mood_Choose_Activity.this, Mood_Activity.class);
        startActivity(intent);
    }
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
        auth= FirebaseAuth.getInstance();
    }

    private void processControl() {
        happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moods="Happy";
                checkDialog(moods);
            }
        });
        angry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moods="Angry";
                checkDialog(moods);
            }
        });
       heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moods="Heart";
                checkDialog(moods);
            }
        });
        laugh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moods="Lanugh";
                checkDialog(moods);
            }
        });
        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moods="Sad";
                checkDialog(moods);
            }
        });
        surprise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moods="Surprise";
                checkDialog(moods);
            }
        });
    }
    private void checkDialog(final String moods) {
        AlertDialog.Builder finishDialog=new AlertDialog.Builder(this);
        finishDialog.setTitle("確認");
        finishDialog.setMessage("您今天的心情是"+moods);
        DialogInterface.OnClickListener confirmClick =new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                insert(moods);
            }
        };
        DialogInterface.OnClickListener cancelClick =new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        };
        finishDialog.setNeutralButton("確定送出",confirmClick);
        finishDialog.setNegativeButton("重新選擇",cancelClick);
        finishDialog.show();
    }
   private void insert(String moods)  {
       dtFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
       date = new Date();
       nowTime = dtFormat.format(date);
        Firebase myFirebaseRef = new Firebase("https://swlabapp.firebaseio.com/user").child("moodChoose").child(auth.getCurrentUser().getUid().trim());
        DB_Mood_Choose data = new DB_Mood_Choose(moods,nowTime);
        myFirebaseRef.push().setValue(data);
    }
}
