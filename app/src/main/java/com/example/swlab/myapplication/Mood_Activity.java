package com.example.swlab.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Mood_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private Button back_btn;
    private Button menu_btn;
    private Button decetion;
    private Button choose;
    private Button diary;
    private Button sum;
    private DrawerLayout drawer;
    private FirebaseAuth auth;
    private NavigationView navigateionView;
    private boolean isdoubleClick=false;

    @Override
    public void onBackPressed() {
       startActivity(new Intent(Mood_Activity.this,Index_Activity.class));
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mood_index);
        processViews();
        processControllers();
    }
    private void processViews(){
        decetion=(Button)findViewById(R.id.btnDetection);
        choose =(Button)findViewById(R.id.btnChoose);
        diary=(Button)findViewById(R.id.btnDiary);
        sum=(Button)findViewById(R.id.btnSum);
        navigateionView=(NavigationView) findViewById(R.id.nav_home);
        navigateionView.setNavigationItemSelectedListener(Mood_Activity.this);
        drawer=(DrawerLayout)findViewById(R.id.drawerLayout);
        back_btn=(Button)findViewById(R.id.back_btn);
        menu_btn=(Button)findViewById(R.id.menu_btn);
        auth= FirebaseAuth.getInstance();
    }
    private void processControllers(){
        decetion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Mood_Activity.this,Mood_Detection_Activity.class);
                startActivity(intent);
                finish();
            }
        });

      choose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Mood_Activity.this,Mood_Choose_Activity.class);
                startActivity(intent);
                finish();
            }
        });
        diary.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Mood_Activity.this,Mood_Diary_Activity.class);
                startActivity(intent);
                finish();
            }
        });
        sum.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Mood_Activity.this,Mood_Sum_Activity.class);
                startActivity(intent);
                finish();
            }
        });
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Mood_Activity.this  , Index_Activity.class);
                startActivity(intent);
                finish();
            }
        });
        menu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.END);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

        if(id== R.id.Entertainments){
            Intent intent=new Intent();
            intent.setClass(Mood_Activity.this,Leisure_Activity.class);
            startActivity(intent);
            finish();
        }
        if(id== R.id.Sport){
            Intent intent=new Intent();
            intent.setClass(Mood_Activity.this,Sports_Activity.class);
            startActivity(intent);
            finish();
        }
        if(id== R.id.Music){
            Intent intent=new Intent();
            intent.setClass(Mood_Activity.this,Music_Activity.class);
            startActivity(intent);
            finish();
        }
        if(id== R.id.Mood){
            Intent intent=new Intent();
            intent.setClass(Mood_Activity.this,Mood_Activity.class);
            startActivity(intent);
            finish();
        }
        if(id== R.id.Question){
            Intent intent=new Intent();
            intent.setClass(Mood_Activity.this,Question_Activity.class);
            startActivity(intent);
            finish();
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
                    intent.setClass(Mood_Activity.this,Main_Activity.class);
                    startActivity(intent);
                    finish();
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
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }
}
