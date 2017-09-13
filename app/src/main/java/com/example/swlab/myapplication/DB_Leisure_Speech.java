package com.example.swlab.myapplication;

/**
 * Created by pig98520 on 2017/9/13.
 */

public class DB_Leisure_Speech {
    private String title;
    private String id;

    public DB_Leisure_Speech(){

    }

    public DB_Leisure_Speech(String title, String id) {
        this.title = title;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
