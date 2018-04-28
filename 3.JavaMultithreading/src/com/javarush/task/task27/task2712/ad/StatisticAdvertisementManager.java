package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager instance = new StatisticAdvertisementManager();
    private AdvertisementStorage storage = AdvertisementStorage.getInstance();

    public static StatisticAdvertisementManager getInstance() {
        return instance;
    }

    private StatisticAdvertisementManager() {
    }

    public List<Advertisement> activeAds (){
        List<Advertisement> advertisements = storage.list();
        List<Advertisement> result = new ArrayList<>();
        if (advertisements==null||advertisements.size()==0){
            return null;
        }
        for (Advertisement ad:advertisements) {
            if (ad.getHits()>0) {
                result.add(ad);
            }
        }
        Collections.sort(result, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        return result!=null? result.size()!=0 ? result : null : null;
    }
    public List<Advertisement> inactiveAds () {
        List<Advertisement> advertisements = storage.list();
        List<Advertisement> result = new ArrayList<>();
        if (advertisements==null||advertisements.size()==0){
            return null;
        }
        for (Advertisement ad:advertisements) {
            if (ad.getHits()<=0) {
                result.add(ad);
            }
        }
        Collections.sort(result, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        return result!=null? result.size()!=0 ? result : null : null;
    }
}
