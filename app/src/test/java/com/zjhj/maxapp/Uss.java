package com.zjhj.maxapp;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Uss implements Comparable<Uss> {

    private String name;
    private int agel;

    public Uss(String name, int agel) {
        this.name = name;
        this.agel = agel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAgel() {
        return agel;
    }

    public void setAgel(int agel) {
        this.agel = agel;
    }

    @Override
    public int compareTo(Uss o) {
        if (this.agel > o.agel)
            return 1;
        else {
            return -1;
        }
    }
}
