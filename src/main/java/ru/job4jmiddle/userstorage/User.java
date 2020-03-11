/**
 * Package ru.job4jmiddle.userstorage for
 *
 * @author Maksim Tiunchik
 */
package ru.job4jmiddle.userstorage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

/**
 * Class User -
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 11.03.2020
 */
public class User {
    /**
     * user id
     */
    private int id;
    /**
     * user account with money
     */
    private int account;

    /**
     * unbasic constructor, set id and account
     *
     * @param id      id number for user
     * @param account amount of money on user account
     */
    public User(int id, int account) {
        this.id = id;
        this.account = account;
    }

    /**
     * return user Id
     *
     * @return user ID
     */
    public int getId() {
        return id;
    }

    /**
     * change user Id
     *
     * @param id new ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * get amount of money on user account
     *
     * @return amount of money on user account
     */
    public int getAccount() {
        return account;
    }

    /**
     * change amount of money on user account
     *
     * @param account new amount of money on user account
     */
    public void setAccount(int account) {
        this.account = account;
    }
}
