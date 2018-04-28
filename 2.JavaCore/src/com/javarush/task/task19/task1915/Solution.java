package com.javarush.task.task19.task1915;

/* 
Дублируем текст
*/

import java.io.*;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream originalPrintStream = System.out;
        String fileName = "";
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
             FileOutputStream fileOutputStream = new FileOutputStream(fileName);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             PrintStream printStream = new PrintStream(byteArrayOutputStream)) {

            fileName = bufferedReader.readLine();
            System.setOut(printStream);
            testString.printSomething(); //записал в байтАррай
            fileOutputStream.write(byteArrayOutputStream.toByteArray()); //записал в файл
            System.setOut(originalPrintStream);
            System.out.println(byteArrayOutputStream.toString());



        } catch (Exception e){}

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}

