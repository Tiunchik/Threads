/**
 * Package ru.job4jmiddle.synscount for.
 *
 * @author Maksim Tiunchik
 */
package ru.job4jmiddle.synscount;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Class Count - safe multithreading counter
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 11.03.2020
 */
@ThreadSafe
public class Count {

    /**
     * counter
     */
    @GuardedBy("this")
    private volatile int count = 0;

    /**
     * increment counter
     */
    public void increment() {
        count++;
    }

    /**
     * get counter
     *
     * @return counter
     */
    public int getCount() {
         return count;
    }
}
