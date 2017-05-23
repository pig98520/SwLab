package com.example.swlab.myapplication;

/**
 * Created by pig98520 on 2017/5/23.
 */

public class DB_Mood_Detiction {
    private String score;
    private String time;

    public DB_Mood_Detiction(){

    }
    public DB_Mood_Detiction(String score, String time) {
        this.score = score;
        this.time = time;
    }
    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
