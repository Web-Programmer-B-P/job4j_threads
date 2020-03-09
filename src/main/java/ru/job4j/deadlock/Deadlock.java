package ru.job4j.deadlock;

import java.util.concurrent.CountDownLatch;

/**
 * Class Deadlock
 *
 * @author Petr B.
 * @since 23.01.2020, 9:03
 */
public class Deadlock {
    private final static CountDownLatch COUNTER = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        Runnable run = () -> {
            try {
                COUNTER.countDown();
                System.out.println("Поток " + Thread.currentThread().getId() + " ожидает");
                COUNTER.await();
                System.out.println("Здесь мы будем ждать вечно, т.к нужно два потока чтобы блокировка отпустила");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread1 = new Thread(run);
        thread1.start();
        thread1.join();
        Thread thread2 = new Thread(run);
        thread2.start();
    }
}
