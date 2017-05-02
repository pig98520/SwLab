package com.example.swlab.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class Sports_Squatting extends AppCompatActivity {
    private DatePicker date;
    private EditText count;
    private Button submit;

    private String sportDate;
    private String Count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sports_squatting);
        processView();
        processControl();
    }

    private void processView() {
        date=(DatePicker)findViewById(R.id.datePicker);
        count=(EditText)findViewById(R.id.edtCount);
        submit=(Button)findViewById(R.id.submit);
    }

    private void processControl() {
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sportDate=(date.getYear()+"-"+(date.getMonth()+1)+"-"+date.getDayOfMonth());
                Count=count.getText().toString()+" 下";
                insertDate(Count,sportDate);
            }
        });
    }

    private void insertDate(String Count,String sportDate){
        Firebase myFirebaseRef = new Firebase("https://swlabapp.firebaseio.com");
        Firebase userRef = myFirebaseRef.child("sport").child("squatting");
        DB_Sports_Ohters data = new DB_Sports_Ohters(Count,sportDate);
        userRef.push().setValue(data);
    }
}