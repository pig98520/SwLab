package com.example.swlab.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Leisure_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leisure_index);
        Button nextPageBtn;
        nextPageBtn = (Button)findViewById(R.id.exhibition_btn);
        assert nextPageBtn != null;
        nextPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Leisure_Activity.this ,Leisure_Exhibition_Activity.class);
                startActivity(intent);
            }
        });

        nextPageBtn = (Button)findViewById(R.id.theater_btn);
        assert nextPageBtn != null;
        nextPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Leisure_Activity.this ,Leisure_Theater_Activity.class);
                startActivity(intent);
            }
        });


        nextPageBtn = (Button)findViewById(R.id.speech_btn);
        assert nextPageBtn != null;
        nextPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Leisure_Activity.this ,Leisure_Speech_Activity.class);
                startActivity(intent);
            }
        });

        nextPageBtn = (Button)findViewById(R.id.article_btn);
        assert nextPageBtn != null;
        nextPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Leisure_Activity.this ,Leisure_Article_Activity.class);
                startActivity(intent);
            }
        });

    }
}
