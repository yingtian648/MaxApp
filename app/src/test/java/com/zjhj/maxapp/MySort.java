package com.zjhj.maxapp;

import java.util.*;


public class MySort {
    private static int len = 20;//数组长度
    private static int topNum = 5;//取最大值数量
    private static int count = 0;//计算次数
    private static int[] result = new int[topNum];

    public static void main(String[] args) {
        int[] arr = new int[len];
        //生成原始数据
        Set<Num> sets = new HashSet<>();//set是乱序存储
        List<Num> nums = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            sets.add(new Num(i, i + 1));
        }
        nums.addAll(sets);
        for (int i = 0; i < nums.size(); i++) {
            arr[i] = nums.get(i).getNum();
        }
        createTreeAndCheck(arr);
    }


    //创建树
    private static void createTreeAndCheck(int[] arr) {
        //创建基础二叉树
        for (int i = (arr.length - 1) / 2; i >= 0; i--) {
            checkChangeTree(arr, i, arr.length);
        }
        int topCountIndex = 0;//已挑选出的最大数据集index
        //排序数据集，将最大值放于末尾 → 排除末尾之后的数组进行比较
        for (int i = arr.length - 1; i > 0; i--) {
            //将树顶元素与尾元素进行交换
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            //重新对树结构进行调整 并 将这次的最大值放在父节点
            checkChangeTree(arr, 0, i);
            if (topCountIndex < topNum) {
                result[topCountIndex] = arr[i];
                topCountIndex++;
            } else {
                System.out.println("计算总次数（比较次数+交换次数）：" + count);
                System.out.println(Arrays.toString(result));
                return;
            }
        }
    }

    /**
     * 计算比较数组，取最大值
     * 第一次创建二叉树的时候已经将原始数组按树形排序
     * 第二次到第N次重新对树结构进行调整 并 将这次的最大值放在父节点
     */
    private static void checkChangeTree(int[] arr, int parent, int length) {
        int temp = arr[parent];
        int indexL = 2 * parent + 1; //左节点起点
        while (indexL < length) {
            int indexR = indexL + 1; //右节点
            // 如果有右结点，并且右结点的值大于左结点，则选取右结点
            if (indexR < length && arr[indexL] < arr[indexR]) {
                indexL++;
                count++;//叠加比较次数
            }
            // 如果父结点的值已经大于结点的值，则直接结束
            if (temp >= arr[indexL]) {
                count++;//叠加比较次数
                break;
            }
            count++;//叠加交换次数
            // 把结点的值赋给父结点
            arr[parent] = arr[indexL];
            //选取结点的左结点,继续向下筛选
            parent = indexL;
            indexL = 2 * indexL + 1;//隔2个数据比较
        }
        arr[parent] = temp;
        System.out.println(Arrays.toString(arr));
    }
}
