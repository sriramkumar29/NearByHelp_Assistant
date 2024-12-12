package com.example.nearbyplace_assistant;

public class HosInfo {

    @Override
    public String toString() {
        return "HosInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }

    private int id;
    private String name;
    private String number;
    private double lat;
    private double lng;


    public HosInfo(int id, String name, String number, double lat, double lng) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.lat = lat;
        this.lng = lng;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

}
