package com.example.swlab.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;

import java.util.Calendar;

public class Mood_Diary_Activity extends AppCompatActivity {
    private DatePicker date;
    private String strDate;
    private Intent intent;
    private Calendar cal;

    @Override
    public void onBackPressed() {
        intent=new Intent();
        intent.setClass(Mood_Diary_Activity.this, Mood_Activity.class);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mood_diary);
        processView();
        processControl();
    }
    private void processView() {
        date=(DatePicker)findViewById(R.id.datePicker);
        cal = Calendar.getInstance();
        date.init(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH), onDateChanged);
    }

    private void processControl() {

    }
    DatePicker.OnDateChangedListener onDateChanged=new DatePicker.OnDateChangedListener() {
        @Override
        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            strDate=(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
            intent = new Intent();
            intent.setClass(Mood_Diary_Activity.this, Mood_Diary_Check_Activity.class);
            Bundle bundle = new Bundle();
            bundle.putString("date", strDate);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };
}
