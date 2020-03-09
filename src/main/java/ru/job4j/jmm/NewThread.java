package ru.job4j.jmm;

/**
 * Class NewThread
 *
 * @author Petr B.
 * @since 09.01.2020, 11:48
 */
public class NewThread implements Runnable {
    private final Demo val;
    private final int number;

    public NewThread(Demo value, int numberOfThread) {
        val = value;
        number = numberOfThread;
    }

    @Override
    public void run() {
        System.out.println("Это тред " + number);
        val.growCount();
    }
}
