package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.TestOrder;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet extends Observable {
    private static Logger logger = Logger.getLogger(Tablet.class.getName());
    final int number;


    public Tablet(int number) {
        this.number = number;
    }

    public Order createOrder() {
        Order order = null;
        try {
            order = new Order(this);
            same(order);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        } catch (NoVideoAvailableException n) {
            logger.log(Level.INFO, "No video is available for the order " + order);
        }
        return order;
    }

    private void same(Order order) {
        ConsoleHelper.writeMessage(order.toString()); //добавил сам
        setChanged();
        if (!order.isEmpty()) {
            notifyObservers(order);
        }
        new AdvertisementManager(order.getTotalCookingTime()*60).processVideos();
    }


    @Override
    public String toString() {
        return String.format("Tablet{number=%d}", number);
    }

    public TestOrder createTestOrder() {
        TestOrder order = null;
        try {
            order = new TestOrder(this);
            same(order);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        } catch (NoVideoAvailableException n) {
            logger.log(Level.INFO, "No video is available for the order " + order);
        }
        return order;
    }
}
