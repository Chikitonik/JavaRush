package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Хуан Хуанович
 */
public class Solution {

    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader (new FileReader(args[0]));
        List<String> inputStrings = new ArrayList<>();
        while (br.ready()) {
            inputStrings.add(br.readLine());
        }
        br.close();
        for (String inputString : inputStrings) {
            String[] strings = inputString.split(" ");
            StringBuilder name = new StringBuilder();
            for (int i = 0; i < strings.length-3; i++) {
                name.append(strings[i] + " ");
            }
//            PEOPLE.add(new Person(inputString.replaceAll("[0-9]", "").trim(), gettingDate(inputString)));
            PEOPLE.add(new Person(name.toString().trim(), gettingDate(inputString)));
        }
        for (Person person:PEOPLE
             ) {
            System.out.println(person);
        }
    }
    public static Date gettingDate(String s) {
        s = s.replaceAll("[a-zA-Zа-яА-Я-]", "").trim();
        int spase1, spase2;
        spase1 = s.indexOf(" ");
        spase2 = s.lastIndexOf(" ");
        int day, month, year;
        day = Integer.parseInt(s.substring(0, spase1));
        month = Integer.parseInt(s.substring(spase1 + 1, spase2));
        year = Integer.parseInt(s.substring(spase2 + 1, s.length()));
        Date d = new Date(year-1900, month-1, day);
        return d;
    }
}
