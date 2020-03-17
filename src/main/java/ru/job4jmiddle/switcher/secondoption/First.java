/**
 * Package ru.job4jmiddle.switcher.secondoption for
 *
 * @author Maksim Tiunchik
 */
package ru.job4jmiddle.switcher.secondoption;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


/**
 * Class First - second version of CyclicBarrier
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 17.03.2020
 */
public class First extends Thread {

    private final Object obj;

    private int adder;

    private CyclicBarrier firstbarrier;

    private CyclicBarrier secondbarrier;

    First(Object obj, int adder, CyclicBarrier firstbarrier, CyclicBarrier secondbarrier) {
        this.obj = obj;
        this.adder = adder;
        this.firstbarrier = firstbarrier;
        this.secondbarrier = secondbarrier;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                for (int index = 0; index < 10; index++) {
                    obj.addStr(adder);
                    Thread.sleep(100);
                }
                firstbarrier.await();
                secondbarrier.await();
            }
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();

        }
    }
}
