package com.javarush.task.task19.task1904;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\roman.holodkov\\Documents\\TEST\\test.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
            PersonScannerAdapter personScannerAdapter = new PersonScannerAdapter(scanner);
            try {
                Person person = personScannerAdapter.read();
                System.out.println(person.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public static class PersonScannerAdapter implements PersonScanner {
        private final Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException {
            String readLine = fileScanner.nextLine();
            String[] personData = readLine.split(" ");
            String firstName = personData[1];
            String middleName = personData[2];
            String lastName = personData[0];
            int year = Integer.parseInt(personData[5]);
            int month = Integer.parseInt(personData[4]) - 1;
            int day = Integer.parseInt(personData[3]);
            GregorianCalendar calendar = new GregorianCalendar(year, month, day);
            Date birthDate = calendar.getTime();
            Person person = new Person(firstName, middleName, lastName, birthDate);
            return person;
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();
        }
    }
}
