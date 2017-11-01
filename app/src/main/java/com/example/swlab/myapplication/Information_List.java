package com.example.swlab.myapplication;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by pig98520 on 2017/5/15.
 */

public class Information_List extends ArrayAdapter<DB_Information>{
    private Activity context;
    private List<DB_Information> questionList;
    private TextView question;
    private TextView answer;

    public Information_List(Activity context, List<DB_Information> informationList){
        super(context,R.layout.question_list,informationList);
        this.context=context;
        this.questionList=informationList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem=inflater.inflate(R.layout.question_list,null,true);

        question=(TextView)listViewItem.findViewById(R.id.question);
        answer=(TextView)listViewItem.findViewById(R.id.answer);
        DB_Information db_information=questionList.get(position);
        question.setText(db_information.getQue());
        answer.setText(db_information.getAns());

        return listViewItem;
    }

}