package com.example.swlab.myapplication;

/**
 * Created by pig98520 on 2017/5/23.
 */

public class DB_Mood_Detection {
    private String score;
    private String time;

    public DB_Mood_Detection(){

    }
    public DB_Mood_Detection(String score, String time) {
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
