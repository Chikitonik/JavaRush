package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;

public class TestOrder extends Order {
    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    protected void initDishes() {
        Dish[] dishesAll = Dish.values();
        int randomCountDishes = (int) Math.floor(Math.random()*dishesAll.length);
        for (int i = 0; i < randomCountDishes ; i++) {
            dishes.add(dishesAll[i]);
        }
    }
}
