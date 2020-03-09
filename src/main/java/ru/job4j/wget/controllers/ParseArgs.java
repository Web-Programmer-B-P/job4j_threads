package ru.job4j.wget.controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class ParseArgs
 *
 * @author Petr B.
 * @since 10.01.2020, 8:21
 */
public class ParseArgs {
    private final String[] arr;
    private String path;

    public String getFileName() {
        return fileName;
    }

    private String fileName;
    private int speed;

    public String getPath() {
        return path;
    }

    public int getSpeed() {
        return speed;
    }

    public ParseArgs(String[] args) {
        arr = args;
        check();
    }

    private void check() {
        if (arr.length == 2) {
            if (findPath() != null) {
                path = findPath();
                speed = findSpeed();
                fileName = findFileName();
            }
        } else {
            System.err.println("Строка должна содержать 2 параметра url, speed например: java -jar wget.jar url 200");
        }
    }

    private String findPath() {
        String res = null;
        Pattern patternDir = Pattern.compile("(https?)");
        Matcher url = patternDir.matcher(arr[0]);
        if (url.find()) {
            res = arr[0];
        }
        return res;
    }

    private Integer findSpeed() {
        Integer res = null;
        try {
            res = Integer.parseInt(arr[1]);
        } catch (NumberFormatException ne) {
            System.err.println("Второй аргумент должен быть числом например: 300");
        }
        return res;
    }

    private String findFileName() {
        String originPath = findPath();
        int first = 0;
        int last = 0;
        for (int index = originPath.length() - 1, count = 0; index > 0; index--, count++) {
            if (count == 0) {
                last = index;
            } else if (count > 0 && originPath.charAt(index) == '/') {
                first = index;
                break;
            }
        }
        return originPath.substring(first, last + 1);
    }
}
