/**
 * Package ru.jo4jmiddle.threads for
 *
 * @author Maksim Tiunchik
 */
package ru.job4jmiddle.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class ConcurrentOutput - class for first steps into multithreading.
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 08.03.2020
 */
public class ConcurrentOutput {

    /**
     * Main.
     *
     * @param args - args
     */
    public static void main(String[] args) {
        Thread another = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        Thread second = new Thread(() -> System.out.println(Thread.currentThread().getName()));
        second.start();
        another.start();
        System.out.println(Thread.currentThread().getName());
    }
}

