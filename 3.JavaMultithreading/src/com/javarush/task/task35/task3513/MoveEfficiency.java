package com.javarush.task.task35.task3513;

public class MoveEfficiency implements Comparable <MoveEfficiency> {
    private int numberOfEmptyTiles, score;
    private Move move;

    public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.score = score;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }
/*В методе compareTo первым делом сравни количество пустых плиток (numberOfEmptyTiles), потом счет
(score), если количество пустых плиток равное. Если и счет окажется равным, будем считать эффективность
ходов равной и вернем ноль.*/

    @Override
    public int compareTo(MoveEfficiency o) {
        return this.numberOfEmptyTiles == o.numberOfEmptyTiles ?
                this.score ==  o.score ?
                        0 : Integer.compare(this.score,o.score) :
                Integer.compare(this.numberOfEmptyTiles,o.numberOfEmptyTiles);
    }
}
