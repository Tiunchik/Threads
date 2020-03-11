package ru.job4jmiddle.userstorage;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserStorageTest {

    private class ThreadTest extends Thread {

        private UserStorage store;

        public ThreadTest(UserStorage store) {
            this.store = store;
        }

        @Override
        public void run() {
            store.add(new User(1, 800));
        }
    }


    @Test
    public void whenWePutInAnotherTreadandThenDeleteInMain() throws InterruptedException {
        UserStorage store = new UserStorage();
        ThreadTest thread01 = new ThreadTest(store);
        store.add(new User(2, 100));

        thread01.start();
        thread01.join();

        assertTrue(store.delete(new User(1, 800)));

    }

    @Test
    public void whenWePutInAnotherTreadAndThenTranserFromMainThreadToThread01() throws InterruptedException {
        UserStorage store = new UserStorage();
        ThreadTest thread01 = new ThreadTest(store);
        store.add(new User(2, 100));

        thread01.start();
        thread01.join();

        store.transfer(1, 2, 200);

        int expected = 300;

        assertEquals(expected, store.get(2).getAccount());

    }
}