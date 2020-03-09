package ru.job4j.synchronizy.wrapper.interfaces;

import java.util.Iterator;

/**
 * Class IThreadSafe
 *
 * @author Petr B.
 * @since 12.01.2020, 15:09
 */
public interface IThreadSafe<E> extends Iterable<E> {
    void add(E value);
    E get(int index);
    int getSize();
    Iterator<E> iterator();
}
