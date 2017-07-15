package com.example.swlab.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.IOException;

public class Music_Exercise extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private MediaPlayer music;
    private Firebase musicFirebaseRef;
    private String musicUrl = " ";
    private int music_temp;
    private int music_index=1;
    private Button backBtn;
    private Button setBtn;
    private Button playBtn;
    private Button nextBtn;
    private Button priviousBtn;
    private String mode;
    private int time=0;
    private boolean isRandom=false;
    private CountDownTimer countdownTimer;
    private SeekBar seekBar;
    private Handler handler;
    private Runnable updateThread;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(Music_Exercise.this, Music_Activity.class);
        startActivity(intent);
        music.stop();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_exercise);
        Firebase.setAndroidContext(this);
        processView();
        defaulMode();
        setMusic();
        processControl();
    }

    private void defaulMode() {
        Bundle bundle = this.getIntent().getExtras();
        if(bundle!=null) {
            time = bundle.getInt("time");
            mode = bundle.getString("mode");
            if(mode.equals("random"))
                isRandom=true;
            else
                isRandom=false;
        }
        if(time!=0)
            timerStart();
        Log.i("Test",time+"\n"+mode);
    }

    private void timerStart() {
        countdownTimer = new CountDownTimer(time *60* 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if(music.isPlaying())
                    music.stop();
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        };
        countdownTimer.start();
    }

    private void setMusic() {
        if(isRandom)
            music_index=(int) (Math.random()*3+1);

        music=new MediaPlayer(); //建立一個media player
        musicFirebaseRef=new Firebase("https://swlabapp.firebaseio.com/server/exercise/"+music_index); //取得firebase網址 用亂數取得節點網址
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
                    playBtn.setBackgroundResource(android.R.drawable.ic_media_pause);
                    music.start();
                    setSeekbar();
                    handler.post(updateThread);
                } catch (IOException e) {
                    progressDialog.dismiss();
                    Toast.makeText(Music_Exercise.this,"讀取不到音樂", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void setSeekbar() {
        seekBar.setMax(music.getDuration());
        handler=new Handler();
        updateThread=new Runnable() {
            @Override
            public void run() {
                seekBar.setProgress(music.getCurrentPosition());
                handler.postDelayed(updateThread,100);
            }
        };
    }

    private void processView() {
        playBtn = (Button) findViewById(R.id.play_btn);
        nextBtn=(Button)findViewById(R.id.next_btn);
        priviousBtn=(Button)findViewById(R.id.previeous_btn);
        backBtn = (Button) findViewById(R.id.back_btn);
        setBtn = (Button) findViewById(R.id.set_btn);
        progressDialog = new ProgressDialog(this);
    }

    private void processControl() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Music_Exercise.this, Music_Activity.class);
                startActivity(intent);
                music.stop();
            }
        });
        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Music_Activity.returnFlag='e';
                intent.setClass(Music_Exercise.this, Music_Set.class);
                startActivity(intent);
                music.stop();
            }
        });
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(music.isPlaying()) {
                    music.pause();
                    playBtn.setBackgroundResource(android.R.drawable.ic_media_play);
                }
                else {
                    music.start();
                    playBtn.setBackgroundResource(android.R.drawable.ic_media_pause);
                }
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music.stop();
                if(music_index==3)
                    music_index=1;
                else
                    music_index+=1;
                setMusic();
            }
        });
        priviousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music.stop();
                if(music_index==1)
                    music_index=3;
                else
                    music_index-=1;
                setMusic();
            }
        });
    }
}
