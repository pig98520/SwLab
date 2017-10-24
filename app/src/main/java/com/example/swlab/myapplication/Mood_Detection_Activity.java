package com.example.swlab.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Mood_Detection_Activity extends AppCompatActivity{

    private SimpleDateFormat dtFormat;
    private String nowTime;
    private Date date;
    private Mood_Detection_Question mQuestionLibrary = new Mood_Detection_Question();
    private TextView mQuestionView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mButtonChoice4;
    private int mQuestionNumber = 0 ;
    private int mScore=0;
    private FirebaseAuth auth;
    private DatabaseReference dbRef;
    private Dialog customDialog;
    private TextView title;
    private TextView message;
    private Button confirm;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(Mood_Detection_Activity.this, Mood_Activity.class);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mood_detection);
        processView();
        checkdone();
        updateQuestion();
        processControl();
    }


    private void processView() {
        mQuestionView = (TextView) findViewById(R.id.question);
        mButtonChoice1 = (Button) findViewById(R.id.choice1);
        mButtonChoice2 = (Button) findViewById(R.id.choice2);
        mButtonChoice3 = (Button) findViewById(R.id.choice3);
        mButtonChoice4 = (Button) findViewById(R.id.choice4);
        dbRef= FirebaseDatabase.getInstance().getReference();
        auth= FirebaseAuth.getInstance();
        dtFormat = new SimpleDateFormat("MM/dd");
        date = new Date();
        nowTime = dtFormat.format(date);
    }

    private void processControl() {

    }
    private void checkdone() {
        dbRef.child("user").child("moodDetection").child(auth.getCurrentUser().getUid()).orderByKey().limitToLast(1).addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(com.google.firebase.database.DataSnapshot snapshot:dataSnapshot.getChildren()){
                        DB_Mood_Detection mood_detection = snapshot.getValue(DB_Mood_Detection.class);
                        Log.i("TAGGG",mood_detection.getTime());
                        Log.i("TAGGGG",nowTime.toString());
                        if(mood_detection.getTime().equals(nowTime.toString()))
                        {
                            customDialog=new Dialog(Mood_Detection_Activity.this,R.style.DialogCustom);
                            customDialog.setContentView(R.layout.custom_dialog_one);
                            customDialog.setCancelable(false);
                            confirm=(Button)customDialog.findViewById(R.id.confirm);
                            confirm.setText("確認");
                            title=(TextView)customDialog.findViewById(R.id.title);
                            title.setText("提醒");
                            message=(TextView)customDialog.findViewById(R.id.message);
                            message.setText("今日已經做過測驗了喔~");

                            confirm.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent();
                                    intent.setClass(Mood_Detection_Activity.this, Mood_Activity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                            customDialog.show();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void updateQuestion(){
        if (mQuestionNumber<mQuestionLibrary.getLength() ){
            mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
            mButtonChoice1.setText(mQuestionLibrary.getChoice(1));
            mButtonChoice2.setText(mQuestionLibrary.getChoice(2));
            mButtonChoice3.setText(mQuestionLibrary.getChoice(3));
            mButtonChoice4.setText(mQuestionLibrary.getChoice(4));
            addScore();
            mQuestionNumber++;
            }
        else if(mQuestionNumber==mQuestionLibrary.getLength()) {
            addScore();
            finishDialog();
        }
    }

    private void finishDialog() {
        customDialog = new Dialog(Mood_Detection_Activity.this, R.style.DialogCustom);
        customDialog.setContentView(R.layout.custom_dialog_one);
        customDialog.setCancelable(false);
        confirm = (Button) customDialog.findViewById(R.id.confirm);
        confirm.setText("確認");
        title = (TextView) customDialog.findViewById(R.id.title);
        title.setText("測驗結果");
        message = (TextView) customDialog.findViewById(R.id.message);
        message.setText("您的憂鬱指數為" + mScore + "分");

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB_Mood_Detection data = new DB_Mood_Detection(mScore + "", nowTime);
                dbRef.child("user").child("moodDetection").child(auth.getCurrentUser().getUid().trim()).push().setValue(data);

                Intent intent = new Intent();
                intent.setClass(Mood_Detection_Activity.this, Mood_Activity.class);
                startActivity(intent);
                finish();
            }
        });
        customDialog.show();
    }

    private void addScore() {
        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScore += 0;
                updateQuestion();
            }
        });
        mButtonChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScore += 1;
                updateQuestion();
            }
        });
        mButtonChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScore += 2;
                updateQuestion();
            }
        });
        mButtonChoice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScore += 3;
                updateQuestion();
            }
        });

    }
}
