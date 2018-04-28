package com.javarush.task.task26.task2601;


import java.util.Arrays;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
//        Integer[] i = {1, 3, 5, 7};
//        Integer[] ii = sort(i);
//        for (int l = 0; l < ii.length; l++) {
//            System.out.println(ii[l]);
//        }
    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here
        Comparator<Integer> integerComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        Arrays.sort(array, integerComparator);
        double mediana;
        int arLenght = array.length;
        if (arLenght%2!=0) {
            mediana = array[arLenght / 2];
        } else {
            mediana = (array[arLenght / 2]+array[arLenght / 2]-1)/2;
        }

        Comparator<Integer> medianComporator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Double.compare(Math.abs(mediana - o1),Math.abs(mediana - o2));
            }
        };
        Arrays.sort(array,medianComporator);
//        System.out.println("Median is: " + mediana);
        return array;
    }
}
