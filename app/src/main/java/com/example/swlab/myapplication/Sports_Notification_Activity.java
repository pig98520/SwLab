package com.example.swlab.myapplication;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class Sports_Notification_Activity extends AppCompatActivity {
    private DatePicker datePicker;
    private ListView listview;
    private List<DB_Sports_Notification> notificationList;
    private DB_Sports_Notification_List adapter;
    private FirebaseAuth auth;
    private Firebase databaseRef;
    private Firebase dateRef;
    private Firebase dbRef;
    private Calendar cal;
    private int id;
    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private PendingIntent pendingIntent;
    private Intent intent;
    private AlarmManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sports_notification);
        processView();
        processControl();
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                notificationList.clear();
                for(com.firebase.client.DataSnapshot notifiSnapshot : dataSnapshot.getChildren()){
                    DB_Sports_Notification notifi=notifiSnapshot.getValue(DB_Sports_Notification.class);
                    notificationList.add(notifi);
                }
                adapter=new DB_Sports_Notification_List(Sports_Notification_Activity.this,notificationList);
                listview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        /*databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                notificationList.clear();
                for(DataSnapshot notifiSnapshot : dataSnapshot.getChildren()){
                    DB_Sports_Notification notifi=notifiSnapshot.getValue(DB_Sports_Notification.class);
                    notificationList.add(notifi);
                }
                adapter=new DB_Sports_Notification_List(Sports_Notification_Activity.this,notificationList);
                listview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
    }

    private void processView() {
        datePicker=(DatePicker)findViewById(R.id.datePicker);
        auth= FirebaseAuth.getInstance();
        dbRef=new Firebase("https://swlabapp.firebaseio.com/");
        databaseRef= dbRef.child("user").child("reminder").child(auth.getCurrentUser().getUid());
        listview=(ListView)findViewById(R.id.listView);
        notificationList=new ArrayList<>();
        cal = Calendar.getInstance();
        manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        intent = new Intent(Sports_Notification_Activity.this, AlarmNotificationReceiver.class);
    }

    private void processControl() {
        datePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                       if(dayOfMonth<Calendar.DAY_OF_MONTH)
                        {
                            Toast.makeText(Sports_Notification_Activity.this,"時間過去就回不來了喔~~",Toast.LENGTH_LONG).show();
                        }
                        else {
                                cal.set(Calendar.YEAR, year);
                                cal.set(Calendar.MONTH, monthOfYear);
                                cal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                                showDialog(1);
                        }
                    }
                });

    }
    protected Dialog onCreateDialog(int id){
        if(id==1) {
            return new TimePickerDialog(Sports_Notification_Activity.this, timePickerListener,0,0, true);
        }
        return null;
    }

    protected TimePickerDialog.OnTimeSetListener timePickerListener =new TimePickerDialog.OnTimeSetListener(){
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            cal.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
            cal.set(Calendar.MINUTE, minute);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            if(Calendar.getInstance().getTime().before(cal.getTime())) {
                alarmManager(cal);
                Toast.makeText(Sports_Notification_Activity.this, cal.getTime().toString(), Toast.LENGTH_LONG).show();
            }
        }
    };

    private void alarmManager(Calendar calendarTime) {
        id=(int) System.currentTimeMillis();
        pendingIntent = PendingIntent.getBroadcast(this,id , intent, 0);
        if(calendarTime.before(Calendar.getInstance()))
            Toast.makeText(Sports_Notification_Activity.this,"時間過去就回不來了喔~~",Toast.LENGTH_LONG).show();
        else{
            manager.set(AlarmManager.RTC_WAKEUP, calendarTime.getTimeInMillis(), pendingIntent);
            saveReminder(cal,id);
        }
    }

    private void saveReminder(Calendar cal, int id) {
            databaseRef.child(id+"").setValue(sdf.format(cal.getTime()).toString());
    }
}
