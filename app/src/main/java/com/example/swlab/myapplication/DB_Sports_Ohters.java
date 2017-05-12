package com.example.swlab.myapplication;

/**
 * Created by swlab on 2017/5/1.
 */

public class DB_Sports_Ohters {
    private String count;
    private String sportdate;

    public DB_Sports_Ohters(){
    }

    public DB_Sports_Ohters(String count, String sportdate) {
        this.count = count;
        this.sportdate = sportdate;
    }

    public String getCount() {
        return count;
    }

    public String getSportdate() {
        return sportdate;
    }


}
