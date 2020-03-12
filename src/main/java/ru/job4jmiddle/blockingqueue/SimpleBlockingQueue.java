/**
 * Package ru.job4jmiddle.blockingqueue for
 *
 * @author Maksim Tiunchik
 */
package ru.job4jmiddle.blockingqueue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Class SimpleBlockingQueue -
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 12.03.2020
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {

    /**
     * collection for queue
     */
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    /**
     * current number positions in queue
     */
    private volatile int count = 0;

    /**
     * max positions in queue
     */
    private volatile int max = 10;

    /**
     * put object into queue
     *
     * @param value object for putting
     * @throws InterruptedException
     */
    public synchronized void offer(T value) throws InterruptedException {
        while (count == max) {
            this.wait();
        }
        count++;
        queue.add(value);
        this.notifyAll();
    }

    /**
     * get object from queue
     *
     * @return object
     * @throws InterruptedException
     */
    public synchronized T poll() throws InterruptedException {
        while (queue.isEmpty()) {
            this.wait();
        }
        count--;
        T temp = queue.poll();
        this.notifyAll();
        return temp;
    }

    /**
     * how many positions is in queue
     *
     * @return
     */
    public int getCount() {
        return count;
    }
}