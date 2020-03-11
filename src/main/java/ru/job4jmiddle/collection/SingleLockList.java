/**
 * Package ru.job4jmiddle.collection for
 *
 * @author Maksim Tiunchik
 */
package ru.job4jmiddle.collection;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.list.LifeArrayList;

import java.util.Iterator;

/**
 * Class SingleLockList.
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 11.03.2020
 */
@ThreadSafe
public class SingleLockList<E> implements Iterable<E> {

    /**
     * simple example of collection.
     */
    @GuardedBy("this")
    private final LifeArrayList<E> innerList = new LifeArrayList<>();

    /**
     * put object into collction.
     *
     * @param value
     */
    public synchronized void add(E value) {
        innerList.add(value);
    }

    /**
     * return object E accordingly index.
     *
     * @param index index of onject
     * @return return object E
     */
    public synchronized E get(int index) {
        return innerList.get(index);
    }

    /**
     * return fail-safe iterator for SingleLockList.
     *
     * @return return fail-safe iterator
     */
    @Override
    public Iterator<E> iterator() {
        synchronized (this) {
            var temp = new LifeArrayList<E>();
            innerList.forEach(temp::add);
            return temp.iterator();
        }
    }
}
