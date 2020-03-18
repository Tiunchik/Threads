/**
 * Package ru.job4jmiddle.bomberman for
 *
 * @author Maksim Tiunchik
 */
package ru.job4jmiddle.bomberman;

import java.util.Random;

/**
 * Class Monster -
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 14.03.2020
 */
public class Monster implements Runnable {

    /**
     * game board for game
     */
    private final Board board;

    /**
     * is there Monster added to game board
     */
    private boolean added = false;

    /**
     * constructor, set board for game
     *
     * @param board
     */
    Monster(Board board) {
        this.board = board;
    }

    /**
     * Monster actions in separated thread
     */
    @Override
    public void run() {
        if (!added) {
            var e = true;
            while (e) {
                e = !board.addToBoard();
            }
        }
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Cell source = findPos();
                Cell dist = step(source);
                if (board.move(source, dist)) {
                    Thread.sleep(1000);
                } else {
                    Thread.sleep(500);
                }
                System.out.println(Thread
                        .currentThread()
                        .getName() + " is on position "
                        + source.getX() + " " + source.getY());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * automatically choose next cell for step in
     *
     * @param place current position of player
     * @return cell for step
     */
    private Cell step(Cell place) {
        int side = board.size() - 1;
        int choice = new Random().nextInt(4);
        int xMove = place.getX();
        int yMove = place.getY();
        if (choice == 0) {
            if (xMove != 0) {
                xMove -= 1;
            } else {
                xMove += 1;
            }
        }
        if (choice == 2) {
            if (xMove != side) {
                xMove += 1;
            } else {
                xMove -= 1;
            }
        }
        if (choice == 1) {
            if (yMove != 0) {
                yMove -= 1;
            } else {
                yMove += 1;
            }
        }
        if (choice == 3) {
            if (yMove != side) {
                yMove += 1;
            } else {
                yMove -= 1;
            }
        }
        return new Cell(xMove, yMove);
    }

    /**
     * find current position of player on board
     *
     * @return cell with coordinates of player
     */
    private Cell findPos() {
        int size = board.size();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (board.check(x, y)) {
                    return new Cell(x, y);
                }
            }
        }
        return null;
    }
}
