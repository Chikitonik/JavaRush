package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.text.SimpleDateFormat;
import java.util.*;


public class StatisticManager {


    private StatisticStorage statisticStorage = new StatisticStorage();

    private Set<Cook> cooks = new HashSet<>();


    public Map<String, Double> getAdsProfit() {
//        Map<String, Double> adsMap = new TreeMap<>(Collections.reverseOrder());
        Map<String, Double> adsMap = new TreeMap<>();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        List<EventDataRow> eventList = statisticStorage.storage.get(EventType.SELECTED_VIDEOS);

        for (EventDataRow event : eventList) {
//            VideoSelectedEventDataRow videoSelectedEventDataRow = (VideoSelectedEventDataRow) event;
//            String date = df.format(videoSelectedEventDataRow.getDate());
//            String date = df.format(videoSelectedEventDataRow.getDate());
//            double money = (double) videoSelectedEventDataRow.getAmount() / 100;
            String date = df.format(event.getDate());
            double money = (double) event.getAmount() / 100;

            if (adsMap.containsKey(date)) adsMap.put(date, adsMap.get(date) + money);
            else adsMap.put(date, money);
        }
        return adsMap;
    }

    public Map<String, Map<String, Integer>> getCooksWorkloading() {

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        Map<String, Map<String, Integer>> loadingMap = new TreeMap<>(Collections.reverseOrder());
        List<EventDataRow> eventList = statisticStorage.storage.get(EventType.COOKED_ORDER);
        for (EventDataRow event : eventList) {
            CookedOrderEventDataRow cookedOrderEvent = (CookedOrderEventDataRow) event;
            String date = df.format(cookedOrderEvent.getDate());
            String cookName = cookedOrderEvent.getCookName();
            int cookingTimeMin = cookedOrderEvent.getTime();


            if (loadingMap.containsKey(date)) {
                Map<String, Integer> temp = loadingMap.get(date);

                if (temp.containsKey(cookName)) {
                    temp.put(cookName, temp.get(cookName) + cookingTimeMin);
                } else {
                    temp.put(cookName, cookingTimeMin);
                }
                loadingMap.put(date, temp);
            } else {

                Map<String, Integer> temp = new TreeMap<>();

                temp.put(cookName, cookingTimeMin);
                loadingMap.put(date, temp);
            }
        }

        return loadingMap;
    }


    private class StatisticStorage {

        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        public StatisticStorage() {

            for (EventType eventType : EventType.values()) {
                storage.put(eventType, new ArrayList<EventDataRow>());
            }


        }

        private void put(EventDataRow data) {
            if (data == null) return;
            storage.get(data.getType()).add(data);

        }


    }

    private static StatisticManager ourInstance = new StatisticManager();

    public static StatisticManager getInstance() {
        return ourInstance;
    }

    public void register(EventDataRow data) {
        statisticStorage.put(data);


    }

    public void register(Cook cook) {
        cooks.add(cook);
    }

    private StatisticManager() {
    }
}
