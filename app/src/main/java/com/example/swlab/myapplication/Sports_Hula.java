package com.example.swlab.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
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

public class Sports_Hula extends AppCompatActivity {
    private TextView txt_cal;
    private TextView txt_count;
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
    private String count;
    private String time;
    private FirebaseAuth auth;
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(Sports_Hula.this, Sports_Record.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sports_hula);
        processView();
        processControl();
    }


    private void processView() {
        txt_cal = (TextView) findViewById(R.id.txt_cal);
        txt_count = (TextView) findViewById(R.id.txt_count);
        txt_time = (TextView) findViewById(R.id.txt_time);
        timer=(TextView)findViewById(R.id.txt_timer);
        finish = (Button) findViewById(R.id.btn_start);
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
                isTimer = false;
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
        txt_cal.setText("");
        txt_count.setText("");
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
                finish.setText("結束運動");
            }

            @Override
            public void onFinish() {

            }
        };
        countdownTimer.start();
    }

    private void timerStop() {
        txt_time.setText(timer.getText().toString().trim());
        txt_cal.setText(((min*60+sec)*0.035)+"");
        timer.setText("00:00");
        min=0;
        sec=0;
        countdownTimer.cancel();
        finishDialog();
    }

    private void finishDialog() {
        AlertDialog.Builder finishDialog=new AlertDialog.Builder(this);
        final EditText input=new EditText(this);
        finishDialog.setTitle("結束運動");
        finishDialog.setMessage("流點汗應該舒服多了吧,算一下你今天搖了幾下吧?");
        finishDialog.setView(input);
        DialogInterface.OnClickListener confirmClick =new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                txt_count.setText(input.getText().toString());
                cal= txt_cal.getText().toString().trim();
                count = txt_count.getText().toString().trim();
                time = txt_time.getText().toString().trim();
                insertData(nowTime,cal, count, time);
                finish.setVisibility(View.INVISIBLE);
                Toast.makeText(Sports_Hula.this, "紀錄已儲存",Toast.LENGTH_LONG).show();
            }
        };
        DialogInterface.OnClickListener cancelClick =new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        };
        finishDialog.setNeutralButton("確定",confirmClick);
        finishDialog.setNegativeButton("取消",cancelClick);
        finishDialog.show();
    }

    private void insertData(String sportDate, String Cal, String Distance, String sportTime){
        Firebase myFirebaseRef = new Firebase("https://swlabapp.firebaseio.com/user");
        Firebase userRef = myFirebaseRef.child("sport").child("hula").child(auth.getCurrentUser().getUid().trim());
        DB_Sports_Others data = new DB_Sports_Others(sportDate,Cal,Distance,sportTime);
        userRef.push().setValue(data);
    }
}