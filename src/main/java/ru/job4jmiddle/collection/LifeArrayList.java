/**
 * Пакет для работы с коллекциями, разаботка упрощённых аналогов коллекций
 *
 * @author Maksim Tiunchik
 */
package ru.job4jmiddle.collection;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс LifeArrayList - класс коллекция-массив
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 06.01.2019
 */
public class LifeArrayList<E> implements CollectionsList<E>, Iterable<E> {

    /**
     * Массив где всё хранится
     */
    private Object[] base;
    /**
     * Текущая позиция для добавления элементов в массив
     */
    private int position = 0;

    private int modCount = 0;

    /**
     * Конструктор при которомбазовое значение массива равно 10
     */
    public LifeArrayList() {
        this.base = new Object[10];
    }

    /**
     * Геттер на modCount
     *
     * @return возвращает значение modCount
     */
    public int getModCount() {
        return modCount;
    }

    /**
     * Конструктор при котором задаётся базовый размер внутреннего массива
     *
     * @param size - базовое значение
     */
    public LifeArrayList(int size) {
        if (size > 0) {
            this.base = new Object[size];
        } else {
            new LifeArrayList();
        }
    }

    /**
     * Добавления значения в коллекцию
     *
     * @param temp - добавляемое значение
     */
    @Override
    public void add(E temp) {
        if (position == base.length) {
            this.base = Arrays.copyOf(this.base, (int) (position * 1.5));
        }
        this.base[position] = temp;
        position++;
        this.modCount++;
    }

    /**
     * геттер для поля position
     *
     * @return возвращает размер массива
     */
    public int size() {
        return position;
    }

    /**
     * Возврат значения из ячейки
     *
     * @param index - номер ячейки
     * @return - возвращаемое значение типа Т
     */
     public E get(int index) {
        return (E) this.base[index];
    }

    /**
     * Иретатор для массива
     *
     * @return - озвращает иретатор для массива
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private Object[] iterBase = base;
            private int iterPos = 0;
            private int end = position;
            private final int modCountStart = modCount;

            @Override
            public boolean hasNext() {
                return iterPos < end;
            }

            @Override
            public E next() {
                if (modCountStart != getModCount()) {
                    throw new ConcurrentModificationException("Array was modificated");
                }
                if (!hasNext()) {
                    throw new NoSuchElementException("Array is expired");
                }
                return (E) iterBase[iterPos++];
            }
        };
    }
}
