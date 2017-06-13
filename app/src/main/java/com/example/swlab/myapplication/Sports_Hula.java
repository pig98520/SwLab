package com.example.swlab.myapplication;

import android.content.DialogInterface;
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
    private EditText edt_cal;
    private EditText edt_count;
    private EditText edt_time;
    private SimpleDateFormat dtFormat;
    private String nowTime;
    private Date date;
    private TextView timer;
    private Button save;
    private Button start;
    private int sec =0;
    private int min=0;
    private CountDownTimer countdownTimer;
    private Boolean isTimer=false;
    private String cal;
    private String count;
    private String sportTime;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sports_hula);
        processView();
        processControl();
    }


    private void processView() {
        edt_cal = (EditText) findViewById(R.id.txtCal);
        edt_count = (EditText) findViewById(R.id.txtCount);
        edt_time = (EditText) findViewById(R.id.txtTime);
        timer=(TextView)findViewById(R.id.txt_timer);
        save = (Button) findViewById(R.id.btn_save);
        start = (Button) findViewById(R.id.btn_start);
        auth = FirebaseAuth.getInstance();
        timer = (TextView)findViewById(R.id.txt_timer);
        dtFormat = new SimpleDateFormat("yyyy/MM/dd");
        date = new Date();
        nowTime = dtFormat.format(date);
    }

    private void processControl() {
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isTimer){
                    isTimer=true;
                    timerStart();
                }
                else
                {
                    isTimer=false;
                    timerStop();
                }

            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal=edt_cal.getText().toString().trim();
                count = edt_count.getText().toString().trim();
                sportTime=edt_time.getText().toString().trim();
                insertData(nowTime,cal, count,sportTime);
                Toast.makeText(Sports_Hula.this, "紀錄已儲存",Toast.LENGTH_LONG).show();
            }
        });
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isTimer) {
                    countdownTimer.cancel();
                    isTimer=false;
                }
                else {
                    countdownTimer.start();
                    isTimer=true;
                }
            }
        });
    }


    private void timerStart() {
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
                start.setText("結束運動");
            }

            @Override
            public void onFinish() {

            }
        };
        countdownTimer.start();
    }

    private void timerStop() {
        edt_time.setText(timer.getText().toString().trim());
        edt_cal.setText(((min*60+sec)*0.035)+"");
        timer.setText("00:00");
        start.setText("開始運動");
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
                edt_count.setText(input.getText().toString());
                cal=edt_cal.getText().toString().trim();
                count = edt_count.getText().toString().trim();
                sportTime=edt_time.getText().toString().trim();
                insertData(nowTime,cal, count,sportTime);
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