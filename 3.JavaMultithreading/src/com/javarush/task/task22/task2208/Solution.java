package com.javarush.task.task22.task2208;

import java.util.LinkedHashMap;
import java.util.Map;

/*
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {
        Map<String, String> data = new LinkedHashMap<>();
        data.put("name", "Ivanov");
        data.put("country", "Ukraine");
        data.put("city", "Kiev");
        data.put("age", "10");
        System.out.println(getQuery(data));
    }
    public static String getQuery(Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> m: params.entrySet()
             ) {
            if (m.getValue()!=null) {
                if (stringBuilder.length()==0){
                    stringBuilder.append(m.getKey() + " = '" + m.getValue()+"'");
                } else {
                    stringBuilder.append(" and " + m.getKey() + " = '" + m.getValue()+"'");
                }
            }
        }
        return stringBuilder.toString();
    }
}

