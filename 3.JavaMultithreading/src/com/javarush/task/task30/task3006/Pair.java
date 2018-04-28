package com.javarush.task.task30.task3006;

public class Pair {
    private int x;
    private int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("x=%d, y=%d", x, y);
    }

    public void swap() {
        /*например X = 4, в двоичной системе это 100
        * Y = 5, в двоичной системе это 101
        * ^ булевое исключающее или
        * принимает значение «истина» только если всего один из аргументов имеет значение «истина»*/
        y = y ^ x;// 101 ^ 100 = 001
        x = y ^ x;// 001 ^ 100 = 101
        y = y ^ x;// 001 ^ 101 = 100
    }
}
