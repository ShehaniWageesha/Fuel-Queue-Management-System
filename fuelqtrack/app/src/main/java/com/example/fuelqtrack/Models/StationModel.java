package com.example.fuelqtrack.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StationModel {
    @SerializedName("_id")
    @Expose
    private String _id;

    @SerializedName("stationName")
    @Expose
    private String stationName;

    @SerializedName("stationLocation")
    @Expose
    private String stationLocation;

    @SerializedName("petrol")
    @Expose
    private String petrol;

    @SerializedName("diesel")
    @Expose
    private String diesel;

    @SerializedName("parivalTime")
    @Expose
    private String parivalTime;

    @SerializedName("darivalTime")
    @Expose
    private String darivalTime;

    @SerializedName("pliters")
    @Expose
    private int pliters;

    @SerializedName("dliters")
    @Expose
    private int dliters;

    @SerializedName("pfinishTime")
    @Expose
    private String pfinishTime;

    @SerializedName("dfinishTime")
    @Expose
    private String dfinishTime;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationLocation() {
        return stationLocation;
    }

    public void setStationLocation(String stationLocation) {
        this.stationLocation = stationLocation;
    }

    public String getPetrol() {
        return petrol;
    }

    public void setPetrol(String petrol) {
        this.petrol = petrol;
    }

    public String getDiesel() {
        return diesel;
    }

    public void setDiesel(String diesel) {
        this.diesel = diesel;
    }

    public String getParivalTime() {
        return parivalTime;
    }

    public void setParivalTime(String parivalTime) {
        this.parivalTime = parivalTime;
    }

    public String getDarivalTime() {
        return darivalTime;
    }

    public void setDarivalTime(String darivalTime) {
        this.darivalTime = darivalTime;
    }

    public int getPliters() {
        return pliters;
    }

    public void setPliters(int pliters) {
        this.pliters = pliters;
    }

    public int getDliters() {
        return dliters;
    }

    public void setDliters(int dliters) {
        this.dliters = dliters;
    }

    public String getPfinishTime() {
        return pfinishTime;
    }

    public void setPfinishTime(String pfinishTime) {
        this.pfinishTime = pfinishTime;
    }

    public String getDfinishTime() {
        return dfinishTime;
    }

    public void setDfinishTime(String dfinishTime) {
        this.dfinishTime = dfinishTime;
    }

    @Override
    public String toString() {
        return "StationModel{" +
                "_id='" + _id + '\'' +
                ", stationName='" + stationName + '\'' +
                ", stationLocation='" + stationLocation + '\'' +
                ", petrol='" + petrol + '\'' +
                ", diesel='" + diesel + '\'' +
                ", parivalTime='" + parivalTime + '\'' +
                ", darivalTime='" + darivalTime + '\'' +
                ", pliters=" + pliters +
                ", dliters=" + dliters +
                ", pfinishTime='" + pfinishTime + '\'' +
                ", dfinishTime='" + dfinishTime + '\'' +
                '}';
    }
}
