package ru.job4jmiddle.concurrent;

import org.junit.Test;

import static org.junit.Assert.*;

public class BaseStorageTest {

    private class BaseThread extends Thread {

        Base base;

        BaseThread(Base base) {
            this.base = base;
        }

        @Override
        public void run() {
            base.setName("change");
        }
    }

    /**
     * try to cause Optimistic exeption
     *
     * @throws InterruptedException
     */
    @Test
    public void whenWeTryToChangeOneObjectSimultaneouslyThenWeCanHaveMistake() throws InterruptedException {
        Base t1 = new Base(35);
        BaseThread thread00 = new BaseThread(t1);
        BaseThread thread01 = new BaseThread(t1);
        t1.setName("change");
        thread00.start();
        thread01.start();
        thread00.join();
        thread01.join();

        int expected = 4;

        assertEquals(expected, t1.getVersion());
    }

    private class BaseAddThread extends Thread {

        private int start;

        private BaseStorage base;

        public BaseAddThread(BaseStorage base, int start) {
            this.base = base;
            this.start = start;
        }

        @Override
        public void run() {
            for (int index = 0; index < 10; index++) {
                base.add(new Base(start + index));
            }
        }
    }

    @Test
    public void whenWeAddManyBaseInOneStorageThneDontHaveMistakes() throws InterruptedException {
        BaseStorage base = new BaseStorage();
        BaseAddThread thread01 = new BaseAddThread(base, 10);
        BaseAddThread thread02 = new BaseAddThread(base, 30);
        thread01.start();
        thread02.start();
        thread01.join();
        thread02.join();

        int expected = 20;

        assertEquals(expected, base.size());
    }

}