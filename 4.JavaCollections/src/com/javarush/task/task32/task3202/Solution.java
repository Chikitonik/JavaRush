package com.javarush.task.task32.task3202;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/* 
Читаем из потока
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("C:\\Users\\roman.holodkov\\Documents\\TEST\\111.txt"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
        if (is == null) return new StringWriter();
        StringWriter stringWriter = new StringWriter();
        byte[] bytes = new byte[1];
        while (is.available() > 0) {
            is.read(bytes);
            stringWriter.append((char)bytes[0]);
        }
        return stringWriter;
    }
}