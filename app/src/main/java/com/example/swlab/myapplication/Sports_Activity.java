package com.example.swlab.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Sports_Activity extends AppCompatActivity {
    private Button record;
    private Button notification;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sports_index);
        processViews();
        processControllers();
    }
    private void processViews(){
        record=(Button)findViewById(R.id.buttonRecord);
        notification=(Button)findViewById(R.id.buttonNotification);
    }
    private void processControllers(){
        record.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Sports_Activity.this,Sports_Content.class);
                startActivity(intent);
            }
        });

        notification.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Sports_Activity.this, Sports_Notification_Activity.class);
                startActivity(intent);
            }
        });
            }
        }