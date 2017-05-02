package com.example.swlab.myapplication;

/**
 * Created by swlab on 2017/5/2.
 */

public class DB_Mood_Diary {
    private String moodDate;
    private String Content;

    public DB_Mood_Diary(){
    }

    public DB_Mood_Diary(String moodDate,String Content) {
        this.moodDate = moodDate;
        this.Content = Content;
    }

    public String getmoodDate() {
        return moodDate;
    }

    public void setmoodDate(String moodDate) {
        this.moodDate = moodDate;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }
}

