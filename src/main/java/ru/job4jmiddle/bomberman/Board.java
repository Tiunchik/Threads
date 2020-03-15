/**
 * Package ru.job4jmiddle.bomberman for
 *
 * @author Maksim Tiunchik
 */
package ru.job4jmiddle.bomberman;

import net.jcip.annotations.ThreadSafe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class Board - game board
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 14.03.2020
 */
@ThreadSafe
public class Board {

    /**
     * game board locker
     */
    private final ReentrantLock[][] lockBoard;

    /**
     * size of game board
     */
    private final int size;

    /**
     * create game board
     *
     * @param size size of board side
     */
    Board(int size) {
        this.size = size;
        lockBoard = new ReentrantLock[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                lockBoard[x][y] = new ReentrantLock();
            }
        }
    }

    /**
     * move a thread with player from one cell to another
     *
     * @param source start cell
     * @param dist distination cell
     * @return true if thread moved successfull
     */
    public boolean move(Cell source, Cell dist) {
        if (lockBoard[dist.getX()][dist.getY()].tryLock()) {
            lockBoard[source.getX()][source.getY()].unlock();
            return true;
        }
        return false;
    }

    /**
     * get size of board
     *
     * @return size on board
     */
    public int size() {
        return size;
    }

    /**
     * check is there current thread blocked chosen cell
     *
     * @param x x coordinate of cell
     * @param y y coordinatw of cell
     * @return true if occupied, r false
     */
    public boolean check(int x, int y) {
        return lockBoard[x][y].isHeldByCurrentThread();
    }

    /**
     * add player to board
     *
     * @return true if player added or false
     */
    public boolean addToBoard() {
        int x = new Random().nextInt(size);
        int y = new Random().nextInt(size);
        return lockBoard[x][y].tryLock();
    }
}
