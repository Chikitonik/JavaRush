package com.javarush.task.task30.task3013;

/* 
Набираем код
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("expected 1 " + Integer.toString(1, 2));
        System.out.println(Integer.toString(solution.resetLowerBits(1), 2));
        System.out.println(solution.resetLowerBits(1));
        System.out.println("expected 4 " + Integer.toString(4, 2));
        System.out.println(Integer.toString(solution.resetLowerBits(4), 2));
        System.out.println(solution.resetLowerBits(4));
        System.out.println("expected 128 " + Integer.toString(255, 2));
        System.out.println(Integer.toString(solution.resetLowerBits(255), 2));
        System.out.println(solution.resetLowerBits(255));


    }

    public int resetLowerBits(int number) {
        //напишите тут ваш код
        number |= number >> 1;
        return number;
    }
}