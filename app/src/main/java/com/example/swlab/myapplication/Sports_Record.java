package com.example.swlab.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Sports_Record extends AppCompatActivity {
    private Button run;
    private Button bike;
    private Button swim;
    private Button upanddown;
    private Button situps;
    private Button squatiing;
    private Button rope;
    private Button hula;
    private Button back_btn;
    private boolean isdoubleClick=false;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(Sports_Record.this, Sports_Activity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sports_record);
        processViews();
        processControllers();
    }
    private void processViews(){
        run=(Button)findViewById(R.id.run_btn);
        bike=(Button)findViewById(R.id.bike_btn);
        swim=(Button)findViewById(R.id.swim_btn);
        upanddown=(Button)findViewById(R.id.upanddown_btn);
        situps=(Button)findViewById(R.id.situps_btn);
        squatiing=(Button)findViewById(R.id.squatting_btn);
        rope=(Button)findViewById(R.id.rope_btn);
        hula=(Button)findViewById(R.id.hula_btn);
        back_btn=(Button)findViewById(R.id.back_btn);
    }
    private void processControllers(){
        run.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Sports_Record.this,Sports_Run.class);
                startActivity(intent);
            }
        });
        bike.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Sports_Record.this, Sports_Bike.class);
                startActivity(intent);
            }
        });
        swim.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Sports_Record.this, Sports_Swim.class);
                startActivity(intent);
            }
        });
        upanddown.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Sports_Record.this, Sports_Upanddown.class);
                startActivity(intent);
            }
        });
        situps.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Sports_Record.this, Sports_Situps.class);
                startActivity(intent);
            }
        });
        squatiing.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Sports_Record.this, Sports_Squatting.class);
                startActivity(intent);
            }
        });
        rope.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Sports_Record.this, Sports_Rope.class);
                startActivity(intent);
            }
        });
        hula.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Sports_Record.this, Sports_Hula.class);
                startActivity(intent);
            }
        });
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Sports_Record.this  , Index_Activity.class);
                startActivity(intent);
            }
        });
    }
}
