package com.javarush.task.task35.task3513;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

/*будет следить за нажатием клавиш во время игры*/
public class Controller extends KeyAdapter {
    private Model model;
    private View view;
    private static final int WINNING_TILE = 2048;

    public Tile[][] getGameTiles() {
        return model.getGameTiles();
    }

    public View getView() {
        return view;
    }

    public int getScore() {
        return model.score;
    }

    public Controller(Model model) {
        this.model = model;
        this.view = new View(this);
    }

    public void resetGame() {
        model.score = 0;
        view.isGameLost = false;
        view.isGameWon = false;
        model.resetGameTiles();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == VK_ESCAPE) {
            resetGame();
        }
        if (!model.canMove()) {
            view.isGameLost = true;
        }
        if (view.isGameLost == false & view.isGameWon == false) {
            switch (e.getKeyCode()) {
                case VK_LEFT: {
                    model.left();
                    break;
                }
                case VK_RIGHT: {
                    model.right();
                    break;
                }
                case VK_UP: {
                    model.up();
                    break;
                }
                case VK_DOWN: {
                    model.down();
                    break;
                }
            }
        }
        if (model.maxTile == WINNING_TILE) {
            view.isGameWon = true;
        }
        if (e.getKeyCode() == VK_Z) {
            model.rollback();
        }
        if (e.getKeyCode() == VK_R) {
            model.randomMove();
        }
        if (e.getKeyCode() == VK_A) {
            model.autoMove();
        }
        view.repaint();
    }
}
