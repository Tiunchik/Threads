/**
 * Package ru.job4jmiddle.concurrent for
 *
 * @author Maksim Tiunchik
 */
package ru.job4jmiddle.concurrent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class OptimisticException - 
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 14.03.2020
 */
public class OptimisticException extends RuntimeException {

    /**
     * constructor for optimistic exception
     *
     * @param message message of exception
     */
    public OptimisticException(String message) {
        super(message);
    }
}
