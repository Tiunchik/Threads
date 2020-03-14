/**
 * Package ru.job4jmiddle.concurrent for
 *
 * @author Maksim Tiunchik
 */
package ru.job4jmiddle.concurrent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Class Base - model of data.
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 14.03.2020
 */
public class Base {

    /**
     * contains verison of object, incremented with each setMethod.
     */
    private AtomicInteger version = new AtomicInteger(0);

    /**
     * id of base.
     */
    private int id;

    /**
     * name of base.
     */
    private String name;

    /**
     * constructor of model, set Id of base.
     *
     * @param id of base
     */
    public Base(int id) {
        this.id = id;
        increment();
    }

    /**
     * get current number of base version.
     *
     * @return number of base version
     */
    public int getVersion() {
        return version.get();
    }

    /**
     * increment number of base version.
     */
    private void increment() {
        version.incrementAndGet();
    }

    /**
     * get Id of object.
     *
     * @return Id of object
     */
    public int getId() {
        return id;
    }

    /**
     * change id of base.
     *
     * @param id new id
     */
    public void setId(int id) {
        int temp = version.get();
        this.id = id;
        increment();
        throwEx(temp);
    }

    /**
     * return name of base.
     *
     * @return name of base
     */
    public String getName() {
        return name;
    }

    /**
     * change name of base.
     *
     * @param name new name
     */
    public void setName(String name) {
        int temp = version.get();
        this.name = name;
        increment();
        throwEx(temp);
    }

    /**
     * thows exeption to main thread.
     *
     * @param temp current number of version
     */
    private void throwEx(int temp) {
        AtomicReference<Exception> ex = new AtomicReference<>();
        if (version.get() != temp + 1) {
            try {
                throw new OptimisticException("Data erased");
            } catch (Exception e) {
                ex.set(e);

            }
        }
    }
}
