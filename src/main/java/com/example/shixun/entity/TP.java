package com.example.shixun.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.util.List;

public class TP {
    private int flag;
    private int Tid;
    private int Pid;
    private int statement;
    private int lev;
    private String gender;

    //描述训练师所有的宝可梦
    @TableField(exist = false)
    private List<Pokemon> pokemons;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getTid() {
        return Tid;
    }

    public void setTid(int tid) {
        Tid = tid;
    }

    public int getPid() {
        return Pid;
    }

    public void setPid(int pid) {
        Pid = pid;
    }

    public int getStatement() {
        return statement;
    }

    public void setStatement(int statement) {
        this.statement = statement;
    }

    public int getLev() {
        return lev;
    }

    public void setLev(int lev) {
        this.lev = lev;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

}
