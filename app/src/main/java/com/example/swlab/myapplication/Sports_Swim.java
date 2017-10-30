package com.example.swlab.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Sports_Swim extends AppCompatActivity {
    private TextView txt_cal;
    private TextView txt_distance;
    private TextView txt_time;

    private SimpleDateFormat dtFormat;
    private String nowTime;
    private Date date;
    private TextView timer;
    private Button finish;
    private int sec =0;
    private int min=0;
    private CountDownTimer countdownTimer;
    private Boolean isTimer=false;
    private String cal;
    private String distance;
    private String time;
    private FirebaseAuth auth;

    private Dialog customDialog;
    private Button confirm;
    private TextView title;
    private TextView message;
    private EditText input;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(Sports_Swim.this, Sports_Record.class);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sports_swim);
        processView();
        processControl();
    }


    private void processView() {
        txt_cal = (TextView) findViewById(R.id.txt_cal);
        txt_distance = (TextView) findViewById(R.id.txt_distance);
        txt_time = (TextView) findViewById(R.id.txt_time);
        timer=(TextView)findViewById(R.id.txt_timer);
        finish = (Button) findViewById(R.id.btn_stop);
        auth = FirebaseAuth.getInstance();
        dtFormat = new SimpleDateFormat("yyyy/MM/dd");
        date = new Date();
        nowTime = dtFormat.format(date);
    }

    private void processControl() {
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish.setVisibility(View.INVISIBLE);
                isTimer=false;
                timerStop();
            }
        });

        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isTimer) {
                    countdownTimer.cancel();
                    isTimer=false;
                }
                else if (min==0&&sec==0){
                    timerStart();
                    isTimer=true;
                }
                else{
                    countdownTimer.start();
                    isTimer=true;
                }
            }
        });
    }


    private void timerStart() {
        finish.setVisibility(View.VISIBLE);
        txt_distance.setText("");
        txt_cal.setText("");
        txt_time.setText("");
        countdownTimer=new CountDownTimer(1000000000000L, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                sec++;
                if(sec==60) {
                    min++;
                    sec=0;
                }
                if(min<10&&sec>10)
                    timer.setText("0"+min+":"+sec);
                else if(min>10&&sec<10)
                    timer.setText(min+":0"+sec);
                else if(min<10&sec<10)
                    timer.setText("0"+min+":0"+sec);
                else
                    timer.setText(min+":"+sec);
                finish.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFinish() {

            }
        };
        countdownTimer.start();
    }

    private void timerStop() {
        txt_time.setText(timer.getText().toString().trim());
        txt_cal.setText(((min*60+sec)*0.2877)+"");
        timer.setText("00:00");
        finish.setText("開始運動");
        min=0;
        sec=0;
        countdownTimer.cancel();
        finishDialog();
    }

    private void finishDialog() {
        customDialog=new Dialog(Sports_Swim.this,R.style.DialogCustom);
        customDialog.setContentView(R.layout.custom_dialog_text);
        customDialog.setCancelable(false);
        customDialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_rounded);
        confirm=(Button)customDialog.findViewById(R.id.confirm);
        confirm.setText("確認");
        title=(TextView)customDialog.findViewById(R.id.title);
        title.setText("結束運動");
        message=(TextView)customDialog.findViewById(R.id.message);
        message.setText("請輸入今天運動的距離吧~");
        input=(EditText)customDialog.findViewById(R.id.editText);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input.getText().toString().trim().matches("^[0-9]*$")&&!input.getText().equals("")) {
                    distance = txt_distance.getText().toString().trim();
                    cal = txt_cal.getText().toString().trim();
                    time = txt_time.getText().toString().trim();
                    insertData(nowTime, cal, distance, time);
                    finish.setVisibility(View.INVISIBLE);
                    customDialog.dismiss();
                    Toast.makeText(Sports_Swim.this, "紀錄已儲存", Toast.LENGTH_LONG).show();
                }
                else if(input.getText().toString().trim().equals(""))
                    Toast.makeText(Sports_Swim.this, "請輸入數字", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Sports_Swim.this, "請輸入數字", Toast.LENGTH_LONG).show();
            }
        });
        customDialog.show();
    }

    private void insertData(String sportDate, String Cal, String Distance, String sportTime){
        Firebase myFirebaseRef = new Firebase("https://swlabapp.firebaseio.com/user");
        Firebase userRef = myFirebaseRef.child("sport").child("swim").child(auth.getCurrentUser().getUid().trim());
        DB_Sports_Bike_Run_Swim data = new DB_Sports_Bike_Run_Swim(sportDate,Cal,Distance,sportTime);
        userRef.push().setValue(data);
    }
}
