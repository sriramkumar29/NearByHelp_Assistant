package com.example.nearbyplace_assistant;

public class FireStInfo {
    int fireId;
    String fireName;
    String fireNum;
    double fireLat;
    double fireLng;

    public FireStInfo(int fireId, String fireName, String fireNum, double fireLat, double fireLng) {
        this.fireId = fireId;
        this.fireName = fireName;
        this.fireNum = fireNum;
        this.fireLat = fireLat;
        this.fireLng = fireLng;
    }

    public int getFireId() {
        return fireId;
    }

    public void setFireId(int fireId) {
        this.fireId = fireId;
    }

    public String getFireName() {
        return fireName;
    }

    public void setFireName(String fireName) {
        this.fireName = fireName;
    }

    public String getFireNum() {
        return fireNum;
    }

    public void setFireNum(String fireNum) {
        this.fireNum = fireNum;
    }

    public double getFireLat() {
        return fireLat;
    }

    public void setFireLat(double fireLat) {
        this.fireLat = fireLat;
    }

    public double getFireLng() {
        return fireLng;
    }

    public void setFireLng(double fireLng) {
        this.fireLng = fireLng;
    }

    @Override
    public String toString() {
        return "fireStInfo{" +
                "fireId=" + fireId +
                ", fireName='" + fireName + '\'' +
                ", fireNum='" + fireNum + '\'' +
                ", fireLat=" + fireLat +
                ", fireLng=" + fireLng +
                '}';
    }
}
