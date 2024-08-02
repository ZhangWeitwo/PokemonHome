package com.example.shixun.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PokemonInfo {
    private String Pname;
    private String location;
    private int Pid;
    private String type1;
    private String type2;
    private String imageUrl;
    private String gender;
    private int lev;
    private int flag;
    @JsonProperty("introduction")
    private String introduction;

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    // Getters and Setters
    public String getPname() {
        return Pname;
    }

    public void setPname(String pname) {
        this.Pname = pname;
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
        this.Pid = pid;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getLev() {
        return lev;
    }

    public void setLev(int lev) {
        this.lev = lev;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "PokemonInfo{" +
                "Pname='" + Pname + '\'' +
                ", location='" + location + '\'' +
                ", Pid=" + Pid +
                ", type1='" + type1 + '\'' +
                ", type2='" + type2 + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", gender='" + gender + '\'' +
                ", lev=" + lev +
                ", flag=" + flag +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}