package com.javarush.task.task27.task2708;

import java.util.Set;

/* 
Убираем deadLock используя открытые вызовы
*/
public class Solution {
    public static void main(String[] args) {
        //текущее время плюс 5 сек
        final long deadLineTime = System.currentTimeMillis() + 5000; //waiting for 5 sec
//        в realEstate создаются 2 хеш сета
//       1. хеш сет allApartments заполняется 5ю новыми Apartment
//        Apartment это this realEstate и String Lоcation случайное число
//       2. хеш сет activeApartments пустой
        final RealEstate realEstate = new RealEstate();
//        это хеш сет allApartments
        Set<Apartment> allApartments = realEstate.getAllApartments();
//      массив apartments это allApartments
        final Apartment[] apartments = allApartments.toArray(new Apartment[allApartments.size()]);
//      20 раз запускается поток с названием RealEstateThread + i
//      в котором 10 раз в realEstate очищается activeApartments
//           в allApartments случайным образом вносятся Apartments
        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        realEstate.revalidate();
                    }
                }
            }, "RealEstateThread" + i).start();
//      массив apartments заполнятся Apartments
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < apartments.length; i++) {
                        apartments[i].revalidate(true);
                    }
                }
            }, "ApartmentThread" + i).start();
        }
//      если прошло менее 5 секунд, то поток засыпает
//        по истечении 5 секунд печатает The dead lock occurred
//        грубо говоря у программы есть 5 секунд, чтобы отработать
        Thread deamonThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (System.currentTimeMillis() < deadLineTime)
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException ignored) {
                    }
                    System.out.println("The dead lock occurred");
            }
        });
        deamonThread.setDaemon(true);
        deamonThread.start();
    }
}