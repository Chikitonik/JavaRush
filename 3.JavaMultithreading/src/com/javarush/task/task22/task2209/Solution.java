package com.javarush.task.task22.task2209;

import jdk.nashorn.internal.runtime.regexp.joni.Matcher;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
        import java.util.Collections;
import java.util.SortedMap;

/*
Составить цепочку слов
*/
public class Solution {
    public static boolean isForward = false;

    public static void main(String[] args) throws IOException {
//        //...C:\Users\roman.holodkov\Desktop\111.txt
//        StringBuilder inStringBuilder = new StringBuilder();
//        System.out.println(inStringBuilder);
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
//            String fileName = br.readLine();
////            String fileName = "C:/Users/roman.holodkov/Desktop/111.txt";
//            try (BufferedReader bis = new BufferedReader(
//                    new InputStreamReader(new FileInputStream(fileName), "cp1251"))) {
////                    new InputStreamReader(new FileInputStream(fileName)))) {
//                while (bis.ready()) {
//                    inStringBuilder.append(bis.readLine());
//                }
////                System.out.println(inStringBuilder);
//            }
//        }
//        String[] words = inStringBuilder.toString().split(" ");
//
//        StringBuilder result = getLine(words);
////        StringBuilder result = getLine();
//        System.out.println(result.toString());
//    }
//
//    public static StringBuilder getLine(String... words) {
//        String firstWord;
//        StringBuilder stringBuilder = new StringBuilder();
//        if (words == null || words.length == 0) {
//            return new StringBuilder();
//        }
//        ArrayList<String> arrayList = new ArrayList<>();
//        Collections.addAll(arrayList, words);
//        firstWord = arrayList.get(0);
//        arrayList.remove(0);
//        while (getSecondWord(firstWord, arrayList) != null) {
//            try {
//                for (int i = 0; i < arrayList.size(); i++) {
//
//                    if (isForward) {
//                        stringBuilder.insert(0, firstWord + " ");
//                    }
//                    if (!isForward) {
//                        stringBuilder.append(firstWord + " ");
//                    }
//                    firstWord = getSecondWord(firstWord, arrayList);
//                    arrayList.remove(firstWord);
//
//
//                }
//            } catch (Exception e) {
//            }
//        }
////        if (arrayList.size() != 0) {
////            if (getSecondWord(firstWord, arrayList) != null) {
////
////            }
////        }
//        if (isForward) {
//            stringBuilder.insert(0, firstWord);
//        }
//        if (!isForward) {
//            stringBuilder.append(firstWord + " ");
//        }
//        return stringBuilder.toString().endsWith(" ") ?
//                stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length()) : stringBuilder;
//
//    }
//
//
//    public static String getSecondWord(String firstWord, ArrayList<String> arrayList) {
//        String secondWord = new String();
//        for (int i = 0; i < arrayList.size(); i++) {
//            if (firstWord.endsWith(arrayList.get(i).substring(0, 1).toLowerCase())) {
//                return secondWord = arrayList.get(i);
//            }
//            if (firstWord.substring(0, 1).toLowerCase().equals((arrayList.get(i).substring(0, 1).toLowerCase()))) {
//                isForward = true;
//                return secondWord = arrayList.get(i);
//            }
//        }Sample Text
//        return null;
        String telNumber = "65464654";
        telNumber.matches("^\\+\\d{12}$");
    }
}
