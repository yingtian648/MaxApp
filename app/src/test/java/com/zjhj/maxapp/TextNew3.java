package com.zjhj.maxapp;

import java.util.ArrayList;
import java.util.List;

public class TextNew3 {
    private static int maxValue = 100000;//数据最大值
    private static int len = 100000;//数组长度
    private static int topNum = 100;//取最大值数量
    private static Num temp;
    private static int countExechange = 0;//计算次数
    private static int countMax = 0;//计算次数
    private static int indexsIndex = 0;//组大值原始为止
    private static int[] topNums = new int[topNum];//记录最大值
    private static ArrayList<Num> nums = new ArrayList<>();
    private static ArrayList<Num> rec = new ArrayList<>();

    public static void main(String[] args) {
        //生成原始数据
        for (int i = 0; i < len; i++) {
            nums.add(new Num(i, (int) (Math.random() * maxValue) + 1));
        }
        findTopNum(nums);
        System.out.println("计算比较总次数：比较大小次数：" + countMax + "\t交换次数：" + countExechange);
    }

    private static void findTopNum(List<Num> datas) {
        if (datas.size() == 1) {//找到两个最大值
            rec.addAll(datas);
            if (rec.size() >= topNum) {
                System.out.println(rec);
                return;
            }
            nums.removeAll(datas);
            findTopNum(nums);
            return;
        }
        ArrayList<Num> temp = new ArrayList<>();
        for (int i = 0; i < datas.size(); i = i + 2) {//组成基础二叉树 两两配对[大数据集中在前半部分]
            executeData(datas, i);
            temp.add(datas.get(i));
        }
//        System.out.println(temp);
        findTopNum(temp);
    }

    //计算比较数据核心[组成树形 并把最大值排前面]
    private static void executeData(List<Num> arrs, int i) {
        int left = i;//二叉树左
        int right = i + 1;//二叉树右

        if (right < arrs.size() && arrs.get(left).getNum() < arrs.get(right).getNum()) {
            countMax++;
            exchange(arrs, i, right);
        }
    }

    //数据交换
    private static void exchange(List<Num> nums, int i, int j) {
        countExechange++;
        int temp = i;
        nums.add(i, nums.get(j));
        nums.remove((i + 1));
        nums.add(j, nums.get(temp));
        nums.remove((j + 1));
    }
}
