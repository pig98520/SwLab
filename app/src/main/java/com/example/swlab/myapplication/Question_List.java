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

public class Question_List extends ArrayAdapter<DB_Question>{
    private Activity context;
    private List<DB_Question> questionList;
    private TextView question;
    private TextView answer;

    public Question_List(Activity context, List<DB_Question> questionList){
        super(context,R.layout.question_list,questionList);
        this.context=context;
        this.questionList=questionList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem=inflater.inflate(R.layout.question_list,null,true);

        question=(TextView)listViewItem.findViewById(R.id.question);
        answer=(TextView)listViewItem.findViewById(R.id.answer);
        DB_Question db_question=questionList.get(position);
        question.setText(db_question.getQue());
        answer.setText(db_question.getAns());

        return listViewItem;
    }

}