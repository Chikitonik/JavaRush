package com.javarush.task.task20.task2014;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
*/
public class Solution implements Serializable{
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println(new Solution(4));

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("C:/Users/roman.holodkov/Desktop/111.txt"));
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("C:/Users/roman.holodkov/Desktop/111.txt"));
        Solution savedObject = new Solution(10);
        objectOutputStream.writeObject(savedObject);
//        System.out.println(savedObject);
        objectOutputStream.close();
        Solution loadedObject = new Solution(8);
        loadedObject = (Solution) objectInputStream.readObject();
        objectInputStream.close();
//        System.out.println(loadedObject);
        System.out.println(savedObject.string.equals(loadedObject.string));
    }

    transient private final String pattern = "dd MMMM yyyy, EEEE";
    transient private Date currentDate;
    transient private int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}
