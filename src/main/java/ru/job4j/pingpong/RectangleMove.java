package ru.job4j.pingpong;

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
        while (true) {
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
                e.printStackTrace();
            }
            index++;
        }
    }
}
