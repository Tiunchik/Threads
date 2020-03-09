/**
 * Package ru.jo4jmiddle.threads for
 *
 * @author Maksim Tiunchik
 */
package ru.jo4jmiddle.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class ThreadState - work with three type of hread status  - new, runnable, terminated.
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 09.03.2020
 */
public class ThreadState {

    /**
     * main - work with three type of hread status  - new, runnable, terminated.
     *
     * @param args
     */
    public static void main(String[] args) {
        Thread first = new Thread(() -> {
        });
        first.setName("First thread");
        Thread second = new Thread(() -> {
        });
        second.setName("Second thread");
        System.out.println(first.getName() + " is " + first.getState());
        System.out.println(second.getName() + " is " + second.getState());
        first.start();
        second.start();
        while ((first.getState() == Thread.State.RUNNABLE) || (second.getState() == Thread.State.RUNNABLE)) {
            System.out.println(String.format("\n|%10s is %s10| |%10s is %s10|\n"
                    , first.getName(), first.getState(), second.getName(), second.getState()));
        }
        System.out.println("Execution of threads is finished, current status is given below:\n");
        System.out.println(String.format("|%10s is %s10| |%10s is %s10|"
                , first.getName(), first.getState(), second.getName(), second.getState()));
    }
}