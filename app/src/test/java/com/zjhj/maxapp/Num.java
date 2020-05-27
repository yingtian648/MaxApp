package com.zjhj.maxapp;

public class Num {
    int index;
    int num;

    public Num(int index, int num) {
        this.index = index;
        this.num = num;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "" + num;
    }
}
