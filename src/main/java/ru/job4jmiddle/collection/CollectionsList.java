package ru.job4jmiddle.collection;

import java.util.Iterator;

public interface CollectionsList<E> extends Iterable<E> {

    public void add(E value);

    public Iterator<E> iterator();
}
