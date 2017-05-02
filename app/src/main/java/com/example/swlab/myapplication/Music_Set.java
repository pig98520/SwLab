package com.example.swlab.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class Music_Set extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_set);
        Button setBtn = (Button)findViewById(R.id.back_btn);
        setBtn.setOnClickListener(new View.OnClickListener() {
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
        RadioGroup mode=(RadioGroup) findViewById(R.id.radGroup) ;
        if (mode.getCheckedRadioButtonId()==R.id.normal_radio){

        }
        else{

        }
        EditText hour= (EditText) this.findViewById(R.id.hr_edit);
        hour.requestFocus();
    }
}
