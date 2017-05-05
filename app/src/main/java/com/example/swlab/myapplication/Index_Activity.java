package com.example.swlab.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Index_Activity extends AppCompatActivity {
    private Button btnfun1;
    private Button btnfun2;
    private Button btnfun3;
    private Button btnfun4;
    private Button btnfun5;
    private Button btnfun6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);
        processViews();
        processControllers();
    }
    private void processViews(){
        btnfun1=(Button)findViewById(R.id.btn1);
        btnfun2=(Button)findViewById(R.id.btn2);
        btnfun3=(Button)findViewById(R.id.btn3);
        btnfun4=(Button)findViewById(R.id.btn4);
        btnfun5=(Button)findViewById(R.id.btn5);
        btnfun6=(Button)findViewById(R.id.btn6);
    }
    private void processControllers(){

        btnfun1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Index_Activity.this,Mood_Activity.class);
                startActivity(intent);
            }
        });

        btnfun2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Index_Activity.this,Leisure_Activity.class);
                startActivity(intent);
            }
        });

        btnfun3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Index_Activity.this,Chat_Activity.class);
                startActivity(intent);
            }
        });
        btnfun4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Index_Activity.this,Music_Activity.class);
                startActivity(intent);
            }
        });
        btnfun5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Index_Activity.this,Sports_Activity.class);
                startActivity(intent);
            }
        });
        btnfun6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Index_Activity.this,Question_Activity.class);
                startActivity(intent);
            }
        });
    }
}
