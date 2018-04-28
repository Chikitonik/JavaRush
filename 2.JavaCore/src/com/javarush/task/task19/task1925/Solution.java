package com.javarush.task.task19.task1925;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Длинные слова
 */
public class Solution {
    // есть косяк, если две строки в файле, то запятую не ставит, но в условии нет уточнения
    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader(args[0]));
//        BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
//
//        while (br.ready()) {
//            Pattern pattern = Pattern.compile("\\b[a-zA-Z0-9]{7,}\\b");
//            Matcher matcher = pattern.matcher(br.readLine());
//            while (matcher.find()) {
//                if (matcher.end() == matcher.regionEnd()) {
//                    bw.write(matcher.group(0));
//                } else {
//                    bw.write(matcher.group(0) + ",");
//                }
//            }
//        }
//        br.close();
//        bw.close();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(args[1]))){
                StringBuilder stringBuilder = new StringBuilder();
                Boolean isFirstStringBuilder = true;
            while (bufferedReader.ready()){
                String readData = bufferedReader.readLine();
                String[] words = readData.split(" ");
                for (int i = 0; i < words.length; i++) {
                    if (words[i].length() > 6) {
                        stringBuilder.append("," + words[i].trim());
                        if (isFirstStringBuilder) {
                            stringBuilder.delete(0,1);
                            isFirstStringBuilder = false;
                        }
                    }
                }
                bufferedWriter.write(stringBuilder.toString());
                stringBuilder.delete(0,stringBuilder.length());
            }
        }
    }
}
