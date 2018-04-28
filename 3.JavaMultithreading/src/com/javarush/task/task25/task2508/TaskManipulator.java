package com.javarush.task.task25.task2508;

public class TaskManipulator implements Runnable, CustomThreadManipulator {
    private Thread thread;

    public TaskManipulator() {
        new Thread(this);
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(this.thread.getName());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void start(String threadName) {
        thread = new Thread(this, threadName);
        thread.start();
    }

    @Override
    public void stop() {
//        System.out.printf("Thread %s interrupt\n",this.thread.getName());
            this.thread.interrupt();
        }
}
