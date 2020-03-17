/**
 * Package ru.job4jmiddle.switcher for
 *
 * @author Maksim Tiunchik
 */
package ru.job4jmiddle.switcher.firstoption;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Class FirstThread - first version of CyclicBarrier
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 16.03.2020
 */
public class FirstThread extends Thread {

    private final Object obj;

    private int adder;

    private Thread thread;

    private CyclicBarrier barrier;

    FirstThread(Object obj, int adder, SecondThread thread) {
        this.obj = obj;
        this.adder = adder;
        this.thread = thread;
        this.barrier = new CyclicBarrier(1, this.thread);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                for (int index = 0; index < 10; index++) {
                    obj.addStr(adder);
                    Thread.sleep(100);
                }
                barrier.await();
            }
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();

        }
    }

}
