
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
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.swlab.myapplication.R.layout.question_index;

public class Question_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Button back_btn;
    private Button menu_btn;
    private ListView listview;
    private List<DB_Question> questionList;
    private DatabaseReference databaseRef;
    private DrawerLayout drawer;
    private FirebaseAuth auth;
    private NavigationView navigateionView;
    private boolean isdoubleClick=false;

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Question_Activity.this,Index_Activity.class));
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(question_index);
        processView();
        processControl();
    }

    private void processView() {
        databaseRef= FirebaseDatabase.getInstance().getReference(DB_Question.REF_QUESTION);
        listview=(ListView)findViewById(R.id.listView);
        questionList=new ArrayList<>();
        navigateionView=(NavigationView) findViewById(R.id.nav_home);
        navigateionView.setNavigationItemSelectedListener(Question_Activity.this);
        drawer=(DrawerLayout)findViewById(R.id.drawerLayout);
        back_btn=(Button)findViewById(R.id.back_btn);
        menu_btn=(Button)findViewById(R.id.menu_btn);
        auth= FirebaseAuth.getInstance();
    }

    private void processControl() {
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Question_Activity.this  , Index_Activity.class);
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
    protected void onStart() {
        super.onStart();
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                questionList.clear();
                for(DataSnapshot questionSnapshot : dataSnapshot.getChildren()){
                    DB_Question question=questionSnapshot.getValue(DB_Question.class);
                    questionList.add(question);
                }
                Question_List adapter=new Question_List(Question_Activity.this,questionList);
                listview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

        if(id== R.id.Entertainments){
            Intent intent=new Intent();
            intent.setClass(Question_Activity.this,Leisure_Activity.class);
            startActivity(intent);
            finish();
        }
        if(id== R.id.Sport){
            Intent intent=new Intent();
            intent.setClass(Question_Activity.this,Sports_Activity.class);
            startActivity(intent);
            finish();
        }
        if(id== R.id.Music){
            Intent intent=new Intent();
            intent.setClass(Question_Activity.this,Music_Activity.class);
            startActivity(intent);
            finish();
        }
        if(id== R.id.Mood){
            Intent intent=new Intent();
            intent.setClass(Question_Activity.this,Mood_Activity.class);
            startActivity(intent);
            finish();
        }
        if(id== R.id.Question){
            Intent intent=new Intent();
            intent.setClass(Question_Activity.this,Question_Activity.class);
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
                    intent.setClass(Question_Activity.this,Main_Activity.class);
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
