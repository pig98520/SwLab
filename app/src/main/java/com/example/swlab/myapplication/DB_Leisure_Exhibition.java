package com.example.swlab.myapplication;

/**
 * Created by pig98520 on 2017/5/21.
 */

public class DB_Leisure_Exhibition {
    private String title;
    private String content;
    private String imageUrl;

    public DB_Leisure_Exhibition(){

    }
    public DB_Leisure_Exhibition(String title, String content, String imageUrl) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
