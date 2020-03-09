package ru.job4j.wait.producer;

/**
 * Class Producer
 *
 * @author Petr B.
 * @since 13.01.2020, 14:41
 */
public class Producer implements Runnable {
    private final SimpleBlockingQueue buffer;
    private int coll;

    public Producer(SimpleBlockingQueue queue, int collProduct) {
        buffer = queue;
        coll = collProduct;
    }

    @Override
    public void run() {
        int index = 1;
        while (index <= coll) {
            try {
                buffer.put(index++);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}
