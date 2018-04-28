package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a = new byte[][]{
                {1, 1, 0, 0, 0, 0},
                {1, 1, 0, 1, 1, 0},
                {0, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 0},
                {1, 1, 0, 1, 0, 1},
        };
        int count = getRectangleCount(a);
        System.out.println("count = " + count);
    }

    public static int getRectangleCount(byte[][] a) {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
/*
определение левого верхнего угла, условия:
текущая равна единице, а также:
слева граница, сверху граница//
слева ноль, сверху граница//
слева граница, сверху ноль//
слева ноль, сверху ноль//
 */
                    if (a[i][j] == 1) {
                    if (j - 1 < 0 && i - 1 < 0) {
                        count++;
                    }
                        if (i-1>=0) {
                            if (j - 1 < 0 && a[i - 1][j] == 0) {
                                count++;
                            }
                        }
                        if (j-1>=0) {
                            if (a[i][j - 1] == 0&&i - 1 < 0) {
                                    count++;
                            }
                        }
                        if (i-1>=0&&j-1>=0) {
                            if (a[i-1][j] == 0 && a[i][j-1]==0) {
                            count++;
                        }
                        }

                    }
            }
        }

//        for (int i = 0; i < a.length; i++) {
//            for (int j = 0; j < a[i].length; j++) {
//                System.out.print(a[i][j]);
//            }
//            System.out.println();
//        }
        return count;
    }
}
