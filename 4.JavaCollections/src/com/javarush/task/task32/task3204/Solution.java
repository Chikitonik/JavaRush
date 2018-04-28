package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        /*таблица ASCII
         * 48-57 цифры
         * 65-90 БУКВЫ англ
         * 97-122 буквы англ*/
        SecureRandom secureRandom = new SecureRandom();
        boolean hasNum = false;
        boolean hasUpChar = false;
        boolean hasLowChar = false;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        while (true) {
            /*создается случайный массив int и проверяется на соответствие условию*/
            IntStream intStream = secureRandom.ints(8, 48, 122);
            int[] ints = intStream.toArray();
            for (int i = 0; i < ints.length; i++) {
                if (ints[i] >= 48 & ints[i] <= 57) hasNum = true;
                if (ints[i] >= 65 & ints[i] <= 90) hasUpChar = true;
                if (ints[i] >= 97 & ints[i] <= 122) hasLowChar = true;
            }
/*пробую со стримом*/
            ArrayList<Integer> integers = new ArrayList<>();
            Arrays.stream(ints).filter(value -> value >= 58)
                    .filter(value -> value <= 64)
                    .forEach(integers::add);
            Arrays.stream(ints).filter(value -> value >= 91)
                    .filter(value -> value <= 96)
                    .forEach(integers::add);

/*            for (int i = 0; i < ints.length; i++) {
                if (ints[i] >= 58 & ints[i] <= 64) hasNum = false;
                if (ints[i] >= 91 & ints[i] <= 96) hasNum = false;
            }*/

            if (integers.isEmpty() && hasLowChar && hasNum && hasUpChar) {
                byte[] bytes = new byte[ints.length];
                for (int i = 0; i < ints.length; i++) {
                    bytes[i] = (byte) ints[i];
                }
                try {
                    byteArrayOutputStream.write(bytes);
                    return byteArrayOutputStream;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            hasLowChar = false;
            hasNum = false;
            hasUpChar = false;
        }

    }
}