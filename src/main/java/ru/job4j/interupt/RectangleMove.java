package ru.job4j.interupt;

import javafx.scene.shape.Rectangle;

/**
 * Class RectangleMove
 *
 * @author Petr B.
 * @since 25.12.2019, 13:11
 */
public class RectangleMove implements Runnable {
    private final Rectangle rect;
    private final static int MAX = 580;
    private final static int MIN = 290;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        int index = 0;
        while (!Thread.currentThread().isInterrupted()) {
            if (index < MIN) {
                rect.setX(rect.getX() + 1);
            }

            if (index > MIN && index < MAX) {
                rect.setX(rect.getX() - 1);
            }

            if (index > MAX) {
                index = 0;
            }
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                System.out.println("Так как поток уже прерван при вызове Thread.sleep выбрасывает исключение и флаг очищается.\n"
                        + "Поэтому мы снова вызываем метод прерываения текущего потока, это пометка для себя.");
                Thread.currentThread().interrupt();
            }
            index++;
        }
    }
}
