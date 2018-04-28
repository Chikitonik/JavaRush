package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.List;
import java.util.Locale;
import java.util.Map;


public class DirectorTablet {



    public void printAdvertisementProfit() {
        Locale.setDefault(Locale.ENGLISH);
        Map<String, Double> adsMap = StatisticManager.getInstance().getAdsProfit();
        double totalCounter = 0;

        for (Map.Entry<String, Double> entry : adsMap.entrySet()) {
            totalCounter += entry.getValue();
            ConsoleHelper.writeMessage(String.format("%s - %.2f",entry.getKey(),entry.getValue()));
        }
        ConsoleHelper.writeMessage(String.format("Total - %.2f", totalCounter));

    }

    public void printCookWorkloading() {
        Locale.setDefault(Locale.ENGLISH);
        Map<String, Map<String, Integer>> map;
        map = StatisticManager.getInstance().getCooksWorkloading();

        //     map.putAll(addFakeOrdersData());

        for (Map.Entry<String, Map<String, Integer>> entry1 : map.entrySet()) {

            ConsoleHelper.writeMessage(entry1.getKey());
            for (Map.Entry<String, Integer> entry2 : entry1.getValue().entrySet()) {
                ConsoleHelper.writeMessage(entry2.getKey() + " - " + entry2.getValue() + " min");
            }
        }
    }


    public void printActiveVideoSet() {
        List<Advertisement> list = StatisticAdvertisementManager.getInstance().activeAds();
        if (list!=null&&list.size()!=0) {
            for (Advertisement advertisement : list) {
                ConsoleHelper.writeMessage(String.format("%s - %d", advertisement.getName(), advertisement.getHits()));
            }
        }
    }

    public void printArchivedVideoSet() {
        List<Advertisement> list = StatisticAdvertisementManager.getInstance().inactiveAds();
        if (list!=null&&list.size()!=0) {
            for (Advertisement advertisement : list) {
                ConsoleHelper.writeMessage(advertisement.getName());
            }
        }
    }

    public DirectorTablet() {
    }
}
