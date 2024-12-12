package com.example.nearbyplace_assistant;

public class UserDetail {


    private int id;
    private String name;
    private String email;
    private String phone_no;
    private String phone_no1;
    private String phone_no2;
    private String phone_no3;

    @Override
    public String toString() {
        return "UserDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone_no='" + phone_no + '\'' +
                ", phone_no1='" + phone_no1 + '\'' +
                ", phone_no2='" + phone_no2 + '\'' +
                ", phone_no3='" + phone_no3 + '\'' +
                '}';
    }

    public UserDetail() {

    }

    public UserDetail(int id, String name, String email, String phone_no, String phone_no1, String phone_no2, String phone_no3) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone_no = phone_no;
        this.phone_no1 = phone_no1;
        this.phone_no2 = phone_no2;
        this.phone_no3 = phone_no3;
    }



    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }


    public String getPhone_no1() {
        return phone_no1;
    }

    public void setPhone_no1(String phone_no1) {
        this.phone_no1 = phone_no1;
    }

    public String getPhone_no2() {
        return phone_no2;
    }

    public void setPhone_no2(String phone_no2) {
        this.phone_no2 = phone_no2;
    }

    public String getPhone_no3() {
        return phone_no3;
    }

    public void setPhone_no3(String phone_no3) {
        this.phone_no3 = phone_no3;
    }

}



