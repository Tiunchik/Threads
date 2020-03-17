/**
 * Package ru.job4jmiddle.deadlock for
 *
 * @author Maksim Tiunchik
 */
package ru.job4jmiddle.deadlock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CountDownLatch;

/**
 * Class GreetThread -
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 17.03.2020
 */
public class GreetThread extends Thread {

    private final User user1;

    private final User user2;

    private final CountDownLatch start;

    GreetThread(User user1, User user2, CountDownLatch start) {
        this.user1 = user1;
        this.user2 = user2;
        this.start = start;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                start.await();
                user1.greetings(user2);
            }
        } catch (InterruptedException e) {
            interrupt();
        }
    }
}
