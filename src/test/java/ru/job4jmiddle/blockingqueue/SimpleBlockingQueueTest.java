package ru.job4jmiddle.blockingqueue;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    private class AdderThread extends Thread {

        SimpleBlockingQueue<Integer> queue;

        AdderThread(SimpleBlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            int i = 0;
            while (i++ <= 11) {
                try {
                    queue.offer(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void whenWeAddManyThenCheckThreadStatus() throws InterruptedException {
        SimpleBlockingQueue<Integer> testQueue = new SimpleBlockingQueue<>();

        AdderThread first = new AdderThread(testQueue);
        first.start();

        Thread.sleep(2000);
        System.out.println("State of adder thread " + first.getState());

        testQueue.poll();

        Thread.sleep(500);

        System.out.println("State of adder thread " + first.getState());

        assertSame(first.getState(), Thread.State.WAITING);

        testQueue.poll();

        Thread.sleep(500);

        assertSame(first.getState(), Thread.State.TERMINATED);

        System.out.println("State of adder thread " + first.getState());
    }

    private class PollThread extends Thread {

        SimpleBlockingQueue<Integer> queue;

        PollThread(SimpleBlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                queue.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void whenWeTryGetFromEmptyQueueThenCheckAndAdd() throws InterruptedException {
        SimpleBlockingQueue<Integer> testQueue = new SimpleBlockingQueue<>();
        PollThread pollThread = new PollThread(testQueue);
        pollThread.start();

        Thread.sleep(500);

        System.out.println("State of adder thread " + pollThread.getState());

        assertSame(pollThread.getState(), Thread.State.WAITING);

        testQueue.offer(1);

        Thread.sleep(500);

        assertSame(pollThread.getState(), Thread.State.TERMINATED);

        System.out.println("State of adder thread " + pollThread.getState());
    }
}