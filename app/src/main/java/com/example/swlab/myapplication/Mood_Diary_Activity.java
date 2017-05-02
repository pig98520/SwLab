package com.example.swlab.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class Mood_Diary_Activity extends AppCompatActivity {
    private DatePicker date;
    private EditText content;
    private Button submit;

    private String moodDate;
    private String Content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mood_diary);
        processView();
        processControl();
    }
    private void processView() {
        date=(DatePicker)findViewById(R.id.datePicker);
        content=(EditText)findViewById(R.id.txtContent);
        submit=(Button)findViewById(R.id.submit);
    }

    private void processControl() {
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                moodDate=(date.getYear()+"-"+(date.getMonth()+1)+"-"+date.getDayOfMonth());
                Content=content.getText().toString();
                insertDate(moodDate,Content);
            }
        });
    }

    private void insertDate(String moodDate,String Content){
        Firebase myFirebaseRef = new Firebase("https://swlabapp.firebaseio.com");
        Firebase userRef = myFirebaseRef.child("moodDiary");
        DB_Mood_Diary data = new DB_Mood_Diary(moodDate,Content);
        userRef.push().setValue(data);
    }
}
