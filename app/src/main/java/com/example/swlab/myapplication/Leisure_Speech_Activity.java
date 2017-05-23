package com.example.swlab.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class Leisure_Speech_Activity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    public static final String API_KEY = "AIzaSyCSoju_ikeadgsz-TL4ipk5AI1bQSw0wSQ";
    //public static final String VIDEO_ID="_kOyO5Jg8wk";
    private String videoArray[] = new String[]
            {"NLElzEJPceA",
                    "uiJ4zibW8_M",
                    "ITxWUu6UcWQ&t=3s",
                    "zxeR614s1mE",
                    "_RTsJGwt_5c",
                    "tmYa6ubl4Ew",
                    "Ns6xmlkjtbg",
                    "vFWpKOlpLnQ"};
    private YouTubePlayerView youTubePlayerView;
    private Spinner spinner;
    private Button confirm;
    private ArrayAdapter<String> adapter;
    private Bundle bundle;
    private int videoIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leisure_article);
        processView();
        processControl();
    }

    private void processView() {
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
        bundle = getIntent().getExtras();
        youTubePlayerView.initialize(API_KEY, Leisure_Speech_Activity.this);
        spinner = (Spinner) findViewById(R.id.spinner);
        confirm = (Button) findViewById(R.id.btn_confirm);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, videoArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(selectListener);
    }

    private void processControl() {
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("video_id", spinner.getSelectedItemPosition() + "");
                Intent intent = new Intent();
                //設定下一個Actitity
                intent.setClass(Leisure_Speech_Activity.this, Leisure_Speech_Activity.class);
                intent.putExtras(bundle);
                //開啟Activity
                startActivity(intent);
            }
        });
    }

    private AdapterView.OnItemSelectedListener selectListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        youTubePlayer.setPlaybackEventListener(playbackEventListener);
        if (!b) {
            if (bundle != null) {
                videoIndex = Integer.parseInt(bundle.getString("video_id"));
                youTubePlayer.cueVideo(videoArray[videoIndex]);
            } else
                youTubePlayer.cueVideo(videoArray[0]);
        }
    }


    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Initial Failed~", Toast.LENGTH_LONG).show();
    }

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {

        }

        @Override
        public void onVideoStarted() {

        }

        @Override
        public void onVideoEnded() {

        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };

    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {

        }

        @Override
        public void onPaused() {

        }

        @Override
        public void onStopped() {

        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };
}