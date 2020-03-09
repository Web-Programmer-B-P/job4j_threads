package ru.job4j.wget;

import ru.job4j.wget.controllers.FileDownload;
import ru.job4j.wget.controllers.ParseArgs;

/**
 * Class MainWget
 *
 * @author Petr B.
 * @since 08.01.2020, 18:25
 */
public class MainWget {
    public static void main(String[] args) throws InterruptedException {
        ParseArgs arg = new ParseArgs(args);
        Thread download = new Thread(new FileDownload(arg.getPath(), arg.getSpeed(), arg.getFileName()));
        download.start();
        download.join();
        System.out.println("Скачивание завершено, файл сохранен!");
    }
}
