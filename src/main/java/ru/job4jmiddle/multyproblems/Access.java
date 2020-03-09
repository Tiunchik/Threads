/**
 * Package ru.job4jmiddle.access for
 *
 * @author Maksim Tiunchik
 */
package ru.job4jmiddle.multyproblems;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class Access - example of race condition in multytheading
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 09.03.2020
 */
public class Access {
    private static final Logger LOG = LogManager.getLogger(Access.class.getName());
    /**
     * static variable
     */
    private static int result = 0;

    /**
     * main, start 2 non-atomic threads that work with one
     *
     * @param args
     */
    public static void main(String[] args) {
        Runnable increment = () -> {
            int first = result++;
            int second = first * 2;
            if (result * 2 != second) {
                throw new IllegalArgumentException("multythreading problem");
            }
        };

        new Thread(increment).start();
        new Thread(increment).start();
        System.out.println(result);
    }

}
