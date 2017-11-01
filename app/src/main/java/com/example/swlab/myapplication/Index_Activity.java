package com.example.swlab.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Index_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private Button btnfun1;
    private Button btnfun2;
    private Button btnfun3;
    private Button btnfun4;
    private Button btnfun5;
    private Button btnfun6;
    private DrawerLayout drawer;
    private FirebaseAuth auth;
    private NavigationView navigateionView;
    private Boolean isdoubleClick=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);
        processViews();
        processControllers();
    }
    @Override
    public void onBackPressed() {
        if(!isdoubleClick)
        {
            Toast.makeText(Index_Activity.this,"雙擊以退出",Toast.LENGTH_LONG).show();
            isdoubleClick=true;
        }
        else
        {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
        new CountDownTimer(5000,1000){
            @Override
            public void onTick(long millisUntilFinished) {

            }
            @Override
            public void onFinish() {
                isdoubleClick=false;
            }
        }.start();
    }

    private void processViews(){
        btnfun1=(Button)findViewById(R.id.btn1);
        btnfun2=(Button)findViewById(R.id.btn2);
        btnfun3=(Button)findViewById(R.id.btn3);
        btnfun4=(Button)findViewById(R.id.btn4);
        btnfun5=(Button)findViewById(R.id.btn5);
        btnfun6=(Button)findViewById(R.id.btn6);
        navigateionView=(NavigationView) findViewById(R.id.nav_home);
        navigateionView.setNavigationItemSelectedListener(Index_Activity.this);
        drawer=(DrawerLayout)findViewById(R.id.drawerLayout);
        auth= FirebaseAuth.getInstance();
    }
    private void processControllers(){

        btnfun1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Index_Activity.this,Leisure_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        btnfun2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Index_Activity.this,Mood_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        btnfun3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Index_Activity.this,Music_Activity.class);
                startActivity(intent);
                finish();
            }
        });
        btnfun4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Index_Activity.this,Sports_Activity.class);
                startActivity(intent);
                finish();
            }
        });
        btnfun5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Index_Activity.this,Information_Activity.class);
                startActivity(intent);
                finish();
            }
        });
        btnfun6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Index_Activity.this,Question_Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

        if(id== R.id.Entertainments){
            Intent intent=new Intent();
            intent.setClass(Index_Activity.this,Leisure_Activity.class);
            startActivity(intent);
            finish();
        }
        if(id== R.id.Sport){
            Intent intent=new Intent();
            intent.setClass(Index_Activity.this,Sports_Activity.class);
            startActivity(intent);
            finish();
        }
        if(id== R.id.Music){
            Intent intent=new Intent();
            intent.setClass(Index_Activity.this,Music_Activity.class);
            startActivity(intent);
            finish();
        }
        if(id== R.id.Mood){
            Intent intent=new Intent();
            intent.setClass(Index_Activity.this,Mood_Activity.class);
            startActivity(intent);
            finish();
        }
        if(id== R.id.Question){
            Intent intent=new Intent();
            intent.setClass(Index_Activity.this,Question_Activity.class);
            startActivity(intent);
            finish();
        }
        if(id== R.id.Information){
            Intent intent=new Intent();
            intent.setClass(Index_Activity.this,Information_Activity.class);
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
                    intent.setClass(Index_Activity.this,Main_Activity.class);
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
