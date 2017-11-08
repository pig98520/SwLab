package com.example.swlab.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import java.text.DecimalFormat;
import java.util.Calendar;

/**
 * Created by pig98520 on 2017/5/28.
 */

public class AlarmNotificationReceiver extends BroadcastReceiver{
    private DecimalFormat decimalFormat = new DecimalFormat("00");
    private String contentTitle="該運動了喔";
    private String contentText =decimalFormat.format(Calendar.getInstance().getTime().getHours())+":"
            +decimalFormat.format(Calendar.getInstance().getTime().getMinutes())+"到了喔,快點App來運動吧";

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getStringExtra("contentTitle")!=null)
            contentTitle=intent.getStringExtra("contentTitle");

        if(intent.getStringExtra("contentText")!=null)
            contentText =intent.getStringExtra("contentText");

        Intent notifiIntent =new Intent(context,Sports_Activity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notifiIntent, 0); //點擊後回到APP

        NotificationCompat.Builder builder=new NotificationCompat.Builder(context);

        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.icon_man)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setContentIntent(pendingIntent) //點擊後回到APP
                //.setContentInfo("推播資訊")
                .setDefaults(Notification.DEFAULT_LIGHTS|Notification.DEFAULT_SOUND);

        NotificationManager notificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,builder.build());
    }
}
