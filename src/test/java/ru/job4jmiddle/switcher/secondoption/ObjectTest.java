package ru.job4jmiddle.switcher.secondoption;

import org.junit.Test;

import java.util.concurrent.CyclicBarrier;

import static org.junit.Assert.*;

public class ObjectTest {

    @Test
    public void simpleTest() throws InterruptedException {
        Object obj = new Object();
        CyclicBarrier first = new CyclicBarrier(2);
        CyclicBarrier second = new CyclicBarrier(2);
        First thread00 = new First(obj, 10, first, second);
        Second thread01 = new Second(obj, 20, first, second);
        thread00.start();
        thread01.start();

        Thread.sleep(4000);

        thread00.interrupt();
        thread01.interrupt();

        String answer = obj.getStr();

        String expected = "10101010101010101010202020202020202020201010101010101010101020202020202020202020";

        assertEquals(expected, answer);

    }

}