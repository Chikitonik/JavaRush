package com.javarush.task.task35.task3505;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertableUtil<K, V> {

    public static <K, V extends Convertable> Map convert(List<V> list) {
        Map<K, V> result = new HashMap<>();
        for (V v:list) {
            result.put((K)v.getKey(),v);
        }
        return result;
    }
}
