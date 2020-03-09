package ru.job4j.wait.producer;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class SimpleBlockingQueueTest
 *
 * @author Petr B.
 * @since 13.01.2020, 15:03
 */
public class SimpleBlockingQueueTest {
    @Test
    public void whenCheckWorkProducerAndConsumer() throws InterruptedException {
        SimpleBlockingQueue<Integer> buffer = new SimpleBlockingQueue<Integer>(3);
        Thread producer = new Thread(new Producer(buffer, 3));
        Consumer<Integer> result = new Consumer<Integer>(buffer);
        Thread consumer = new Thread(result);
        producer.start();
        producer.join();
        consumer.start();
        consumer.join();
        assertThat(result.getSize(), is(3));
    }
}