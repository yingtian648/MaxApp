package com.zjhj.maxapp;

import java.util.Arrays;

public class TestAny {

    private static int maxValue = 100;//数据最大值
    private static int len = 100;//数组长度
    private static int topNum = 10;//取最大值数量
    private static int temp;
    private static int count = 0;//计算次数

    public static void main(String[] args) {
        int findOutCount = 0;
        int[] arrs = new int[len];
        for (int i = 0; i < len; i++) {
            arrs[i] = (int) (Math.random() * maxValue);
        }
        System.out.println(Arrays.toString(arrs));

        for (int i = len / 2 - 1; i > 0; i--) {//组成基础二叉树 两两配对[大数据集中在前半部分]
            executeData(arrs, i);
        }

        for (int i = arrs.length - 1; i > 0; i--) {
            executeData(arrs, 0);
            len--;//找到一个则长度减1
        }
        System.out.println(Arrays.toString(Arrays.copyOf(arrs, topNum)));
        System.out.println("计算比较总次数：" + count);
    }

    //计算比较数据核心[组成树形 并把最大值排前面]
    private static void executeData(int[] arrs, int i) {
        int left = 2 * i + 1;//二叉树左
        int right = 2 * i + 2;//二叉树右
        int largest = i;//存储最大值

        if (left < len && arrs[left] > arrs[largest]) {
            largest = left;
            count++;
        }

        if (right < len && arrs[right] > arrs[largest]) {
            largest = right;
            count++;
        }
        if (largest != i) {
            exchange(arrs, i, largest);
            executeData(arrs, largest);
        }
    }

    //数据交换
    private static void exchange(int[] arrs, int i, int j) {
        count++;
        temp = arrs[i];
        arrs[i] = arrs[j];
        arrs[j] = temp;
    }
}
