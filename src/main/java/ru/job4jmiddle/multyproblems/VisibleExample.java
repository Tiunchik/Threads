/**
 * Package ru.job4jmiddle.multyproblems for
 *
 * @author Maksim Tiunchik
 */
package ru.job4jmiddle.multyproblems;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class VisibleExample - example of visible problems in multithreading.
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 10.03.2020
 */
public class VisibleExample extends Thread {
    private static final Logger LOG = LogManager.getLogger(VisibleExample.class.getName());
    /**
     * run the run circle
     */
    boolean circle = true;

    /**
     * run method, make infinity increment of int variable.
     */
    @Override
    public void run() {
        int x = 0;
        while (circle) {
            x++;
        }
        System.out.println("Stop running: " + x);
    }

    /**
     * main.
     *
     * @param args - none.
     * @throws InterruptedException .
     */
    public static void main(String[] args) throws InterruptedException {
        VisibleExample t = new VisibleExample();
        t.start();
        Thread.sleep(500);
        t.circle = false;
        System.out.println("Circle is false");
    }
}
