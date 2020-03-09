package ru.job4j.jmm;

/**
 * Class ShowProblems
 *
 * @author Petr B.
 * @since 09.01.2020, 11:47
 */
public class ShowProblems {
    /**
     * Когда мы запускаем в данном случае два потока с общим объектом, мы не знаем какой из потоков отработает первым и
     * какой из потоков первым увеличит поле, если не ставить задержку или не использовать метод join, мы не увидим
     * результат, потому что метод main мгновенно выполниться и умрет, поэтому был использован join чтобы можно было
     * увидеть конечное состояние объекта.
     */
    public static void main(String[] args) throws InterruptedException {
        Demo object = new Demo();
        Thread one = new Thread(new NewThread(object, 1));
        Thread two = new Thread(new NewThread(object, 2));
        one.start();
        two.start();
        one.join();
        two.join();
        object.showCount();
    }
}
