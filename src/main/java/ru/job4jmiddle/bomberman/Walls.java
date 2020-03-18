package ru.job4jmiddle.bomberman;

public class Walls implements Runnable {

    /**
     * game board for game
     */
    private final Board board;

    /**
     * number of blocked positions
     */
    private final int count;

    /**
     * constructor, set board for game
     *
     * @param board
     */
    Walls(Board board, int count) {
        this.board = board;
        this.count = count > 0 ? count : 10;
    }


    /**
     * make obstacles on deck
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            for (int index = 0; index < count; index++) {
                boolean cicle = true;
                while (cicle) {
                    cicle = !board.addToBoard();
                }
            }
        }
    }
}
