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
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;

public class Sports_Notification_Activity extends AppCompatActivity {
    private DatePicker datePicker;
    private Calendar cal;
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

    private void processView() {
        datePicker=(DatePicker)findViewById(R.id.datePicker);
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
            alarmManager(cal);
            if(Calendar.getInstance().getTime().before(cal.getTime()))
                Toast.makeText(Sports_Notification_Activity.this,cal.getTime().toString(),Toast.LENGTH_LONG).show();
        }
    };

    private void alarmManager(Calendar calendarTime) {
        pendingIntent = PendingIntent.getBroadcast(this, (int) System.currentTimeMillis(), intent, 0);
        if(calendarTime.before(Calendar.getInstance()))
        {
            Toast.makeText(Sports_Notification_Activity.this,"時間過去就回不來了喔~~",Toast.LENGTH_LONG).show();
        }
        else
            manager.set(AlarmManager.RTC_WAKEUP, calendarTime.getTimeInMillis(), pendingIntent);
    }
}
