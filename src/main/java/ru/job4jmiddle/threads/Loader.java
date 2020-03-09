/**
 * Package ru.jo4jmiddle.threads for
 *
 * @author Maksim Tiunchik
 */
package ru.job4jmiddle.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class Loader - class for work with Thread.sleep method
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 09.03.2020
 */
public class Loader {

    /**
     * main.
     *
     * @param args - args.
     */
    public static void main(String[] args) {
        Thread loader = new Thread(() -> {
            System.out.println("Start loading:");
            for (int persent = 0; persent <= 100; persent++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(String.format("\rloaded %5d persentages", persent));
            }
            System.out.print("\rLoading is finihsed");
        });
        loader.start();
    }


}
