package com.example.swlab.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.IOException;

public class Music_Sleep extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private MediaPlayer music;
    private Firebase musicFirebaseRef;
    private String musicUrl = " ";
    private Button backBtn;
    private Button setBtn;
    private Button playBtn;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(Music_Sleep.this, Music_Activity.class);
        startActivity(intent);
        music.stop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_sleep);
        Firebase.setAndroidContext(this);
        processView();
        setMusic();
        processControl();
    }
    private void setMusic() {
        music=new MediaPlayer(); //建立一個media player
        musicFirebaseRef=new Firebase("https://swlabapp.firebaseio.com/server/sleep/"+(int) (Math.random()*3+1)); //取得firebase網址 用亂數取得節點網址
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("載入音樂中,請稍後");
        progressDialog.setIcon(R.drawable.loading_24);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressDialog.show();
        musicFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                musicUrl=dataSnapshot.getValue(String.class); //取得節點內的資料
                try {
                    music.setDataSource(musicUrl); //設定media的路徑
                    music.prepare();
                    progressDialog.dismiss();
                } catch (IOException e) {
                    Toast.makeText(Music_Sleep.this,"讀取不到音樂", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void processView() {
        playBtn = (Button) findViewById(R.id.play_btn);
        backBtn = (Button) findViewById(R.id.back_btn);
        setBtn = (Button) findViewById(R.id.set_btn);
        progressDialog = new ProgressDialog(this);
    }

    private void processControl() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Music_Sleep.this, Music_Activity.class);
                startActivity(intent);
                music.stop();
            }
        });
        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Music_Sleep.this, Music_Set.class);
                startActivity(intent);
                music.stop();
            }
        });
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(music.isPlaying()) {
                    music.stop();
                    playBtn.setBackgroundResource(android.R.drawable.ic_media_play);
                    setMusic();
                }
                else {
                    music.start();
                    playBtn.setBackgroundResource(android.R.drawable.ic_media_pause);
                }
            }
        });
    }
}
