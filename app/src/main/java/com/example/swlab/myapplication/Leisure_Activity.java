package com.example.swlab.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Leisure_Activity extends Activity implements NavigationView.OnNavigationItemSelectedListener{
    private Button back_btn;
    private Button menu_btn;
    private Button nextPageBtn;
    private DrawerLayout drawer;
    private FirebaseAuth auth;
    private NavigationView navigateionView;
    private boolean isdoubleClick=false;

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Leisure_Activity.this,Index_Activity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leisure_index);
        processViews();
        processControllers();
    }
    private void processViews(){
        navigateionView=(NavigationView) findViewById(R.id.nav_home);
        navigateionView.setNavigationItemSelectedListener(Leisure_Activity.this);
        drawer=(DrawerLayout)findViewById(R.id.drawerLayout);
        back_btn=(Button)findViewById(R.id.back_btn);
        menu_btn=(Button)findViewById(R.id.menu_btn);
        auth= FirebaseAuth.getInstance();
    }
    private void processControllers(){
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
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Leisure_Activity.this  , Index_Activity.class);
                startActivity(intent);
            }
        });
        menu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

        if(id== R.id.Entertainments){
            Intent intent=new Intent();
            intent.setClass(Leisure_Activity.this,Leisure_Activity.class);
            startActivity(intent);
        }
        if(id== R.id.Sport){
            Intent intent=new Intent();
            intent.setClass(Leisure_Activity.this,Sports_Activity.class);
            startActivity(intent);
        }
        if(id== R.id.Music){
            Intent intent=new Intent();
            intent.setClass(Leisure_Activity.this,Music_Activity.class);
            startActivity(intent);
        }
        if(id== R.id.Mood){
            Intent intent=new Intent();
            intent.setClass(Leisure_Activity.this,Mood_Activity.class);
            startActivity(intent);
        }
        if(id== R.id.Question){
            Intent intent=new Intent();
            intent.setClass(Leisure_Activity.this,Question_Activity.class);
            startActivity(intent);
        }
        else if(id==R.id.Logout)
        {
            AlertDialog.Builder logoutDialog=new AlertDialog.Builder(this);
            logoutDialog.setTitle("登出");
            logoutDialog.setMessage("確定要登出?");
            DialogInterface.OnClickListener confirmClick =new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    auth.signOut();
                    Intent intent=new Intent();
                    intent.setClass(Leisure_Activity.this,Main_Activity.class);
                    startActivity(intent);
                }
            };
            DialogInterface.OnClickListener cancelClick =new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            };
            logoutDialog.setNeutralButton("確定",confirmClick);
            logoutDialog.setNegativeButton("取消",cancelClick);
            logoutDialog.show();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
