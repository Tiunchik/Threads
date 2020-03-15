/**
 * Package ru.job4jmiddle.bomberman for
 *
 * @author Maksim Tiunchik
 */
package ru.job4jmiddle.bomberman;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class Cell - data model that contains coordinates
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 14.03.2020
 */
public class Cell {

    /**
     * x coordinate in matrix
     */
    private int x = 0;
    /**
     * y coordinate in matrix
     */
    private int y = 0;

    /**
     * constructor, set basic coordinates
     *
     * @param x
     * @param y
     */
    Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * getter for x coordinate
     *
     * @return x coordinate for cell
     */
    public int getX() {
        return x;
    }

    /**
     * setter for x coordinate
     *
     * @param x set new parameter for x coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * getter for y coordinate
     *
     * @return y coordinate for cell
     */
    public int getY() {
        return y;
    }

    /**
     * setter for y coordinate
     *
     * @param y set new parameter for y coordinate
     */
    public void setY(int y) {
        this.y = y;
    }
}
