package com.javarush.task.task27.task2707;

/*
Определяем порядок захвата монитора
*/
public class Solution {
    public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2) {
        synchronized (obj1) {
            synchronized (obj2) {
                System.out.println(obj1 + " " + obj2);
            }
        }
    }

    public static boolean isNormalLockOrder(final Solution solution, final Object o1, final Object o2) throws Exception {
        //do something here
        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                synchronized (o1) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread1 = new Thread(){
            @Override
            public void run() {
                super.run();
                    solution.someMethodWithSynchronizedBlocks(o1, o2);
            }
        };
        thread.start();
        thread.sleep(200);
        thread1.start();
        thread1.sleep(200);
        boolean result;
        synchronized (o2) {
            result = thread1.getState().equals(Thread.State.BLOCKED) ? true : false;
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        final Solution solution = new Solution();
        final Object o1 = new Object();
        final Object o2 = new Object();

        System.out.println(isNormalLockOrder(solution, o1, o2));


    }
}

