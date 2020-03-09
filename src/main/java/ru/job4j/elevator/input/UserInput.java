package ru.job4j.elevator.input;

import ru.job4j.elevator.interfaces.IUserInput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class UserInput
 *
 * @author Petr B.
 * @since 23.01.2020, 16:55
 */
public class UserInput implements IUserInput {
    private BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public int getAnswer(String question) {
        int res = -1;
        System.out.print(question);
        try {
            res = Integer.parseInt(buffer.readLine());
        } catch (IOException | NumberFormatException io) {
            throw new NumberFormatException("You entered a line but, you need a number!");
        }
        return res;
    }
}
