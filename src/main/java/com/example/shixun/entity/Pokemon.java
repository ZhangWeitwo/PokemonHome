package com.example.shixun.entity;

public class Pokemon {

    private String Pname;
    private String location;
    private int Pid;
    private String type1;
    private String type2;
    private String imageUrl;
    private String introduction;

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPname() {
        return Pname;
    }

    public void setPname(String pname) {
        Pname = pname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPid() {
        return Pid;
    }

    public void setPid(int pid) {
        Pid = pid;
    }

    public String getType1() {
        return type1;
    }



    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
   @Override
    public String toString() {
        return "Pokemon{" +
                "Pname='" + Pname + '\'' +
                ", location='" + location + '\'' +
                ", Pid=" + Pid +
                ", type1='" + type1 + '\'' +
                ", type2='" + type2 + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}
