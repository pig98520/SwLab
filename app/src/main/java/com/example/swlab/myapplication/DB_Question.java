package com.example.swlab.myapplication;

/**
 * Created by swlab on 2017/4/30.
 */

public class DB_Question {
    public static final String REF_QUESTION="question";
    private String que;
    private String ans;

    public DB_Question(){

    }

    public DB_Question(String que, String ans) {
        this.que = que;
        this.ans = ans;
    }

    public String getQue() {
        return que;
    }


    public String getAns() {
        return ans;
    }


}
