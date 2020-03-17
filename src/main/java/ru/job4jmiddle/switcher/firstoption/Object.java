/**
 * Package ru.job4jmiddle.switcher for
 *
 * @author Maksim Tiunchik
 */
package ru.job4jmiddle.switcher.firstoption;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Class Object - first version of CyclicBarrier.
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
        Object obj = new Object();
        SecondThread second = new SecondThread(obj, 20);
        FirstThread first = new FirstThread(obj, 10, second);
        first.start();
        Thread.sleep(4000);
        System.out.println(obj.getStr());
        first.interrupt();
    }
}
