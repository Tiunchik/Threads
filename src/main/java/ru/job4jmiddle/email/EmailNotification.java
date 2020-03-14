/**
 * Package ru.job4jmiddle.email for
 *
 * @author Maksim Tiunchik
 */
package ru.job4jmiddle.email;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class EmailNotification -
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 14.03.2020
 */
public abstract class EmailNotification {

    private ExecutorService pool =
            Executors
                    .newFixedThreadPool(Runtime.getRuntime().availableProcessors() / 2);

    /**
     * Create template for sending email
     *
     * @param user
     */
    public void emailTo(User user) {
        String subject = String.format("Notification %s to email %s.", user.getUsername(), user.getEmail());
        String body = String.format("Add a new event to %s", user.getUsername());
        pool.submit(() -> {
            send(subject, body, user.getEmail());
        });
    }

    public void send(String suject, String body, String email) {
    }

    /**
     * shotdown executor servise pool
     */
    public void close() {
        try {
            pool.shutdown();
        } catch (Exception e) {
            pool.shutdownNow();
        }
    }

}
