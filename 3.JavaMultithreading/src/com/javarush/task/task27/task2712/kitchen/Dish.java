package com.javarush.task.task27.task2712.kitchen;

import java.util.Arrays;

public enum Dish {

    Fish(25),
    Steak(30),
    Soup(15),
    Juice(5),
    Water(3);

    private int duration;

    public int getDuration() {
        return duration;
    }

    Dish(int duration) {
        this.duration = duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public static String allDishesToString() {
        Boolean isFirstDish = true;
        StringBuilder allDishes = new StringBuilder();
        Dish[] allDishesArray = Dish.values();
        for (int i = 0; i < allDishesArray.length; i++) {
            if (isFirstDish) {
                allDishes.append(allDishesArray[i]);
                isFirstDish = false;
            } else {
                allDishes.append(", " + allDishesArray[i]);
            }
        }
        return allDishes.toString();
//        return Arrays.toString(Dish.values()); //выведет с {}
    }
}
