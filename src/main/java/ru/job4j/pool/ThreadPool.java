package ru.job4j.pool;

import ru.job4j.pool.worker.WorkerThread;
import ru.job4j.wait.stop.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * Class ThreadPool
 *
 * @author Petr B.
 * @since 17.01.2020, 11:00
 */
public class ThreadPool {
    private final List<WorkerThread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<Runnable>();
    private final int sizeOfPool;

    public ThreadPool(int size) {
        sizeOfPool = size;
        for (int index = 0; index < sizeOfPool; index++) {
            WorkerThread thread = new WorkerThread(tasks);
            threads.add(thread);
        }
        for (WorkerThread startEachThread : threads) {
            startEachThread.start();
        }
    }

    public void work(Runnable job) {
        tasks.put(job);
    }

    public void shutdown() {
        for (WorkerThread stop : threads) {
            stop.interrupt();
        }
    }
}
