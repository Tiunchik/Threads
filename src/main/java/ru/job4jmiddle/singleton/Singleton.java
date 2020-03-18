package ru.job4jmiddle.singleton;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Class Singleton example
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 18.03.2020
 */
public class Singleton {

    private static volatile AtomicReference<String> iNSTANCE;

    private Singleton() {
    }

    public static AtomicReference<String> getInstance() {
        if (iNSTANCE == null) {
            iNSTANCE = new AtomicReference<String>();
        }
        return iNSTANCE;
    }

    public String add(String model) {
        return model;
    }

    public static void main(String[] args) {
        AtomicReference<String> tracker = Singleton.getInstance();
    }
}

