package com.example.nearbyplace_assistant;

public class PolInfo {
    int polId;
    String polName;
    String polNum;
    double polLat;
    double polLng;

    public PolInfo(int polId, String polName, String polNum, double polLat, double polLng) {
        this.polId = polId;
        this.polName = polName;
        this.polNum = polNum;
        this.polLat = polLat;
        this.polLng = polLng;
    }

    public int getPolId() {
        return polId;
    }

    public void setPolId(int polId) {
        this.polId = polId;
    }

    public String getPolName() {
        return polName;
    }

    public void setPolName(String polName) {
        this.polName = polName;
    }

    public String getPolNum() {
        return polNum;
    }

    public void setPolNum(String polNum) {
        this.polNum = polNum;
    }

    public double getPolLat() {
        return polLat;
    }

    public void setPolLat(double polLat) {
        this.polLat = polLat;
    }

    public double getPolLng() {
        return polLng;
    }

    public void setPolLng(double polLng) {
        this.polLng = polLng;
    }

    @Override
    public String toString() {
        return "PolInfo{" +
                "polId=" + polId +
                ", polName='" + polName + '\'' +
                ", polNum='" + polNum + '\'' +
                ", polLat=" + polLat +
                ", polLng=" + polLng +
                '}';
    }
}
