/**
 * Package ru.job4jmiddle.deadlock for
 *
 * @author Maksim Tiunchik
 */
package ru.job4jmiddle.deadlock;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CountDownLatch;

/**
 * Class User - calss for deadlock cause
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 17.03.2020
 */
@ThreadSafe
public class User {

    @GuardedBy("this")
    private String name;

    User(String name) {
        this.name = name;
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized String greetings(User user) {
        String greeted = user.getName();
        return "User " + getName() + "greetings User " + greeted;
    }

    public static void main(String[] args) throws InterruptedException{
        User user1 = new User("first");
        User user2 = new User("second");

        CountDownLatch start = new CountDownLatch(1);

        GreetThread thread00 = new GreetThread(user1, user2, start);
        GreetThread thread01 = new GreetThread(user2, user1, start);
        thread00.start();
        thread01.start();


        Thread.sleep(500);

        start.countDown();

        Thread.sleep(500);

        System.out.println(thread00.getState().toString());
        System.out.println(thread01.getState().toString());

    }


}
