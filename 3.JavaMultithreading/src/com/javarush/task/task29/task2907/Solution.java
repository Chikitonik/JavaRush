package com.javarush.task.task29.task2907;

import java.math.BigDecimal;
import java.math.MathContext;

/* 
Этот странный BigDecimal
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getValue(1.1d, 1.2d));
    }

    public static BigDecimal getValue(double v1, double v2) {
        BigDecimal bigDecimal = new BigDecimal(0);
        BigDecimal bigDecimal2 = new BigDecimal(0).add(bigDecimal);
        bigDecimal = BigDecimal.valueOf(v1);
        bigDecimal2 = BigDecimal.valueOf(v2);
        bigDecimal = bigDecimal.add(bigDecimal2);
        return bigDecimal;
    }
}