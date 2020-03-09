package ru.job4j.pool.worker;

import ru.job4j.wait.stop.SimpleBlockingQueue;

/**
 * Class WorkerThread
 *
 * @author Petr B.
 * @since 17.01.2020, 11:28
 */
public class WorkerThread extends Thread {
    private final SimpleBlockingQueue queue;
    private Runnable task;

    public WorkerThread(SimpleBlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        while (!isInterrupted()) {
            try {
                this.task = (Runnable) queue.get();
                task.run();
            } catch (InterruptedException e) {
//                e.printStackTrace();
            }
        }
    }
}
