package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) {
        String fileName = args[0];
        int number = Integer.parseInt(args[1]);
        String text = args[2];

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw")) {
            byte[] readBytes = new byte[text.length()];
            randomAccessFile.seek(number);
            randomAccessFile.read(readBytes,0,text.length());
            randomAccessFile.seek(randomAccessFile.length());
            if (text.equals(new String(readBytes))) {
                randomAccessFile.write("true".getBytes());
            } else {
                randomAccessFile.write("false".getBytes());
            }
        } catch (IOException e) {

        }
    }
}
