package com.example.swlab.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.swlab.myapplication.R.id;
import static com.example.swlab.myapplication.R.layout;

public class Main_Activity extends AppCompatActivity {
    private Button btnLogin;
    private Button btnForget;
    private Button btnRegister;
    private EditText edtUser;
    private EditText edtPsw;
    private String user;
    private String psw;
    private FirebaseAuth auth= FirebaseAuth.getInstance();
    private boolean isdoubleClick=false;

    @Override
    public void onBackPressed() {
        if(!isdoubleClick)
        {
            Toast.makeText(Main_Activity.this,"雙擊以退出",Toast.LENGTH_LONG).show();
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
                    Toast.makeText(Main_Activity.this, "請帳號或密碼檢查是否有誤", Toast.LENGTH_SHORT).show();
                else
                    login(user,psw);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                user=edtUser.getText().toString();
                psw=edtPsw.getText().toString();
                if(user.equals("")||psw.equals(""))
                    Toast.makeText(Main_Activity.this,  "請檢察帳號或密碼是否有誤", Toast.LENGTH_SHORT).show();
                else
                    createUser(user,psw);
            }
        });
        btnForget.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            forgetDialog();
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
                        Toast.makeText(Main_Activity.this,"請檢察帳號和密碼。。", Toast.LENGTH_SHORT).show();
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
                        if(!isEmailValid(user))
                            Toast.makeText(Main_Activity.this,"帳號必須為電子郵件。", Toast.LENGTH_SHORT).show();
                        else if(psw.length()<6)
                            Toast.makeText(Main_Activity.this,"密碼至少為六碼。", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(Main_Activity.this,"註冊失敗，請檢查帳號是否已存在。", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void forgetDialog() {
        AlertDialog.Builder forgetDialog=new AlertDialog.Builder(this);
        forgetDialog.setTitle("忘記密碼");
        forgetDialog.setMessage("是否發送忘記密碼E-mail聯絡客服?\n\n"+"若無安裝郵件軟體，請寫信到swlabgroup2@gmail.com");
        DialogInterface.OnClickListener confirmClick =new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent sendMail = new Intent(Intent.ACTION_SEND);
                sendMail.setType("message/rfc822");
                sendMail.putExtra(Intent.EXTRA_EMAIL  , new String[]{"swlabgroup2@gmail.com"});
                sendMail.putExtra(Intent.EXTRA_SUBJECT, "我忘記我的APP密碼");
                sendMail.putExtra(Intent.EXTRA_TEXT   , "請完成以下資料，我們將盡快發送新的密碼至您的Mail\n\n\n" +
                        "--------------------------------------------------\n"+
                        "姓名:\n\n"+
                        "郵件:\n\n"+
                        "備用郵件:\n\n"+
                        "--------------------------------------------------"+
                        "\n\n\n感謝您的填寫，我們將盡快為您處理");
                try {
                    startActivity(Intent.createChooser(sendMail, "郵件傳送中..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(Main_Activity.this, "傳送郵件失敗", Toast.LENGTH_SHORT).show();
                }
            }
        };
        DialogInterface.OnClickListener cancelClick =new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        };
        forgetDialog.setNeutralButton("是",confirmClick);
        forgetDialog.setNegativeButton("否",cancelClick);
        forgetDialog.show();
    }

}