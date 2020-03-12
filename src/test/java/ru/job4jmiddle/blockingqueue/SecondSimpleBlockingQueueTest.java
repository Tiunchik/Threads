package ru.job4jmiddle.blockingqueue;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;


public class SecondSimpleBlockingQueueTest {

    final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
    final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();

    @Test
    public void test() throws InterruptedException {


        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int index = 5; index < 10; index++) {
                        queue.offer(index);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!(queue.getCount() == 0) || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            //e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();

        producer.join();

        consumer.interrupt();
        consumer.join();


        assertThat(buffer, is(Arrays.asList(5, 6, 7, 8, 9)));
    }
}