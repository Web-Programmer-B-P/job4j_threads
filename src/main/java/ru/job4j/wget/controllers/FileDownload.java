package ru.job4j.wget.controllers;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * Class FileDownload
 *
 * @author Petr B.
 * @since 08.01.2020, 18:20
 */
public class FileDownload implements Runnable {
    private final int speed;
    private final String url;
    private final String fileName;

    public FileDownload(String url, int speed, String fileName) {
        this.speed = speed;
        this.url = url;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("/home/proger/Загрузки/" + fileName)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            long startTime = System.currentTimeMillis();
            long actualTime = 0L;
            long endTime = 0L;
            long expectedTime = 0L;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                endTime = System.currentTimeMillis();
                actualTime = endTime - startTime;
                expectedTime = (bytesRead / speed) * 1000;
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                if (actualTime < expectedTime) {
                    System.out.println("Скачали быстрее чем надо пора поспать, время сна = " + (expectedTime - actualTime) + " секунд");
                    Thread.sleep(expectedTime - actualTime);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
