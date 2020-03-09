package ru.job4j.elevator;

import ru.job4j.elevator.exception.ParameterNumberException;
import ru.job4j.elevator.input.UserInput;
import ru.job4j.elevator.lift.Elevator;
import ru.job4j.elevator.menu.ElevatorMenu;

/**
 * Class StartElevator
 *
 * @author Petr B.
 * @since 23.01.2020, 14:57
 */
public class StartElevator {
    private final ElevatorMenu menu;
    private final UserInput userInput;

    public StartElevator(ElevatorMenu menu, UserInput userInput) {
        this.menu = menu;
        this.userInput = userInput;
    }

    public void start() {
        boolean exit = true;
        menu.initMenu();
        menu.drawMenu();
        while (exit) {
            try {
                int res = userInput.getAnswer("Выберите вариант: ");
                exit = menu.doWork(res);
            } catch (IndexOutOfBoundsException | NumberFormatException | ParameterNumberException io) {
                System.out.println(io.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        UserInput input = new UserInput();
        try {
            new StartElevator(new ElevatorMenu(new Elevator(args), input), input).start();
        } catch (ParameterNumberException pne) {
            System.err.print(pne.getMessage());
        }
    }
}
