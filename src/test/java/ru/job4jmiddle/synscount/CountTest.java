package ru.job4jmiddle.synscount;

import org.junit.Test;

import static org.junit.Assert.*;

public class CountTest {


    /**
     * Class that is describing thread with counter
     */
    private class ThreadCount extends Thread {
        private final Count count;

        private ThreadCount(final Count count) {
            this.count = count;
        }

        @Override
        public void run() {
            this.count.increment();
        }
    }

    @Test
    public void increment() throws InterruptedException {
        Count c1 = new Count();
        Thread t1 = new ThreadCount(c1);
        Thread t2 = new ThreadCount(c1);

        t1.start();
        t2.start();

        int expected = 2;

        t1.join();
        t2.join();

        assertEquals(expected, c1.getCount());
    }
}