package com.javarush.task.task25.task2512;

import java.util.LinkedList;

/*
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        LinkedList<Throwable> throwables = new LinkedList<>();
        throwables.addFirst(e);
        boolean isCause = true;
        while (isCause) {
            if (e.getCause() != null) {
                throwables.addFirst(e.getCause());
                e = e.getCause();
            } else {
                isCause = false;
            }
        }
        for (Throwable th : throwables) {
            System.out.println(th);
        }
    }

    public static void main(String[] args) {
//        Solution solution = new Solution();//
//        Exception ee = new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")));
//        solution.uncaughtException(Thread.currentThread(), ee);
    }

}
