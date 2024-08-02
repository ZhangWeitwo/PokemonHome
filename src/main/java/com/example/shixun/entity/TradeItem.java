package com.example.shixun.entity;

public class TradeItem {
    private int propid;
    private String propName;
    private String ownerName;
    private int price;
    private String special;
    private int num;

    public int getPropid() {
        return propid;
    }

    public void setPropid(int propid) {
        this.propid = propid;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
