package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;
    public static void main(String[] args) {
//        Tablet tablet = new Tablet(5);
//        Cook cook = new Cook("Amigo");
//        StatisticManager.getInstance().register(cook);
//        Waiter waiter = new Waiter();
//        tablet.addObserver(cook);
//        cook.addObserver(waiter);
//        tablet.createOrder();
//
//        DirectorTablet directorTablet = new DirectorTablet();
////        directorTablet.printActiveVideoSet();
////        directorTablet.printAdvertisementProfit();
////        directorTablet.printArchivedVideoSet();
////        directorTablet.printCookWorkloading();
        RandomOrderGeneratorTask task = new RandomOrderGeneratorTask();
        Thread thread = new Thread(task, String.valueOf(ORDER_CREATING_INTERVAL));
        thread.start();
    }
}
