package ru.job4jmiddle.collection;

import org.junit.Test;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SingleLockListTest {

    @Test
    public void whenWeAddThenFind() throws InterruptedException {
        SingleLockList<Integer> list = new SingleLockList<>();
        Thread first = new Thread(() -> list.add(1));
        Thread second = new Thread(() -> list.add(2));
        first.start();
        second.start();
        first.join();
        second.join();
        Set<Integer> rsl = new TreeSet<>();
        list.iterator().forEachRemaining(rsl::add);
        assertThat(rsl, is(Set.of(1, 2)));
    }

    @Test
    public void whenWeAddThenUserIteratorAndAdd() throws InterruptedException {
        SingleLockList<Integer> list = new SingleLockList<>();
        Thread first = new Thread(() -> list.add(1));
        Thread second = new Thread(() -> list.add(2));
        first.start();
        second.start();
        first.join();
        second.join();
        Set<Integer> rsl = new TreeSet<>();
        Iterator temp = list.iterator();
        Thread third = new Thread(() -> list.add(3));
        third.start();
        third.join();
        temp.forEachRemaining(e -> rsl.add((Integer) e));
        assertThat(rsl, is(Set.of(1, 2)));
    }

}