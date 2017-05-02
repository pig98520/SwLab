package com.example.swlab.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class Sports_Swim extends AppCompatActivity {
    private DatePicker date;
    private EditText cal;
    private EditText distance;
    private EditText time;
    private Button submit;

    private String sportDate;
    private String Cal;
    private String Distance;
    private String sportTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sports_swim);
        processView();
        processControl();
    }

    private void processView() {
        date=(DatePicker)findViewById(R.id.datePicker);
        cal=(EditText)findViewById(R.id.txtCal);
        distance=(EditText)findViewById(R.id.txtDistance);
        time=(EditText)findViewById(R.id.txtTime);
        submit=(Button)findViewById(R.id.submit);
    }

    private void processControl() {
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sportDate=(date.getYear()+"-"+(date.getMonth()+1)+"-"+date.getDayOfMonth());
                Cal=cal.getText().toString()+" 大卡";
                Distance=distance.getText().toString()+" 公尺";
                sportTime=time.getText().toString()+" 分鐘";
                insertDate(sportDate,Cal,Distance,sportTime);
            }
        });
    }

    private void insertDate(String sportDate,String Cal, String Distance, String sportTime){
        Firebase myFirebaseRef = new Firebase("https://swlabapp.firebaseio.com");
        Firebase userRef = myFirebaseRef.child("sport").child("swim");
        DB_Sports_Bike_Run_Swim data = new DB_Sports_Bike_Run_Swim(sportDate,Cal,Distance,sportTime);
        userRef.push().setValue(data);
    }
}
