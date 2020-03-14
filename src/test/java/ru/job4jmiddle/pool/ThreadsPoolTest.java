package ru.job4jmiddle.pool;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public class ThreadsPoolTest {

    @Test
    public void whenWeTryyoUseOurInsaneCodeAndThneItWorks() throws InterruptedException {
        ThreadsPool pool = new ThreadsPool(4);
        AtomicInteger test = new AtomicInteger(0);
        Runnable task = () -> {
            for (int index = 0; index < 350; index++) {
                test.incrementAndGet();
            }
        };
        for (int index = 0; index < 4; index++) {
            pool.work(task);
        }
        Thread.sleep(1500);

        pool.shutdown();

        assertEquals(4 * 350, test.get());


    }
}