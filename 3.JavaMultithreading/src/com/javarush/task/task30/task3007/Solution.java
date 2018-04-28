package com.javarush.task.task30.task3007;

import java.math.BigDecimal;
import java.math.BigInteger;

/*
Найдем число 2 в максимальной степени
*/
public class Solution {
    public static void main(String[] args) {
//        System.out.println(maxPowerOf2(140_000));   //131072
//        System.out.println(maxPowerOf2(1026));      //1024
//        System.out.println(maxPowerOf2(17));        //16
/*
* >> побитовый сдвиг
* | побитовое OR
* & побитовое AND
* ~ побитовое NOT
* ()=*/

        int i = 5;
        System.out.println("test 100 и 001 " + Integer.toBinaryString(~i));
        System.out.println("test 101 и 001 " + Integer.toBinaryString(~1));
        System.out.println("test 100 и 000 " + Integer.toBinaryString(4&0));
        System.out.println("test 101 и 000 " + Integer.toBinaryString(i&0));
        System.out.println(Integer.toBinaryString(4));
        BigInteger bigInteger = new BigInteger("10000000",2);
        System.out.println(bigInteger);

    }

    public static int maxPowerOf2(int x) {

        return x;
    }
}