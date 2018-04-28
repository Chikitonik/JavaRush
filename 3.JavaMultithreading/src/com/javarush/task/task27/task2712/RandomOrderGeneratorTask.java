package com.javarush.task.task27.task2712;

import java.util.ArrayList;
import java.util.List;

public class RandomOrderGeneratorTask implements Runnable {
    private List<Tablet> tablets = new ArrayList<>();
    @Override
    public void run() {
        /*for testing below*/
        for (int i = 0; i < 5; i++) {
            tablets.add(new Tablet(i));
        }
        /*for testing upper*/
        int randomNumOftablets = (int)Math.floor(Math.random()*(tablets.size()));
        int interval = Integer.parseInt(Thread.currentThread().getName());
        while (true){
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tablets.get(randomNumOftablets).createTestOrder();
        }
    }
}
