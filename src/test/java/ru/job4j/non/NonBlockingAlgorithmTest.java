package ru.job4j.non;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.non.exception.OptimisticException;
import ru.job4j.non.model.Base;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class NonBlockingAlgorithmTest
 *
 * @author Petr B.
 * @since 15.01.2020, 11:05
 */
public class NonBlockingAlgorithmTest {
    private NonBlockingAlgorithm storage;
    private Base base;

    @Before
    public void setUp() throws Exception {
        storage = new NonBlockingAlgorithm();
        base = new Base(1, 1, "initName");
        storage.add(base);
    }

    @Test
    public void whenAddNewModel() {
        assertThat(storage.getModelById(1).getVersion(), is(1));
    }

    @Test
    public void whenDelete() {
        storage.delete(base);
        assertThat(storage.getSize(), is(0));
    }

    @Test
    public void whenThrowException() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread thread = new Thread(
                () -> {
                    try {
                        throw new OptimisticException("Исключение выброшено");
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        thread.start();
        thread.join();
        assertThat(ex.get().getMessage(), is("Исключение выброшено"));
    }

    @Test
    public void whenUpdateModelTwoThreadsTheSameTime() throws InterruptedException {
        AtomicReference<Exception> optimistic = new AtomicReference<>();
        Thread thread1 = new Thread(
                () -> {
                    try {
                        storage.update(new Base(1, 1, "firstUpdate"));
                    } catch (OptimisticException e) {
                        optimistic.set(e);
                    }
                }
        );
        Thread thread2 = new Thread(
                () -> {
                    try {
                        storage.update(new Base(1, 1, "secondUpdate"));
                    } catch (OptimisticException e) {
                        optimistic.set(e);
                    }
                }
        );
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertThat(optimistic.get().getMessage(), is("version has already changed"));
    }
}