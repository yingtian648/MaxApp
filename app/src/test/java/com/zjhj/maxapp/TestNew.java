package com.zjhj.maxapp;

import java.util.*;

//这里仅存储第二层数据的情况，运行输出结果正常，比较次数是80W次左右
//网上的堆排序是73W次左右
public class TestNew {

    private static int maxValue = 100;//数据最大值
    private static int len = 100;//数组长度
    private static int topNum = 10;//数组长度
    private static int flen = len;//数组长度
    private static int countExechange = 0;//计算次数
    private static int countMax = 0;//计算次数
    private static ArrayList<Num> nums = new ArrayList<>();
    private static ArrayList<Num> rec = new ArrayList<>();
    private static ArrayList<Num> sec;

    public static void main(String[] args) {
        //生成原始数据
        Set<Num> sets = new HashSet<>();
        for (int i = 0; i < len; i++) {
            sets.add(new Num(i, i + 1));
        }
        nums.addAll(sets);
        System.out.println(nums);
        for (int i = 0; i < nums.size(); i++) {
            nums.get(i).setIndex(i);
        }

        //存储第二层数据
        sec = new ArrayList<>();
        for (int i = 0; i < nums.size(); i = i + 2) {//组成基础二叉树 两两配对[大数据集中在前半部分]
            executeData(nums, i);
            sec.add(nums.get(i));
        }

        int f = 1;
        while (flen > 1) {
            f++;
            List<Num> ftemp = new ArrayList<>();
            for (int i = 0; i < nums.size(); i = i + 2) {//组成基础二叉树 两两配对[大数据集中在前半部分]
                executeData(nums, i);
                ftemp.add(nums.get(i));
            }
            flen = (int) Math.floor((double) (ftemp.size()) / 2);
        }

        System.out.println("总层次："+f);
        System.out.println(sec);
//        findTopNum(sec);
        System.out.println("计算比较总次数：比较大小次数：" + countMax + "\t交换次数：" + countExechange);
    }

    private static void findTopNum(List<Num> datas) {
        if (datas.size() == 1) {//找到最大值，放在结果集
            rec.addAll(datas);
            if (rec.size() >= topNum) {//找打了对应数量的值，推出递归
                System.out.println(rec);
                return;
            }
            sec.removeAll(datas);//从第二层移除数据
            //第一层数据在第一次二叉树的时候已经把最大值放于左边，
            // 这里只需从原始二叉树值中添加右侧值就好
            sec.add(nums.get(datas.get(0).index + 1));
            findTopNum(sec);
            return;
        }
        ArrayList<Num> temp = new ArrayList<>();
        for (int i = 0; i < datas.size(); i = i + 2) {//组成基础二叉树 两两配对[大数据集中在前半部分]
            executeData(datas, i);
            temp.add(datas.get(i));
        }
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

