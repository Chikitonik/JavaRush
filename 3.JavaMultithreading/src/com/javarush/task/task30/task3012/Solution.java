package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private int[] ints = {1, 3, 9, 27, 81, 243, 729, 2187};
    private int num = 74;
    private int sum = 0;
    int fact = 1;
    int temp =1;
    List<int[]> list = new ArrayList<>();

    public static void main(String[] args) {
        Solution solution = new Solution();
//        solution.createExpression(74);
//        solution.recursion(0);
//        System.out.println(solution.factorial(0));
        System.out.println(solution.factorialRecursion(7));
    }

    private int factorial(int i) {
        int result = 1;
        for (int j = 1; j <= i; j++) {
            result *=j;
        }
        return result;
    }

    private int factorialRecursion(int i) {
       int result;
        if (i==1) {
            return i;
        }
        result = factorialRecursion(i-1)*i;
        return result;
    }

    public void createExpression(int number) {
        //напишите тут ваш код
        /* список из трех
         * 1. пробую все сложить
         * 2. умножаю на 1 первое значение в списке
         * 3. пробую все сложить
         * 4. умножаю на 1 второй
         * 5. пробую сложить
         * 6. умножаю на 1 третий
         * 7. пробую сложить*/
    }

    public int recursion(int i) {
        //вызывать с нулем рекурсию
        //массив сделать {1, 3, 9, 27, 81, 243, 729, 2187, -1, -3, -9, -27, -81, -243, -729, -2187}
        //как подсчитать возможное кол-во комбинаций - list.size*(list.size-1) НЕ ПРАВИЛЬНО?
        if (i >= ints.length) {
            System.out.println("Решение не было найдено");

            return 0;
        }
        for (int j = 0; j < i; j++) {
            sum += ints[j];
        }
        if (sum==num) {
            System.out.println("Решение найдено");
            return i;
        }
        sum = 0;
        recursion(++i);
        return i;
    }

    public List<int[]> getAllVariants(int[] ints) {
        list.add(ints);

        return list;
    }
}