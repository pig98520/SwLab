package com.example.swlab.myapplication;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

import java.util.List;

/**
 * Created by pig98520 on 2017/8/4.
 */

public class Sports_Notification_List extends ArrayAdapter<DB_Sports_Notification> {
    private Activity context;
    private List<DB_Sports_Notification> notificationList;
    private CheckedTextView date;

    public Sports_Notification_List(Activity context, List<DB_Sports_Notification> notificationList){
        super(context,R.layout.question_list,notificationList);
        this.context=context;
        this.notificationList=notificationList;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem=inflater.inflate(R.layout.sports_notification_list,null,true);

        date=(CheckedTextView)listViewItem.findViewById(R.id.checkedTextView) ;
        DB_Sports_Notification db_sports_notification=notificationList.get(position);
        date.setText(db_sports_notification.getDate());

        return listViewItem;
    }

}