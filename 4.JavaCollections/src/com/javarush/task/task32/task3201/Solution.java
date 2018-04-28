package com.javarush.task.task32.task3201;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) {
        String fileName = args[0];
        long number = Long.parseLong(args[1]);
        String text = args[2];
        long textLenght = text.length();

            try (RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw")) {
                Long fileLength = randomAccessFile.length();
                if (fileLength < (number + textLenght)) number = fileLength;
                randomAccessFile.seek(number);
                randomAccessFile.write(text.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
}
