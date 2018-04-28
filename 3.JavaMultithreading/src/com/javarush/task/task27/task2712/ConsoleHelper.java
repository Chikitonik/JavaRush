package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return bufferedReader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        String allDishes = Dish.allDishesToString();
        writeMessage(allDishes);
        writeMessage("Введите название блюда или введите exit для выхода");
        String inputStrung = "";
        Boolean isExit = false;
        List<Dish> dishList = new ArrayList<>();
        while (!isExit) {
            inputStrung = readString();
            if (allDishes.contains(inputStrung)) {
                dishList.add(Dish.valueOf(inputStrung));
            } else if (inputStrung.compareTo("exit")==0) {
                isExit = true;
            } else {
                writeMessage("Данного блюда нет в меню, введите название блюда");
            }
        }
        return dishList;
    }
}
