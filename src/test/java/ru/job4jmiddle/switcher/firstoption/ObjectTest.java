package ru.job4jmiddle.switcher.firstoption;

import org.junit.Test;

import static org.junit.Assert.*;

public class ObjectTest {

    @Test
    public void simpleTest() throws InterruptedException {
        Object obj = new Object();
        SecondThread second = new SecondThread(obj, 20);
        FirstThread first = new FirstThread(obj, 10, second);
        first.start();
        Thread.sleep(4000);
        first.interrupt();

        String answer = obj.getStr();

        String expected = "10101010101010101010202020202020202020201010101010101010101020202020202020202020";

        assertEquals(expected, answer);

    }

}