package com.example.swlab.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Sports_Run extends AppCompatActivity implements LocationListener {
    private TextView txt_cal;
    private TextView txt_distance;
    private TextView txt_time;
    private SimpleDateFormat dtFormat;
    private String nowTime;
    private Date date;
    private TextView timer;
    private Button finish;
    private int sec = 0;
    private int min = 0;
    private CountDownTimer countdownTimer;
    private Boolean isTimer = false;
    private String cal;
    private String distance="0.0";
    private String time;
    private FirebaseAuth auth;

    private Dialog customDialog;
    private Button confirm;
    private TextView title;
    private TextView message;
    private EditText input;

    private LocationManager lm;
    private Location location;
    private Location startPoint;
    private Location endPoint;
    private String bestProvider = LocationManager.GPS_PROVIDER;
    private boolean getService = false;
    private double longitude_start;
    private double latitude_start;
    private double longitude_end;
    private double latitude_end;
    private boolean isEnd = false;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(Sports_Run.this, Sports_Content.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sports_run);
        processView();
        processControl();
    }


    private void processView() {
        txt_cal = (TextView) findViewById(R.id.txt_cal);
        txt_distance = (TextView) findViewById(R.id.txt_distance);
        txt_time = (TextView) findViewById(R.id.txt_time);
        timer = (TextView) findViewById(R.id.txt_timer);
        finish = (Button) findViewById(R.id.btn_start);
        auth = FirebaseAuth.getInstance();
        dtFormat = new SimpleDateFormat("yyyy/MM/dd");
        date = new Date();
        nowTime = dtFormat.format(date);
        //取得系統定位服務
        LocationManager status = (LocationManager) (this.getSystemService(Context.LOCATION_SERVICE));
        if (status.isProviderEnabled(LocationManager.GPS_PROVIDER) || status.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            //如果GPS或網路定位開啟，呼叫locationServiceInitial()更新位置
            getService = true;
/*            locationServiceInitial();*/
        } else {
            Toast.makeText(this, "請開啟定位服務", Toast.LENGTH_LONG).show();
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));    //開啟設定頁面
        }
    }

    private void locationServiceInitial() {
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);    //取得系統定位服務
        Criteria criteria = new Criteria();    //資訊提供者選取標準
        bestProvider = lm.getBestProvider(criteria, true);    //選擇精準度最高的提供者

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        location = lm.getLastKnownLocation(bestProvider);
        getLocation(location);
    }

    private void getLocation(Location location) {    //將定位資訊顯示在畫面中
        if (location != null) {
            if(!isEnd) {
                longitude_start = location.getLongitude();    //取得經度
                latitude_start = location.getLatitude();    //取得緯度
                Log.i("起點經緯度",longitude_start+" "+latitude_start);
            }
            else{
                longitude_end = location.getLongitude();    //取得經度
                latitude_end = location.getLatitude();    //取得緯度
                Log.i("終點經緯度",longitude_end+" "+latitude_end);
            }

        } else {
            Toast.makeText(this, "無法定位座標", Toast.LENGTH_LONG).show();
        }
    }

    private void processControl() {
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isTimer = false;
                isEnd=true;
                locationServiceInitial();
                timerStop();
            }
        });

        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTimer) {
                    countdownTimer.cancel();
                    isTimer = false;
                } else if (min == 0 && sec == 0) {
                    locationServiceInitial();
                    timerStart();
                    isTimer = true;
                } else {
                    countdownTimer.start();
                    isTimer = true;
                }
            }
        });
    }


    private void timerStart() {
        txt_distance.setText("");
        txt_cal.setText("");
        txt_time.setText("");
        finish.setVisibility(View.VISIBLE);
        countdownTimer=new CountDownTimer(1000000000000L, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                sec++;
                if(sec==60) {
                    min++;
                    sec=0;
                }
                if(min<10&&sec>10)
                    timer.setText("0"+min+":"+sec);
                else if(min>10&&sec<10)
                    timer.setText(min+":0"+sec);
                else if(min<10&sec<10)
                    timer.setText("0"+min+":0"+sec);
                else
                    timer.setText(min+":"+sec);
                finish.setText("結束運動");
            }

            @Override
            public void onFinish() {

            }
        };
        countdownTimer.start();
    }

    private void timerStop() {
        txt_time.setText(timer.getText().toString().trim());
        txt_cal.setText(((min*60+sec)*0.1819)+"");
        timer.setText("00:00");
        min=0;
        sec=0;
        countdownTimer.cancel();
        if(!getService)
            finishDialog();
        else
            calDistance();
    }

    private void finishDialog() {
        customDialog=new Dialog(Sports_Run.this,R.style.DialogCustom);
        customDialog.setContentView(R.layout.custom_dialog_text);
        customDialog.setCancelable(false);
        confirm=(Button)customDialog.findViewById(R.id.confirm);
        confirm.setText("確認");
        title=(TextView)customDialog.findViewById(R.id.title);
        title.setText("結束運動");
        message=(TextView)customDialog.findViewById(R.id.message);
        message.setText("請輸入今天運動的距離吧~");
        input=(EditText)customDialog.findViewById(R.id.editText);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input.getText().toString().trim().equals("")) {
                    Toast.makeText(Sports_Run.this, "請輸入數字~", Toast.LENGTH_LONG).show();
                }
                else{
                    distance = input.getText().toString().trim();
                    cal= txt_cal.getText().toString().trim();
                    time = txt_time.getText().toString().trim();
                    insertData(nowTime, cal, distance, time);
                    finish.setVisibility(View.INVISIBLE);
                    customDialog.dismiss();
                    txt_distance.setText(distance);
                    Toast.makeText(Sports_Run.this, "紀錄已儲存", Toast.LENGTH_LONG).show();
                }

            }
        });
        customDialog.show();
    }

    private void calDistance() {
        startPoint=new Location("locationA");
        startPoint.setLatitude(latitude_start);
        startPoint.setLongitude(longitude_start);

        endPoint=new Location("locationB");
        endPoint.setLatitude(latitude_end);
        endPoint.setLongitude(longitude_end);

        distance=startPoint.distanceTo(endPoint)+"";

        cal= txt_cal.getText().toString().trim();
        time = txt_time.getText().toString().trim();
        insertData(nowTime, cal, distance, time);

        Log.i("距離",distance);
        txt_distance.setText(distance);
        finish.setVisibility(View.VISIBLE);
        Toast.makeText(Sports_Run.this, "紀錄已儲存", Toast.LENGTH_LONG).show();
    }

    private void insertData(String sportDate, String Cal, String Distance, String sportTime){
        Firebase myFirebaseRef = new Firebase("https://swlabapp.firebaseio.com/user");
        Firebase userRef = myFirebaseRef.child("sport").child("run").child(auth.getCurrentUser().getUid().trim());
        DB_Sports_Bike_Run_Swim data = new DB_Sports_Bike_Run_Swim(sportDate,Cal,Distance,sportTime);
        userRef.push().setValue(data);
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}

/*參考資料:
GPS定位取得經緯度:https://www.moke.tw/wordpress/computer/advanced/279
計算距離:https://stackoverflow.com/questions/6981916/how-to-calculate-distance-between-two-locations-using-their-longitude-and-latitu*/