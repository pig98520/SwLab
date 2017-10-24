package com.example.swlab.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

public class Mood_Diary_Activity extends AppCompatActivity {
    private DatePicker date;
    private EditText content;
    private Button submit;
    private String moodDate;
    private String Content;
    private FirebaseAuth auth;
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(Mood_Diary_Activity.this, Mood_Activity.class);
        startActivity(intent);
        finish();
    }
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
        submit=(Button)findViewById(R.id.save);
        auth= FirebaseAuth.getInstance();
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
        Firebase myFirebaseRef = new Firebase("https://swlabapp.firebaseio.com/user");
        Firebase userRef = myFirebaseRef.child("moodDiary").child(auth.getCurrentUser().getUid().trim());
        DB_Mood_Diary data = new DB_Mood_Diary(moodDate,Content);
        userRef.push().setValue(data);
    }
}
