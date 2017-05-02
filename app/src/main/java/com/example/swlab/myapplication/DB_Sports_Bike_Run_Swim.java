package com.example.swlab.myapplication;

import java.sql.Time;

/**
 * Created by swlab on 2017/4/30.
 */

public class DB_Sports_Bike_Run_Swim {
    private String sportdate;
    private String cal;
    private String distance;
    private String sporttime;

    public DB_Sports_Bike_Run_Swim(){
    }

    public DB_Sports_Bike_Run_Swim(String sportdate, String cal, String distance, String sporttime) {
        this.sportdate = sportdate;
        this.cal = cal;
        this.distance = distance;
        this.sporttime = sporttime;
    }

    public String getSportdate() {
        return sportdate;
    }

    public void setSportdate(String sportdate) {
        this.sportdate = sportdate;
    }

    public String getCal() {
        return cal;
    }

    public void setCal(String cal) {
        this.cal = cal;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getSporttime() {
        return sporttime;
    }

    public void setSporttime(String sporttime) {
        this.sporttime = sporttime;
    }

}
