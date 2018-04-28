
package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.NoAvailableVideoEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AdvertisementManager {

    private int timeSeconds;

    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() {
        List<Advertisement> advertisements1 = new ArrayList<>();
        for (Advertisement advertisement : storage.list()) {
            if (advertisement.getHits() > 0) {
                advertisements1.add(advertisement);
            }

        }
        List<Advertisement> advertisements2 = new ArrayList<>();
        chooseAdvertisement(advertisements1, advertisements2, timeSeconds);

        int timeLeft = timeSeconds;
        /*заполнение статистики*/
        long amount = 0;
        int duration = 0;
        for (Advertisement advertisement:advertisements2) {
            amount += advertisement.getAmountPerOneDisplaying();
            duration += advertisement.getDuration();
        }
        StatisticManager.getInstance().register(new VideoSelectedEventDataRow(advertisements2, amount, duration));
        /*конец заполнения статистики*/
        for (Advertisement advertisement : advertisements2) {
            if (timeLeft < advertisement.getDuration()) {
                continue;
            }


//            ConsoleHelper.writeMessage(advertisement.getName());
            ConsoleHelper.writeMessage(advertisement.getName() + " is displaying... "
                    + advertisement.getAmountPerOneDisplaying() + ", "
                    + advertisement.getAmountPerOneDisplaying() * 1000 / advertisement.getDuration());

            timeLeft -= advertisement.getDuration();
            advertisement.revalidate();
        }

        if (timeLeft == timeSeconds) {
            StatisticManager.getInstance().register(new NoAvailableVideoEventDataRow(timeSeconds));
            throw new NoVideoAvailableException();
        }
    }

    private void chooseAdvertisement(List<Advertisement> begin, List<Advertisement> end, int time) {
        if (begin.isEmpty()) {
            return;
        }
        Advertisement chooseAdvertisement;
        Advertisement advertisementAmount = Collections.max(begin, Comparator.comparing(Advertisement::getAmountPerOneDisplaying));
        List<Advertisement> advertisementsAmount = begin.stream()
                .filter(advertisement -> advertisement.getAmountPerOneDisplaying() == advertisementAmount.getAmountPerOneDisplaying())
                .collect(Collectors.toList());
        if (advertisementsAmount.size() > 1) {
            Advertisement advertisementDuration = Collections.max(advertisementsAmount, Comparator.comparing(Advertisement::getDuration));
            List<Advertisement> advertisementsDuration = advertisementsAmount.stream()
                    .filter(advertisement -> advertisement.getDuration() == advertisementDuration.getDuration())
                    .collect(Collectors.toList());
            if (advertisementsDuration.size() > 1) {
                chooseAdvertisement = Collections.max(advertisementsDuration, Comparator.comparing(Advertisement::getHits));
            } else {
                chooseAdvertisement = advertisementDuration;
            }
        } else {
            chooseAdvertisement = advertisementAmount;
        }
        begin.remove(chooseAdvertisement);
        if (time >= chooseAdvertisement.getDuration()) {
            end.add(chooseAdvertisement);
            time -= chooseAdvertisement.getDuration();
        }
        chooseAdvertisement(begin, end, time);
    }
}








/* мое решение не прошло
package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;

import java.util.*;

*/
/*у каждого планшета будет свой объект менеджера, который будет подбирать оптимальный набор роликов и их последовательность для каждого заказа.
Он также будет взаимодействовать с плеером и отображать ролики.*//*

public class AdvertisementManager {

    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException {
        List<List<Advertisement>> listsAds = new ArrayList<>();
        */
/*listsAds заполняется всеми возможными комбинациями видео*//*

        makeVideoList(storage.list(), 0, listsAds);
        */
/*listsAdsResult заполняется всеми возможными комбинациями видео с учетом нужного времени*//*

        List<List<Advertisement>> listsAdsResult = new ArrayList<>(removeExcessVideo(listsAds));
        if (listsAdsResult == null || listsAdsResult.size() == 0) {
            throw new NoVideoAvailableException();
        }
        */
/*сортирую список списков, сначала по цене, затем по макс времени,
        затем по минимальному кол-ву *//*

        Collections.sort(listsAdsResult, new Comparator<List<Advertisement>>() {
            @Override
            public int compare(List<Advertisement> o1, List<Advertisement> o2) {
                return Long.compare(getMaxMoneyList(o1), getMaxMoneyList(o2)) * -1;
            }
        }.thenComparing(new Comparator<List<Advertisement>>() {
            @Override
            public int compare(List<Advertisement> o1, List<Advertisement> o2) {
                return Integer.compare(getTotalTime(o1), getTotalTime(o2)) * -1;
            }
        }.thenComparing(new Comparator<List<Advertisement>>() {
            @Override
            public int compare(List<Advertisement> o1, List<Advertisement> o2) {
                return Integer.compare(o1.size(), o2.size());
            }
        })));
        Collections.sort(listsAdsResult.get(0), new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return Long.compare(o1.getAmountPerOneDisplaying(),o2.getAmountPerOneDisplaying());
            }
        });
        for (Advertisement advertisement: listsAdsResult.get(0)) {
            advertisement.revalidate();
            ConsoleHelper.writeMessage(advertisement.getName());
        }

    }


    */
/*запускать с нулем*//*

    private void makeVideoList(List<Advertisement> videos, int k, List<List<Advertisement>> listsAds) {
        */
/*возвращает список со всеми возможными списками*//*

        if (videos.size() != 0 && videos != null && listsAds.size()==0 && listsAds!= null) {
            */
/*переставляются все элементы в списке*//*

            for (int i = k; i < videos.size(); i++) {
                Collections.swap(videos, i, k);
                makeVideoList(videos, k + 1, listsAds);
                Collections.swap(videos, k, i);
            }
            if (k == videos.size() - 1) {
                listsAds.add(new ArrayList<>(videos));
            }
        }
    }

    private List<List<Advertisement>> removeExcessVideo(List<List<Advertisement>> list) {
        */
/*оставляет видео с нужным временем*//*

        if (list == null || list.size() == 0) {
            return null;
        }
        int tempTime = 0;
        int adTime;
        List<Advertisement> tempList = new ArrayList<>();
        List<List<Advertisement>> listsAdsResult = new ArrayList<>();

        for (List<Advertisement> advertisements : list) {
            for (Advertisement advertisement : advertisements) {
                adTime = advertisement.getDuration();
                if (adTime <= (timeSeconds - tempTime) && advertisement.getHits() > 0) {
                    tempTime += adTime;
                    tempList.add(advertisement);
                }
            }
            listsAdsResult.add(new ArrayList<>(tempList));
            tempTime = 0;
            tempList.clear();
        }
        if (listsAdsResult == null || listsAdsResult.size() == 0) {
            return null;
        }
        return listsAdsResult;
    }


    private long getMaxMoneyList(List<Advertisement> list) {
        */
/*возвращает сумму списка*//*

        int totalAmount = 0;
        if (list == null) {
            return totalAmount;
        }
        for (Advertisement advertisement : list) {
            totalAmount += advertisement.getAmountPerOneDisplaying();
        }
        return totalAmount;
    }


    private int getTotalTime(List<Advertisement> list) {
        */
/*возвращает длительность списка*//*

        int totalTime = 0;
        if (list == null) {
            return totalTime;
        }
        for (Advertisement advertisement : list) {
            totalTime += advertisement.getDuration();
        }
        return totalTime;
    }
}
*/


