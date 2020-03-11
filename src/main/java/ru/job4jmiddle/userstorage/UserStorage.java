/**
 * Package ru.job4jmiddle.userstorage for
 *
 * @author Maksim Tiunchik
 */
package ru.job4jmiddle.userstorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

/**
 * Class UserStorage - storage for users
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 11.03.2020
 */
@ThreadSafe
public class UserStorage {
    /**
     * inner HashMap for storing Users
     */
    @GuardedBy("this")
    private final HashMap<Integer, User> storage = new HashMap<>();

    /**
     * add user
     *
     * @param user user for add
     * @return true if user is added
     */
    public synchronized boolean add(User user) {
        storage.put(user.getId(), user);
        return storage.containsKey(user.getId());
    }

    /**
     * update user (use hashmap clash mechanic)
     *
     * @param user user to uodate
     * @return true if user is updated
     */
    public synchronized boolean update(User user) {
        return delete(user) & add(user);
    }

    /**
     * delete user from storage
     *
     * @param user user for deleting
     * @return true if user is deleted
     */
    public synchronized boolean delete(User user) {
        storage.remove(user.getId());
        return !storage.containsKey(user.getId());
    }

    /**
     * receive user from storage by ID
     *
     * @param id ID for searching
     * @return user
     */
    public synchronized User get(int id) {
        return storage.get(id);
    }

    /**
     * transefer money from one user t another
     *
     * @param fromId id user whome money is transferred
     * @param toId   id user which receive money
     * @param amount amount of money
     */
    public synchronized void transfer(int fromId, int toId, int amount) {
        User temp = storage.get(fromId);
        temp.setAccount(temp.getAccount() - amount);
        temp = storage.get(toId);
        temp.setAccount(temp.getAccount() + amount);
    }
}
