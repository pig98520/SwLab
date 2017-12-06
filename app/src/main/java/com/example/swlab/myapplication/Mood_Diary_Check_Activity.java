package com.example.swlab.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by pig98520 on 2017/12/6.
 */

public class Mood_Diary_Check_Activity extends AppCompatActivity{
    private EditText edtContent;
    private Button submit;
    private String date;
    private String contentText;
    private Boolean isSaved=false;
    private FirebaseAuth auth;
    private Firebase myFirebaseRef;
    private Firebase diaryRef;
    private Bundle bundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mood_diary_check);
        processView();
        processControl();
        setContent();
    }

    private void setContent() {
        diaryRef.child("content").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    if(!isSaved)
                        edtContent.setText(dataSnapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void processView() {
        edtContent=(EditText)findViewById(R.id.txtContent);
        submit=(Button)findViewById(R.id.save);
        bundle=this.getIntent().getExtras();
        date =bundle.getString("date");
        Log.i("日期",date);
        auth=FirebaseAuth.getInstance();
        myFirebaseRef = new Firebase("https://swlabapp.firebaseio.com/user");
        diaryRef = myFirebaseRef.child("moodDiary").child(auth.getCurrentUser().getUid().trim()).child(date);
    }

    private void processControl() {
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                isSaved=true;
                contentText=edtContent.getText().toString();
                insertDate(date,contentText);
                edtContent.setText("");
                Toast.makeText(Mood_Diary_Check_Activity.this,"日記已儲存。",Toast.LENGTH_LONG);
            }
        });
    }
    private void insertDate(String moodDate,String Content){
        DB_Mood_Diary data = new DB_Mood_Diary(moodDate,Content);
        diaryRef.setValue(data);
    }
}
