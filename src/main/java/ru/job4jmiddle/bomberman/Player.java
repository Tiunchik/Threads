/**
 * Package ru.job4jmiddle.bomberman for
 *
 * @author Maksim Tiunchik
 */
package ru.job4jmiddle.bomberman;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4jmiddle.pool.ThreadsPool;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class Player -
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 14.03.2020
 */
public class Player implements Runnable {

    /**
     * game board for game
     */
    private final Board board;

    /**
     * is there player added to game board
     */
    private boolean added = false;

    /**
     *
     */
    private volatile char to = '0';

    /**
     * constructor, set board for game
     *
     * @param board
     */
    Player(Board board) {
        this.board = board;
    }

    /**
     * player actions in separated thread
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
                System.out.println("Player is on position " + source.getX() + " " + source.getY());
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
        int choice = to;
        int side = board.size() - 1;
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

    private class ReadKeyBoard extends Thread {


        @Override
        public void run() {
            //TODO actions with "to" to provide information of player actions
        }
    }

}
