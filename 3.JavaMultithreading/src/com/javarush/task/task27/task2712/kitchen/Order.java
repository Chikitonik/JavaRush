package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        initDishes();
        this.dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    @Override
    public String toString() {
        if (dishes.isEmpty()) {
            return "";
        }
        return String.format("Your order: %s of %s", dishes.toString(), tablet.toString());
    }

    public int getTotalCookingTime() {
        int totalCookingTime = 0;
        for (Dish dish : dishes) {
            totalCookingTime += dish.getDuration();
        }
        return totalCookingTime;
    }

    public boolean isEmpty(){

        return dishes.isEmpty() ? true : false;
    }
    protected void initDishes(){
        dishes = new ArrayList<>();
    }
}
