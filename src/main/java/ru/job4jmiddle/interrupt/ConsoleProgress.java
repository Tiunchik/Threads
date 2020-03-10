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
 * @version 0.2
 * @since 10.03.2020
 */
public class ConsoleProgress implements Runnable {

    private static final String CHECK = "- / | \\ -";

    /**
     * overriding iheritable method fropm runnabale interface. Pring spinning circle on console screen
     */
    @Override
    public void run() {
        String circle = "- \\ | / -";

        while (!Thread.currentThread().isInterrupted()) {
            circle = circle.equals(CHECK) ? "- \\ | / -" : CHECK;
            System.out.print("\r load: " + circle);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("\n" + Thread.currentThread().getName() + "'s execution is stopped");
            }
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
