package com.example.swlab.myapplication;

/**
 * Created by pig98520 on 2017/9/13.
 */

public class DB_Leisure_Speech {
    private String title;
    private String id;
    private String imageUrl;
    public DB_Leisure_Speech(){

    }


    public DB_Leisure_Speech(String title, String id, String imageUrl) {
        this.title = title;
        this.id = id;
        this.imageUrl=imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
