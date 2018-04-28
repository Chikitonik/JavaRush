package com.javarush.task.task35.task3513;

import java.util.*;

/*будет содержать игровую логику и хранить игровое поле*/
public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    protected int score;
    protected int maxTile;
    private Stack<Tile[][]> previousStates = new Stack<>();
    private Stack<Integer> previousScores = new Stack<>();
    private boolean isSaveNeeded = true;

    private void saveState(Tile[][] tiles) {
        Tile[][] tempTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                tempTiles[i][j] = new Tile(gameTiles[i][j].getValue());
            }
        }
        previousStates.push(tempTiles);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    public void rollback() {
        if (!previousStates.empty() & !previousScores.empty()) {
            gameTiles = previousStates.pop();
            score = previousScores.pop();
        }
    }

    public Model() {
        resetGameTiles();
        this.score = 0;
        this.maxTile = 2;
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public boolean canMove() {
        if (!getEmptyTiles().isEmpty()) {
            return true;
        }
        for (int i = 0; i < FIELD_WIDTH - 1; i++) {
            for (int j = 0; j < FIELD_WIDTH - 1; j++) {
                if (gameTiles[i][j].value == gameTiles[i][j + 1].value ||
                        gameTiles[i][j].value == gameTiles[i + 1][j].value) {
                    return true;
                }
            }

        }
        return false;
    }

    public void resetGameTiles() {
        this.gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();

    }

    private void addTile() {
        List<Tile> emptyTiles = getEmptyTiles();
        if (emptyTiles != null && !emptyTiles.isEmpty()) {
            int random = (int) (Math.random() * emptyTiles.size());
            int tileValue = Math.random() < 0.9 ? 2 : 4;
            emptyTiles.get(random).setValue(tileValue);
        }
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> emptyTiles = new ArrayList<>();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].isEmpty()) {
                    emptyTiles.add(gameTiles[i][j]);
                }
            }
        }
        return emptyTiles;
    }

    /*Сжатие плиток, таким образом, чтобы все пустые плитки были
    справа, т.е. ряд {4, 2, 0, 4} становится рядом {4, 2, 4, 0}*/
    private boolean compressTiles(Tile[] tiles) {
/*        Tile[] oldTiles = tiles;
        Arrays.sort(tiles, new Comparator<Tile>() {
            @Override
            public int compare(Tile o1, Tile o2) {
                if (o1.getValue() != 0 && o2.getValue() == 0) {
                    return -1;
                } else return 0;
            }
        });
        boolean isChanged = !oldTiles.equals(tiles);
        return isChanged;*/
        boolean changes = false;
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].isEmpty()) {
                int j = i;
                while (tiles[j].isEmpty() && j < tiles.length - 1) {
                    j++;
                }
                if (!tiles[j].isEmpty()) changes = true;
                tiles[i] = tiles[j];
                tiles[j] = new Tile();
            }
        }
        return changes;
    }

    private boolean mergeTiles(Tile[] tiles) {
/*Слияние плиток одного номинала, т.е. ряд {4, 4, 2, 0} становится рядом {8, 2, 0, 0}.
Обрати внимание, что ряд {4, 4, 4, 4} превратится в {8, 8, 0, 0}, а {4, 4, 4, 0} в {8, 4, 0, 0}*/
        boolean isChanged = false;
        for (int i = 0; i < FIELD_WIDTH - 1; i++) {
            if (tiles[i].getValue() == tiles[i + 1].getValue() && tiles[i].getValue() != 0) {
                tiles[i].setValue(tiles[i].getValue() * 2);
                score += tiles[i].getValue();
                if (tiles[i].getValue() > maxTile) {
                    maxTile = tiles[i].getValue();
                }
                tiles[i + 1].setValue(0);
                isChanged = true;
            }
        }
        compressTiles(tiles);
        return isChanged;
    }

    public void left() {
        if (isSaveNeeded) {
            saveState(gameTiles);
        }
        boolean isChange = false;
        for (int i = 0; i < gameTiles.length; i++) {
            if (compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i])) {
                isChange = true;
            }
        }
        if (isChange && getEmptyTiles().size() != 0) addTile();
        isSaveNeeded = true;
    }

    public void right() {
        saveState(gameTiles);
        rotate90();
        rotate90();
        left();
        rotate90();
        rotate90();
    }

    public void up() {
        saveState(gameTiles);
        rotate90();
        rotate90();
        rotate90();
        left();
        rotate90();
    }

    public void down() {
        saveState(gameTiles);
        rotate90();
        left();
        rotate90();
        rotate90();
        rotate90();
    }

    public void rotate90() {
        /*создается массив значений плиток*/
        int[][] copyGameTilesValues = new int[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                copyGameTilesValues[i][j] = gameTiles[i][j].getValue();
            }
        }
        /*массив переворачивается на 90 градусов используя вспомогательный массив*/
        int[][] tempCopyGameTilesValues = new int[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                tempCopyGameTilesValues[j][FIELD_WIDTH - 1 - i] = copyGameTilesValues[i][j];
            }
        }
        /*в массиве плиток значения придаются из временного массива значений*/
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j].setValue(tempCopyGameTilesValues[i][j]);
            }
        }
    }

    public void randomMove() {
        int n = ((int) (Math.random() * 100)) % 4;
        switch (n) {
            case 0: {
                left();
                break;
            }
            case 1: {
                right();
                break;
            }
            case 2: {
                up();
                break;
            }
            case 3: {
                down();
                break;
            }
        }
    }

    public boolean hasBoardChanged() {
        int gameTilesValues = 0, previousStatesValues = 0;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTilesValues += gameTiles[i][j].value;
                previousStatesValues += previousStates.peek()[i][j].value;
            }
        }
        return gameTilesValues != previousStatesValues ? true : false;
    }

    public MoveEfficiency getMoveEfficiency(Move move) {
        move.move();
        MoveEfficiency moveEfficiency = new MoveEfficiency(getEmptyTiles().size(), score, move);
        if (!hasBoardChanged()) {
           return new MoveEfficiency(-1,0,move);
        }
        rollback();
        return moveEfficiency;
    }

    public void autoMove () {
        /*Создадим локальную PriorityQueue с параметром Collections.reverseOrder() (для того, чтобы
        вверху очереди всегда был максимальный элемент) и размером равным четырем.*/
        PriorityQueue priorityQueue = new PriorityQueue(4, Collections.reverseOrder());
        priorityQueue.add(getMoveEfficiency(this::left));
        priorityQueue.add(getMoveEfficiency(this::right));
        priorityQueue.add(getMoveEfficiency(this::up));
        priorityQueue.add(getMoveEfficiency(this::down));
        MoveEfficiency moveEfficiency = (MoveEfficiency)priorityQueue.poll();
        moveEfficiency.getMove().move();
    }
}
