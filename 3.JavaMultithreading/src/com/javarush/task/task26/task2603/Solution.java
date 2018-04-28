package com.javarush.task.task26.task2603;

import java.util.Comparator;

/*
Убежденному убеждать других не трудно
*/
public class Solution {
    public static class CustomizedComparator<T> implements Comparator<T>{
        private Comparator<T>[] comparators;

        public CustomizedComparator(Comparator<T>... comparators) {
            this.comparators = comparators;
        }
        public int compare(T o1, T o2) {
            int r = 0;
            for (Comparator comparator :
                    comparators) {
                r = comparator.compare(o1, o2);
                if (r!=0) break;
            }
            return r;
        }
    }
    public static void main(String[] args) {

    }
}
