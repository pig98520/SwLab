package com.example.swlab.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

public class Music_Set extends AppCompatActivity {
    private Button backBtn;
    private Button saveBtn;
    private EditText hour;
    private EditText min;
    private RadioButton random;
    private RadioButton normal;
    private int time;
    private String mode;
    private FirebaseAuth auth;
    private Firebase firebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_set);
        processView();
        processContral();
        hour.requestFocus();
    }

    private void processView() {
        backBtn = (Button)findViewById(R.id.back_btn);
        saveBtn=(Button)findViewById(R.id.save_btn);
        hour= (EditText) findViewById(R.id.hr_edit);
        min=(EditText)findViewById(R.id.min_edit);
        random=(RadioButton)findViewById(R.id.ram_radio);
        normal=(RadioButton)findViewById(R.id.normal_radio);
        auth= FirebaseAuth.getInstance();
        firebaseRef=new Firebase("https://swlabapp.firebaseio.com/user/musicsetting/"+auth.getCurrentUser().getUid());
    }

    private void processContral() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                switch (Music_Activity.returnFlag)  {
                    case 'r' : {
                        intent.setClass(Music_Set.this, Music_Relax.class);
                        startActivity(intent);
                        break;
                    }
                    case 'c' : {
                        intent.setClass(Music_Set.this, Music_Concentrate.class);
                        startActivity(intent);
                        break;
                    }
                    case 'e' : {
                        intent.setClass(Music_Set.this, Music_Concentrate.class);
                        startActivity(intent);
                        break;
                    }
                    case 's' : {
                        intent.setClass(Music_Set.this, Music_Concentrate.class);
                        startActivity(intent);
                        break;
                    }
                }
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                time=Integer.parseInt(hour.getText().toString().trim())*60
                    +Integer.parseInt(min.getText().toString().trim());
                if(random.isChecked())
                    mode="random";
                else
                    mode="normal";

                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                switch (Music_Activity.returnFlag)  {
                    case 'r' : {
                        intent.setClass(Music_Set.this, Music_Relax.class);
                        startActivity(intent);
                        bundle.putString("mode", mode);
                        bundle.putInt("time",time);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    }
                    case 'c' : {
                        intent.setClass(Music_Set.this, Music_Concentrate.class);
                        bundle.putString("mode", mode);
                        bundle.putInt("time",time);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    }
                    case 'e' : {
                        intent.setClass(Music_Set.this, Music_Concentrate.class);
                        bundle.putString("mode", mode);
                        bundle.putInt("time",time);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    }
                    case 's' : {
                        intent.setClass(Music_Set.this, Music_Concentrate.class);
                        bundle.putString("mode", mode);
                        bundle.putInt("time",time);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    }
                }
            }
        });
    }
}
