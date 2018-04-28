package com.javarush.task.task22.task2212;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Проверка номера телефона
Критерии валидности:
1) если номер начинается с '+', то он содержит 12 цифр
^\+[0-9]{12}
2) если номер начинается с цифры или открывающей скобки, то он содержит 10 цифр
^[\(\d](?:.*?\d){10}
3) может содержать 0-2 знаков '-', которые не могут идти подряд
(?:.*?-){0,2}
4) может содержать 1 пару скобок '(' и ')' , причем если она есть, то она расположена левее знаков '-'
[^-]*\(.*?\)
5) скобки внутри содержат четко 3 цифры
\(\d{3}\)
6) номер не содержит букв
[^a-zA-Zа-яА-Я]
7) номер заканчивается на цифру
\d$

+380501234567 - true
+38(050)1234567 - true
+38050123-45-67 - true
050123-4567 - true
+38)050(1234567 - false
+38(050)1-23-45-6-7 - false
050ххх4567 - false
050123456 - false
(0)501234567 - false
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        Pattern pattern1 = Pattern.compile("^\\+[0-9]{12}");
        Matcher matcher1 = pattern1.matcher(telNumber);
        Pattern pattern2 = Pattern.compile("^[(](\\d){10}$|^(\\d){10}$");
        Matcher matcher2 = pattern2.matcher(telNumber);
        Pattern pattern3 = Pattern.compile("(?:.*?-){0,2}\\d+");
        Matcher matcher3 = pattern3.matcher(telNumber);
        Pattern pattern4 = Pattern.compile("[^-]*\\(.*?\\)");
        Matcher matcher4 = pattern4.matcher(telNumber);
        Pattern pattern7 = Pattern.compile(".+\\(\\d{3}\\).+");
        Matcher matcher7 = pattern7.matcher(telNumber);
        Pattern pattern5 = Pattern.compile("[^a-zA-Zа-яА-Я]+");
        Matcher matcher5 = pattern5.matcher(telNumber);
        Pattern pattern6 = Pattern.compile("\\d$");
        Matcher matcher6 = pattern6.matcher(telNumber);
        return matcher1.matches()||matcher2.matches()||matcher3.matches()
                ||matcher4.matches()||matcher7.matches()&& matcher5.matches()&& matcher6.matches();
    }

    public static void main(String[] args) throws IOException {
        System.out.println("+380501234567 - true " + checkTelNumber("+380501234567"));
        System.out.println("+38(050)1234567 - true " + checkTelNumber("+38(050)1234567"));
        System.out.println("+38050123-45-67 - true " + checkTelNumber("+38050123-45-67"));
        System.out.println("050123-4567 - true " + checkTelNumber("050123-4567"));
        System.out.println("+38)050(1234567 - false " + checkTelNumber("+38)050(1234567"));
        System.out.println("+38(050)1-23-45-6-7 - false " + checkTelNumber("+38(050)1-23-45-6-7"));
        System.out.println("050ххх4567 - false " + checkTelNumber("050ххх4567"));
        System.out.println("050123456 - false " + checkTelNumber("050123456"));
        System.out.println("(0)501234567 - false " + checkTelNumber("(0)501234567"));

}
}
