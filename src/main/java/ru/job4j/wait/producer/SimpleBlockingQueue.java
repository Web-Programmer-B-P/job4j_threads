package ru.job4j.wait.producer;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Class SimpleBlockingQueue
 *
 * @author Petr B.
 * @since 13.01.2020, 14:11
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private final int size;

    public SimpleBlockingQueue(int size) {
        this.size = size;
    }

    public synchronized void put(T value) throws InterruptedException {
        while (queue.size() >= size) {
            this.wait();
        }
        queue.offer(value);
        this.notify();
    }

    public synchronized T get() throws InterruptedException {
        while (queue.size() == 0) {
            this.wait();
        }
        T res = queue.poll();
        this.notify();
        return res;
    }

    public synchronized int getBufferSize() {
        return queue.size();
    }
}
