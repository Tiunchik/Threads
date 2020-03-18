/**
 * Package ru.job4jmiddle.switcher.secondoption for
 *
 * @author Maksim Tiunchik
 */
package ru.job4jmiddle.switcher.secondoption;

import ru.job4jmiddle.switcher.secondoption.Object;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Class Second - second version of CyclicBarrier
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 17.03.2020
 */
public class Second extends Thread {

    private final Object obj;

    private int adder;

    private CyclicBarrier firstbarrier;

    private CyclicBarrier secondbarrier;

    Second(Object obj, int adder, CyclicBarrier firstbarrier, CyclicBarrier secondbarrier) {
        this.obj = obj;
        this.adder = adder;
        this.firstbarrier = firstbarrier;
        this.secondbarrier = secondbarrier;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                firstbarrier.await();
                for (int index = 0; index < 10; index++) {
                    obj.addStr(adder);
                    Thread.sleep(100);
                }
                secondbarrier.await();
            }
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();

        }
    }
}
