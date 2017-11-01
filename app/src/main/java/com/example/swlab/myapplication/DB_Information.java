package com.example.swlab.myapplication;

/**
 * Created by swlab on 2017/4/30.
 */

public class DB_Information {
    public static final String REF_Information="server/information";
    private String que;
    private String ans;

    public DB_Information(){

    }

    public DB_Information(String que, String ans) {
        this.que = que;
        this.ans = ans;
    }

    public String getQue() {
        return que;
    }


    public String getAns() {
        return ans;
    }

    public void setQue(String que) {this.que = que;}

    public void setAns(String ans) {this.ans = ans;}

}
