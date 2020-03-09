package ru.job4j.wait.stop;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Class SimpleBlockingQueue
 *
 * @author Petr B.
 * @since 14.01.2020, 7:35
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    public synchronized void put(T value) {
        while (!(queue.size() == 0)) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.offer(value);
        this.notifyAll();
    }

    public synchronized T get() throws InterruptedException {
        while (queue.size() == 0) {
            this.wait();
        }
        T res = queue.poll();
        this.notifyAll();
        return res;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
