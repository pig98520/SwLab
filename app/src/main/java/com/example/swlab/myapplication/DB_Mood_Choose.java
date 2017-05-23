package com.example.swlab.myapplication;

/**
 * Created by swlab on 2017/5/12.
 */

public class DB_Mood_Choose {
    private String moods;
    private String time;

    public DB_Mood_Choose(){
    }

    public DB_Mood_Choose(String moods, String time) {
        this.moods = moods;
        this.time = time;
    }

    public String getMoods() {
        return moods;
    }

    public void setMoods(String moods) {
        this.moods = moods;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
