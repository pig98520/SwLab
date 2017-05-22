package com.example.swlab.myapplication;

/**
 * Created by Owner on 2017/5/22.
 */

public class QuestionBank {

    private String textQuestions []={
            "1. 我常常覺得想哭",
            "2. 我覺得心情不好",
            "3. 我覺得比以前容易發脾氣",
            "4. 我睡不好",
            "5. 我覺得不想吃東西"
    };

    private  String multipleChoice [][] ={
            {"沒有或極少 (每周: 1天以下)", "有時侯 (每周: 1～2天)", "時常 (每周: 3～4天)", "常常或總是 (每周: 5～7天)"},
            {"沒有或極少 (每周: 1天以下)", "有時侯 (每周: 1～2天)", "時常 (每周: 3～4天)", "常常或總是 (每周: 5～7天)"},
            {"沒有或極少 (每周: 1天以下)", "有時侯 (每周: 1～2天)", "時常 (每周: 3～4天)", "常常或總是 (每周: 5～7天)"},
            {"沒有或極少 (每周: 1天以下)", "有時侯 (每周: 1～2天)", "時常 (每周: 3～4天)", "常常或總是 (每周: 5～7天)"},
            {"沒有或極少 (每周: 1天以下)", "有時侯 (每周: 1～2天)", "時常 (每周: 3～4天)", "常常或總是 (每周: 5～7天)"}
    };

    public int getLength() {return textQuestions.length; }

    public String getQuestion(int a){
        String question = textQuestions[a];
        return question;
    }

    public String getChoice(int index, int num){
        String choice0 = multipleChoice[index] [num-1];
        return choice0;
    }
}
