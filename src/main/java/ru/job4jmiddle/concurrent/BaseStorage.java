/**
 * Package ru.job4jmiddle.concurrent for
 *
 * @author Maksim Tiunchik
 */
package ru.job4jmiddle.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4jmiddle.userstorage.User;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class BaseStorage.
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.2
 * @since 18.03.2020
 */
@ThreadSafe
public class BaseStorage {
    /**
     * inner ConcurrentHashMap for storing Users.
     */
    private final ConcurrentHashMap<Integer, Base> storage = new ConcurrentHashMap<>();

    /**
     * add base.
     *
     * @param base base for add
     * @return true if base is added
     */
    public boolean add(Base base) {
        storage.put(base.getId(), base);
        return storage.containsKey(base.getId());
    }

    /**
     * update base (use hashmap clash mechanic).
     *
     * @param base base to uodate
     * @return true if base is updated
     */
    public boolean update(Base base) {
        return delete(base) & add(base);
    }

    /**
     * delete base from storage.
     *
     * @param base base for deleting
     * @return true if base is deleted
     */
    public boolean delete(Base base) {
        storage.remove(base.getId());
        return !storage.containsKey(base.getId());
    }

    public int size() {
        return storage.size();
    }
}
