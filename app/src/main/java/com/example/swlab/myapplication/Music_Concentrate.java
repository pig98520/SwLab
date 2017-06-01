package com.example.swlab.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.IOException;

public class Music_Concentrate extends AppCompatActivity {

    private MediaPlayer music;
    private Firebase musicFirebaseRef;
    private String musicUrl = " ";
    private Button backBtn;
    private Button setBtn;
    private ImageButton playBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_concentrate);
        Firebase.setAndroidContext(this);
        processView();
        setMusic();
        processControl();
    }

    private void setMusic() {
        music=new MediaPlayer(); //建立一個media player
        musicFirebaseRef=new Firebase("https://swlabapp.firebaseio.com/server/relax/"+(int) (Math.random()*5+1)); //取得firebase網址 用亂數取得節點網址

        musicFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                musicUrl=dataSnapshot.getValue(String.class); //取得節點內的資料
                try {
                    music.setDataSource(musicUrl); //設定media的路徑
                    music.prepare();
                } catch (IOException e) {
                    Toast.makeText(Music_Concentrate.this,"讀取不到音樂", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void processView() {
        playBtn = (ImageButton) findViewById(R.id.play_btn);
        backBtn = (Button) findViewById(R.id.back_btn);
        setBtn = (Button) findViewById(R.id.set_btn);
    }

    private void processControl() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Music_Concentrate.this, Music_Activity.class);
                startActivity(intent);
                music.stop();
            }
        });
        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Music_Concentrate.this, Music_Set.class);
                startActivity(intent);
                music.stop();
            }
        });
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(music.isPlaying()) {
                    music.stop();
                    setMusic();
                }
                else {
                    music.start();
                }
            }
        });
    }
}
