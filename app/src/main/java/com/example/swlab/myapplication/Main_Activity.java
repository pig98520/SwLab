package com.example.swlab.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.swlab.myapplication.R.*;

public class Main_Activity extends AppCompatActivity {
    private Button btnLogin;
    private Button btnForget;
    private Button btnRegister;
    private EditText edtUser;
    private EditText edtPsw;
    private String user;
    private String psw;
    private FirebaseAuth auth= FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        processViews();
        processControllers();
    }
    private void processViews(){
        btnLogin=(Button)findViewById(id.btnLogin);
        btnForget=(Button)findViewById(id.btnForget);
        btnRegister=(Button)findViewById(id.btnRegister);
        edtUser=(EditText)findViewById(id.txtID);
        edtPsw=(EditText)findViewById(id.txtPsw);
    }
    private void processControllers(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                user=edtUser.getText().toString();
                psw=edtPsw.getText().toString();
                if(user.equals("")||psw.equals(""))
                    Toast.makeText(Main_Activity.this, "Please enter your ID and Password~", Toast.LENGTH_SHORT).show();
                else
                    login(user,psw);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                user=edtUser.getText().toString();
                psw=edtPsw.getText().toString();
                if(user.equals("")||psw.equals(""))
                    Toast.makeText(Main_Activity.this,  "Please enter your ID and Password~", Toast.LENGTH_SHORT).show();
                else
                    createUser(user,psw);
            }
        });
        btnForget.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(Main_Activity.this, "Are you forget your password?", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.setClass(Main_Activity.this,Question_Activity.class);
                startActivity(intent);
            }
        });
    }

    private void login(final String user, final String psw){
        auth.signInWithEmailAndPassword(user, psw)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(Main_Activity.this,"歡迎回來~   "+user, Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent();
                        intent.setClass(Main_Activity.this,Index_Activity.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Main_Activity.this,"請填入帳號和密碼。", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void createUser(final String user,final String psw) {
        auth.createUserWithEmailAndPassword(user,psw)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(Main_Activity.this,"歡迎加入~ "+user, Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent();
                        intent.setClass(Main_Activity.this,Index_Activity.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Main_Activity.this,"註冊失敗，請檢查帳號是否存在。", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}