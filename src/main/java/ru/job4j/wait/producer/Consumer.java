package ru.job4j.wait.producer;

import java.util.ArrayList;

/**
 * Class Consumer
 *
 * @author Petr B.
 * @since 13.01.2020, 14:41
 */
public class Consumer<T> implements Runnable {
    private final SimpleBlockingQueue buffer;
    private ArrayList<T> list = new ArrayList<>();

    public Consumer(SimpleBlockingQueue queue) {
        buffer = queue;
    }

    @Override
    public void run() {
        try {
            while (buffer.getBufferSize() > 0) {
                list.add((T) buffer.get());
            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    public int getSize() {
        return list.size();
    }
}
