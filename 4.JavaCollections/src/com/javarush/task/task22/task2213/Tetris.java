package com.javarush.task.task22.task2213;


import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class Tetris {
    private Field field;
    private Figure figure;
    public static Tetris game;

    public Field getField() {
        return field;
    }

    public Figure getFigure() {
        return figure;
    }

    public static void main(String[] args) {
        game = new Tetris();
        game.run();

    }


    public void run() {

    }

    public void step() {

    }
}
