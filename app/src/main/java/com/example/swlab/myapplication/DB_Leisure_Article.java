package com.example.swlab.myapplication;

/**
 * Created by user on 2017/8/28.
 */

public class DB_Leisure_Article {
    private String title;
    private String content;
    private String imageUrl;
    public DB_Leisure_Article(){

    }
    public DB_Leisure_Article(String title, String content, String imageUrl) {
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
