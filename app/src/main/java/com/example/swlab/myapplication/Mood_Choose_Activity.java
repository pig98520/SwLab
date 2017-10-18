package com.example.swlab.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Mood_Choose_Activity extends AppCompatActivity {

    private SimpleDateFormat dtFormat;
    private String nowTime;
    private Date date;
    private Button angry;
    private Button happy;
    private Button heart;
    private Button laugh;
    private Button sad;
    private Button surprise;
    private String moods;
    private Firebase dbRef;
    private FirebaseAuth auth;
    private Dialog customDialog;
    private TextView title;
    private TextView message;
    private Button confirm;
    private Button cancel;
    private Boolean done=false;

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
        happy=(Button)findViewById(R.id.happy_btn);
        angry=(Button)findViewById(R.id.angry_btn);
        heart=(Button)findViewById(R.id.heart_btn);
        laugh=(Button)findViewById(R.id.laugh_btn);
        sad=(Button)findViewById(R.id.sad_btn);
        surprise=(Button)findViewById(R.id.surprise_btn);
        dtFormat = new SimpleDateFormat("yyyy/MM/dd");
        date = new Date();
        nowTime = dtFormat.format(date);
        dbRef=new Firebase("https://swlabapp.firebaseio.com/");
        auth= FirebaseAuth.getInstance();
    }
    private void check_done() {
        dbRef.child("user").child("moodChoose").child(auth.getCurrentUser().getUid()).orderByKey().limitToLast(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        DB_Mood_Choose mood_choose = snapshot.getValue(DB_Mood_Choose.class);
                        if(mood_choose.getTime().equals(nowTime.toString()))
                        {
                            done=true;
                            customDialog=new Dialog(Mood_Choose_Activity.this,R.style.DialogCustom);
                            customDialog.setContentView(R.layout.custom_dialog_one);
                            customDialog.setCancelable(false);
                            confirm=(Button)customDialog.findViewById(R.id.confirm);
                            confirm.setText("確認");
                            title=(TextView)customDialog.findViewById(R.id.title);
                            title.setText("提醒");
                            message=(TextView)customDialog.findViewById(R.id.message);
                            message.setText("今日已經做過紀錄了喔~");

                            confirm.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent();
                                    intent.setClass(Mood_Choose_Activity.this, Mood_Activity.class);
                                    startActivity(intent);
                                }
                            });
                            customDialog.show();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void processControl() {
        happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_done();
                if(!done) {
                    moods = "Happy";
                    checkDialog(moods);
                }
            }
        });
        angry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_done();
                if(!done) {
                    moods = "Angry";
                    checkDialog(moods);
                }
            }
        });
       heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_done();
                if(!done) {
                    moods = "Heart";
                    checkDialog(moods);
                }
            }
        });
        laugh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_done();
                if(!done) {
                    moods = "Lanugh";
                    checkDialog(moods);
                }
            }
        });
        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_done();
                if(!done) {
                    moods = "Sad";
                    checkDialog(moods);
                }
            }
        });
        surprise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_done();
                if(!done){
                    moods="Surprise";
                    checkDialog(moods);
                }
            }
        });
    }
    private void checkDialog(final String moods) {
        customDialog=new Dialog(Mood_Choose_Activity.this,R.style.DialogCustom);
        customDialog.setContentView(R.layout.custom_dialog_two);
        customDialog.setCancelable(false);
        confirm=(Button)customDialog.findViewById(R.id.confirm);
        confirm.setText("儲存心情");
        cancel=(Button)customDialog.findViewById(R.id.cancel);
        cancel.setText("重作一次");
        title=(TextView)customDialog.findViewById(R.id.title);
        title.setText("確認");
        message=(TextView)customDialog.findViewById(R.id.message);
        message.setText("您今天的心情是"+moods);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               insert(moods);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Mood_Choose_Activity.this, Mood_Choose_Activity.class);
                startActivity(intent);
            }
        });
        customDialog.show();
    }
   private void insert(String moods)  {
       DB_Mood_Choose data = new DB_Mood_Choose(moods,nowTime);
       dbRef.child("user").child("moodChoose").child(auth.getCurrentUser().getUid().trim()).push().setValue(data);
    }
}
