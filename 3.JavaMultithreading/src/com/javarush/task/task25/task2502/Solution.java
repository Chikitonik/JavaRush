package com.javarush.task.task25.task2502;

import java.util.*;

/* 
Машину на СТО не повезем!
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() {
            wheels = new ArrayList<>();
            String [] wheel = loadWheelNamesFromDB();
            ArrayList<Wheel> wheels2 = new ArrayList<>();
            wheels2.forEach(wheel2 -> wheels.add(wheel2));

            for (int i = 0; i < wheel.length; i++) {
                String  wheel1 = wheel[i];
                if (!wheels.contains(wheel1)&&!wheels2.contains(wheel1)) {
                    wheels.add(Wheel.valueOf(wheel1));
                } else {
                    throw new IllegalArgumentException();
                }
            }
            if (wheels.size()!=4) {
                throw new IllegalArgumentException();
            }
            //init wheels here
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) {
        Car car = new Car();
        System.out.println(Arrays.toString(car.wheels.toArray()));
    }
}
