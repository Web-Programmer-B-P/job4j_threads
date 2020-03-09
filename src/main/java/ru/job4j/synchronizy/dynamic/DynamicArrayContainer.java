package ru.job4j.synchronizy.dynamic;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class DynamicArrayContainer
 *
 * @author Petr B.
 * @since 09.10.2019, 10:00
 */
public class DynamicArrayContainer<E> implements Iterable<E> {
    private int currentSize = 3;
    private E[] container = (E[]) new Object[currentSize];
    private int modCount;
    private int position;

    public DynamicArrayContainer() {

    }

    public DynamicArrayContainer(int size) {
        this.currentSize = size;
    }

    public void add(E value) {
        this.modCount++;
        this.checkSizeContainer();
        this.container[this.position++] = value;
    }

    public E get(int index) {
        E result = null;
        E containt = this.container[index];
        if (containt != null) {
            result = containt;
        }
        return result;
    }

    private void checkSizeContainer() {
        if (this.position == this.currentSize) {
            this.currentSize = this.currentSize + (this.currentSize >> 1);
            this.container = Arrays.copyOf(this.container, this.currentSize);
        }
    }

    public int getSize() {
        return this.currentSize;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private final int expectedModCount = modCount;
            private int index = 0;

            @Override
            public boolean hasNext() {
                boolean result = false;

                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                if (this.index < position) {
                    result = true;
                }
                return result;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[this.index++];
            }
        };
    }
}
