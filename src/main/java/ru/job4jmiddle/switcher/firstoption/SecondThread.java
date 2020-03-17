/**
 * Package ru.job4jmiddle.switcher for
 *
 * @author Maksim Tiunchik
 */
package ru.job4jmiddle.switcher.firstoption;

/**
 * Class SecondThread - first version of CyclicBarrier.
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 16.03.2020
 */
public class SecondThread extends Thread {

    private final Object obj;

    private int adder;

    SecondThread(Object obj, int adder) {
        this.obj = obj;
        this.adder = adder;
    }

    @Override
    public void run() {
        try {
            for (int index = 0; index < 10; index++) {
                obj.addStr(adder);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
