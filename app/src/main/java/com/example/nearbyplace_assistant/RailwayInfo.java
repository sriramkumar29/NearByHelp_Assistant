package com.example.nearbyplace_assistant;

public class RailwayInfo {
    int railId;
    String railName;
    String railNum;
    double railLat;
    double railLng;

    public RailwayInfo(int railId, String railName, String railNum, double railLat, double railLng) {
        this.railId = railId;
        this.railName = railName;
        this.railNum = railNum;
        this.railLat = railLat;
        this.railLng = railLng;
    }

    public int getRailId() {
        return railId;
    }

    public void setRailId(int railId) {
        this.railId = railId;
    }

    public String getRailName() {
        return railName;
    }

    public void setRailName(String railName) {
        this.railName = railName;
    }

    public String getRailNum() {
        return railNum;
    }

    public void setRailNum(String railNum) {
        this.railNum = railNum;
    }

    public double getRailLat() {
        return railLat;
    }

    public void setRailLat(double railLat) {
        this.railLat = railLat;
    }

    public double getRailLng() {
        return railLng;
    }

    public void setRailLng(double railLng) {
        this.railLng = railLng;
    }

    @Override
    public String toString() {
        return "RailwayInfo{" +
                "railId=" + railId +
                ", railName='" + railName + '\'' +
                ", railNum='" + railNum + '\'' +
                ", railLat=" + railLat +
                ", railLng=" + railLng +
                '}';
    }
}
