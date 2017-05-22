package com.example.swlab.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.TextView;



import com.firebase.client.Firebase;

public class Mood_Choose_Activity extends AppCompatActivity {

    private Button submit;
    private ImageButton angry;
    private ImageButton happy;
    private ImageButton heart;
    private ImageButton laugh;
    private ImageButton sad;
    private ImageButton surprise;
    private TextView GotClick;
    private String moods;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mood_choose);
        processView();
        processControl();
    }
    private void processView() {
        submit=(Button)findViewById(R.id.submit);
        happy=(ImageButton)findViewById(R.id.happy_btn);
        angry=(ImageButton)findViewById(R.id.angry_btn);
        heart=(ImageButton)findViewById(R.id.heart_btn);
        laugh=(ImageButton)findViewById(R.id.laugh_btn);
        sad=(ImageButton)findViewById(R.id.sad_btn);
        surprise=(ImageButton)findViewById(R.id.surprise_btn);
        GotClick= (TextView) findViewById(R.id.textView);

    }

    private void processControl() {
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                happy.setOnFocusChangeListener(new OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus == true)
                        {
                            GotClick.setText("圖片按鈕狀態為 : Got Click");
                            insert(moods);
                        }
                    }
                });
                happy.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GotClick.setText("圖片按鈕狀態為:Got Click");
                        insert(moods);
                    }
                });

                angry.setOnFocusChangeListener(new OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus == true)
                        {
                            GotClick.setText("圖片按鈕狀態為 : Got Click");
                            insert(moods);
                        }
                    }
                });
                angry.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GotClick.setText("圖片按鈕狀態為:Got Click");
                        insert(moods);
                    }
                });

                heart.setOnFocusChangeListener(new OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus == true)
                        {
                            GotClick.setText("圖片按鈕狀態為 : Got Click");
                            insert(moods);
                        }
                    }
                });
                heart.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GotClick.setText("圖片按鈕狀態為:Got Click");
                        insert(moods);
                    }
                });

                laugh.setOnFocusChangeListener(new OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus == true)
                        {
                            GotClick.setText("圖片按鈕狀態為 : Got Click");
                            insert(moods);
                        }
                    }
                });
                laugh.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GotClick.setText("圖片按鈕狀態為:Got Click");
                        insert(moods);
                    }
                });

                sad.setOnFocusChangeListener(new OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus == true)
                        {
                            GotClick.setText("圖片按鈕狀態為 : Got Click");
                            insert(moods);
                        }
                    }
                });
                sad.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GotClick.setText("圖片按鈕狀態為:Got Click");
                        insert(moods);
                    }
                });

                surprise.setOnFocusChangeListener(new OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus == true)
                        {
                            GotClick.setText("圖片按鈕狀態為 : Got Click");
                            insert(moods);
                        }
                    }
                });
                surprise.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GotClick.setText("圖片按鈕狀態為:Got Click");
                        insert(moods);
                    }
                });

            }
        });
    }

   private void insert(String moods)  {
        Firebase myFirebaseRef = new Firebase("https://swlabapp.firebaseio.com");
        Firebase userRef = myFirebaseRef.child("moodChoose");
        DB_Mood_Choose data = new DB_Mood_Choose();
        userRef.push().setValue(data);
    }
}
