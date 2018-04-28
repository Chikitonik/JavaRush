package com.javarush.task.task20.task2025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Алгоритмы-числа
Число S состоит из M цифр, например, S=370 и M (количество цифр) = 3
Реализовать логику метода getNumbers, который должен среди натуральных чисел меньше N (long)
находить все числа, удовлетворяющие следующему критерию:
число S равно сумме его цифр, возведенных в M степень
getNumbers должен возвращать все такие числа в порядке возрастания.

Пример искомого числа:
370 = 3*3*3 + 7*7*7 + 0*0*0
8208 = 8*8*8*8 + 2*2*2*2 + 0*0*0*0 + 8*8*8*8

На выполнение дается 10 секунд и 50 МБ памяти.
*/
public class Solution {
//    public static long[] getNumbers(long N) {
//        long[] result = new long[50];
//        byte t=0;
//        for (long i = 0; i <= N; i++) {
//            if (getNumSum(i,getDegree(i))==i){
//                result[t] = i;
//                t++;
//            }
//        }
//
//        return result;
//    }
//    public static long getNumSum(long N, long degree) {
//        long numSum = 0;
//        for (int i = 0; i < degree; i++) {
//            numSum += Math.pow(N%10,degree);
//            N /=10;
//        }
//        return numSum;
//    }
//
//    public static long getDegree(long N) {
//        //проверить скорость, заменив байт на лонг
//        long p = 10;
//        for (byte i = 1; i < 19; i++) {
//            if (N < p) {
//                return i;
//            } else {
//                p *= 10;
//            }
//        }
//        return 1;
//
//    }
//    }

    private static long cntSearch;
    private static int N;
    private static int[] digitsMultiSet;

    private static List<Long> results;
    private static long maxPow;
    private static long minPow;

    private static long[][] pows;

    private static void genPows(int N) {
        if (N > 20) throw new IllegalArgumentException();
        pows = new long[10][N + 1];
        for (int i = 0; i < pows.length; i++) {
            long p = 1;
            for (int j = 0; j < pows[i].length; j++) {
                pows[i][j] = p;
                p *= i;
            }
        }
    }

    private static boolean check(long pow) {
        cntSearch++;
        if (pow >= maxPow) return false;
        if (pow < minPow) return false;

        int[] testMultiSet = new int[10];

        while (pow > 0) {
            int i = (int) (pow % 10);
            testMultiSet[i]++;
            pow = pow / 10;
        }

        for (int i = 0; i < 10; i++) {
            if (testMultiSet[i] != digitsMultiSet[i]) return false;
        }

        return true;
    }

    private static void search(int digit, int unused, long pow) {
        if (digit == 10) {
            if (check(pow)) results.add(pow);
            return;
        }

        if (digit == 9) {
            digitsMultiSet[digit] = unused;
            search(digit + 1, 0, pow + unused * pows[digit][N]);
        } else {
            for (int i = 0; i <= unused; i++) {
                digitsMultiSet[digit] = i;
                search(digit + 1, unused - i, pow + i * pows[digit][N]);
            }
        }
    }

    public static long[] getNumbers(long N) {
        if (N<=0){
            long[] l = new long[1];
            return l;
        }
        N=String.valueOf(N).length();
        if (N >= 20) throw new IllegalArgumentException();

        genPows((int)N);//
        results = new ArrayList<>();
        digitsMultiSet = new int[10];
        cntSearch = 0;

        for (Solution.N = 1; Solution.N <= N; Solution.N++) {
            minPow = (long) Math.pow(10, Solution.N - 1);
            maxPow = (long) Math.pow(10, Solution.N);

            search(0, Solution.N, 0);
        }

        // System.out.println(cntSearch); // here we print the number of cases calculated

        Collections.sort(results);

        long[] l = new long[results.size()];
        int t = 0;
        for (Long lL : results) {
            l[t]= lL.longValue();
            t++;
        }
        return l;
    }

    public static void main(String[] args) {
//        long start = System.currentTimeMillis();
        long[] list = getNumbers(8208);
//        long finish = System.currentTimeMillis();
//        System.out.println("Time consumed: " + (finish - start) + " ms");
//        System.out.println(list.size());
        for (long l:list) {
            System.out.println(l);
        }
//        System.out.println(list);
    }
}
