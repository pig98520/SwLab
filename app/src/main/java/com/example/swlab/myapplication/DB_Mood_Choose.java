package com.example.swlab.myapplication;

import android.widget.ImageButton;

/**
 * Created by swlab on 2017/5/12.
 */

public class DB_Mood_Choose {
    private ImageButton angry;
    private ImageButton happy;
    private ImageButton heart;
    private ImageButton laugh;
    private ImageButton sad;
    private ImageButton surprise;
    private String moods;

    public DB_Mood_Choose(){
    }

    public DB_Mood_Choose(ImageButton angry,ImageButton happy,ImageButton heart,ImageButton laugh,ImageButton sad,ImageButton surprise,String moods) {
        this.angry = angry;
        this.happy = happy;
        this.heart = heart;
        this.laugh = laugh;
        this.sad = sad;
        this.surprise = surprise;
        this.moods = moods;

    }

    public ImageButton getAngry() {
        return angry;
    }

    public void setAngry( ImageButton getAngry) {
        this.angry = angry;
    }

    public ImageButton gethappy() {
        return happy;
    }

    public void sethappy( ImageButton gethappy) {
        this.happy = happy;
    }

    public ImageButton getheart() {
        return heart;
    }

    public void setheart( ImageButton getheart) {
        this.heart = heart;
    }

    public ImageButton getlaugh() {
        return laugh;
    }

    public void setlaugh( ImageButton getlaugh) {
        this.laugh = laugh;
    }

    public ImageButton getsad() {
        return sad;
    }

    public void setsad( ImageButton getsad) {
        this.sad = sad;
    }

    public ImageButton getsurprise() {
        return surprise;
    }

    public void setsurprise( ImageButton getsurprise) {this.surprise = surprise;}

    public String getmoods() {
        return moods;
    }

    public void setmoods(String moods) {
        this.moods = moods;
    }

}
