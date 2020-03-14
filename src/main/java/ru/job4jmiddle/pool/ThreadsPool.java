/**
 * Package ru.job4jmiddle.pool for
 *
 * @author Maksim Tiunchik
 */
package ru.job4jmiddle.pool;

import ru.job4jmiddle.blockingqueue.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * Class Pool - simple copy of threads pool
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 14.03.2020
 */
public class ThreadsPool {

    /**
     * list of pools
     */
    private final List<Thread> threads = new LinkedList<>();

    /**
     * blocking queue of tasks
     */
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    /**
     * standart constructor, use quantity of avaliable logic processors
     */
    public ThreadsPool() {
        int size = Runtime.getRuntime().availableProcessors();
        for (int index = 0; index < size; index++) {
            threads.add(new PoolThread(tasks));
        }
        threads.forEach(Thread::start);
    }

    /**
     * constructor with settable number of using threads for work
     *
     * @param size number of useable threads
     */
    public ThreadsPool(int size) {
        for (int index = 0; index < size; index++) {
            threads.add(new PoolThread(tasks));
        }
        threads.forEach(Thread::start);
    }

    /**
     * put runnable task for execution
     *
     * @param job rannable task
     */
    public void work(Runnable job) {
        try {
            tasks.offer(job);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * interrupt all used threads
     */
    public void shutdown() {
        for (var e : threads) {
            e.interrupt();
        }
    }

    /**
     * inner special class of thread that is used blocking qeue for execution method run()
     */
    private class PoolThread extends Thread {
        /**
         * blockingQueu with tasks
         */
        private final SimpleBlockingQueue<Runnable> queue;

        /**
         * constructor for poolthread, make connections between blockinqueue and all threads
         *
         * @param queue
         */
        PoolThread(SimpleBlockingQueue<Runnable> queue) {
            this.queue = queue;
        }

        /**
         * overided method run, poll and start task.run() from queue
         */
        @Override
        public void run() {
            while (!this.isInterrupted()) {
                try {
                    queue.poll().run();
                } catch (InterruptedException e) {
                    this.interrupt();
                }
            }
        }
    }
}

