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

public class Sports_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Button record;
    private Button notification;
    private DrawerLayout drawer;
    private FirebaseAuth auth;
    private NavigationView navigateionView;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sports_index);
        processViews();
        processControllers();
    }
    private void processViews(){
        record=(Button)findViewById(R.id.buttonRecord);
        notification=(Button)findViewById(R.id.buttonNotification);
        navigateionView=(NavigationView) findViewById(R.id.nav_home);
        navigateionView.setNavigationItemSelectedListener(Sports_Activity.this);
        drawer=(DrawerLayout)findViewById(R.id.drawerLayout);
        auth= FirebaseAuth.getInstance();
    }
    private void processControllers(){
        record.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Sports_Activity.this,Sports_Content.class);
                startActivity(intent);
            }
        });

        notification.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Sports_Activity.this, Sports_Notification_Activity.class);
                startActivity(intent);
            }
        });
            }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

        if(id== R.id.Entertainments){
            Intent intent=new Intent();
            intent.setClass(Sports_Activity.this,Leisure_Activity.class);
            startActivity(intent);
        }
        if(id== R.id.Sport){
            Intent intent=new Intent();
            intent.setClass(Sports_Activity.this,Sports_Activity.class);
            startActivity(intent);
        }
        if(id== R.id.Music){
            Intent intent=new Intent();
            intent.setClass(Sports_Activity.this,Music_Activity.class);
            startActivity(intent);
        }
        if(id== R.id.Mood){
            Intent intent=new Intent();
            intent.setClass(Sports_Activity.this,Mood_Activity.class);
            startActivity(intent);
        }
        if(id== R.id.Question){
            Intent intent=new Intent();
            intent.setClass(Sports_Activity.this,Question_Activity.class);
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
                    intent.setClass(Sports_Activity.this,Main_Activity.class);
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