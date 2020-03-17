/**
 * Package ru.job4jmiddle.switcher for
 *
 * @author Maksim Tiunchik
 */
package ru.job4jmiddle.switcher.secondoption;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Class Object - second version of CyclicBarrier
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 16.03.2020
 */
@ThreadSafe
public class Object {

    private AtomicReference<String> str = new AtomicReference<>();

    Object() {
        str.set("");
    }

    public String getStr() {
        return str.get();
    }

    public void addStr(int variable) {
        this.str.set(getStr() + variable);
    }

    public static void main(String[] args) throws InterruptedException {

    }
}