package ru.job4j.jmm;

/**
 * Class Demo
 *
 * @author Petr B.
 * @since 09.01.2020, 11:51
 */
public class Demo {
    private int count;

    public void growCount() {
        count++;
    }

    public void showCount() {
        System.out.println("Текущее состояние поля объекта " + count);
    }
}
