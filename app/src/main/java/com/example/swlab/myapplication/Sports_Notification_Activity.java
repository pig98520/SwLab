package com.example.swlab.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Sports_Notification_Activity extends AppCompatActivity {
    private DatePicker datePicker;
    private Calendar todayDate;
    private int year,month,day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sports_notification);
        processView();
        processControl();
    }

    private void processView() {
        datePicker=(DatePicker)findViewById(R.id.datePicker);
        todayDate = Calendar.getInstance();
        year = todayDate.get(Calendar.YEAR);
        month  = todayDate.get(Calendar.MONTH) + 1;
        day = todayDate.get(Calendar.DAY_OF_MONTH);

    }

    private void processControl() {
        datePicker.init(todayDate.get(Calendar.YEAR),todayDate.get(Calendar.MONTH),
                todayDate.get(Calendar.DAY_OF_MONTH),

                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Toast.makeText(Sports_Notification_Activity.this,year+" "+monthOfYear+" "+dayOfMonth+" ",Toast.LENGTH_LONG).show();
                    }
                });

    }
}
