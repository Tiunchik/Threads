/**
 * Package ru.job4jmiddle.access for
 *
 * @author Maksim Tiunchik
 */
package ru.job4jmiddle.multyproblems;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class RaceExample - example of race condition in multithreading
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.2
 * @since 10.03.2020
 */
public class RaceExample {
    private static final Logger LOG = LogManager.getLogger(RaceExample.class.getName());
    /**
     * static variable
     */
    private static int result = 0;

    /**
     * main, start 2 threads that work with one int variable.
     * One thread capure variable and don't give it to the other.
     *
     * @param args
     */
    public static void main(String[] args) {
        Runnable increment = () -> {
            while (result < 10) {
                int first = ++result;
                if (first == result) {
                    System.out.println(first);
                }
            }
        };

        new Thread(increment).start();
        new Thread(increment).start();
        System.out.println(result);
    }

}
