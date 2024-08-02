package com.example.shixun.entity;

public class Trainer {
    private String Tname;
    private int age;
    private String gender;
    private String password;
    private int Tid;
    private String TrainerPic;
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTname() {
        return Tname;
    }

    public void setTname(String tname) {
        Tname = tname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTid() {
        return Tid;
    }

    public void setTid(int tid) {
        Tid = tid;
    }

    public String getTrainerPic() {
        return TrainerPic;
    }

    public void setTrainerPic(String trainerPic) {
        TrainerPic = trainerPic;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "Tname='" + Tname + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", password='" + password + '\'' +
                ", Tid=" + Tid +
                ", TrainerPic='" + TrainerPic + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
