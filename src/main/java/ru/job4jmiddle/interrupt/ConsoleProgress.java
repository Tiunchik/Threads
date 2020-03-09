/**
 * Package ru.job4j.interrupt for
 *
 * @author Maksim Tiunchik
 */
package ru.job4jmiddle.interrupt;

/**
 * Class ConsoleProgress - work with interruption
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 09.03.2020
 */
public class ConsoleProgress implements Runnable {

    private static final String CHECK = "- / | \\ -";

    /**
     * overriding iheritable method fropm runnabale interface. Pring spinning circle on console screen
     */
    @Override
    public void run() {
        String circle = "- \\ | / -";
        try {
        while (!Thread.currentThread().isInterrupted()) {
            circle = circle.equals(CHECK) ? "- \\ | / -" : CHECK;
            System.out.print("\r load: " + circle);
            Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("\n" + Thread.currentThread().getName() + "'s execution is stopped");
        }
    }

    /**
     * main.
     *
     * @param args - args
     * @throws InterruptedException - method sleep cam throw execption
     */
    public static void main(String[] args) throws InterruptedException {
        Thread first = new Thread(new ConsoleProgress());
        first.start();
        Thread.sleep(3000);
        first.interrupt();
    }
}
