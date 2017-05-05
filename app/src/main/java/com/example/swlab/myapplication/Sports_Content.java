package com.example.swlab.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Sports_Content extends AppCompatActivity {
    private Button run;
    private Button bike;
    private Button swim;
    private Button upanddown;
    private Button situps;
    private Button squatiing;
    private Button rope;
    private Button hula;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sports_content);
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
    }
    private void processControllers(){
        run.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Sports_Content.this,Sports_Run.class);
                startActivity(intent);
            }
        });
        bike.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Sports_Content.this, Sports_Bike.class);
                startActivity(intent);
            }
        });
        swim.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Sports_Content.this, Sports_Swim.class);
                startActivity(intent);
            }
        });
        upanddown.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Sports_Content.this, Sports_Upanddown.class);
                startActivity(intent);
            }
        });
        situps.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Sports_Content.this, Sports_Situps.class);
                startActivity(intent);
            }
        });
        squatiing.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Sports_Content.this, Sports_Squatting.class);
                startActivity(intent);
            }
        });
        rope.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Sports_Content.this, Sports_Rope.class);
                startActivity(intent);
            }
        });
        hula.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Sports_Content.this, Sports_Hula.class);
                startActivity(intent);
            }
        });
    }
}
