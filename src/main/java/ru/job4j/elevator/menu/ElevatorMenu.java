package ru.job4j.elevator.menu;

import ru.job4j.elevator.actions.CallElevator;
import ru.job4j.elevator.actions.ChooseFloor;
import ru.job4j.elevator.actions.Exit;
import ru.job4j.elevator.interfaces.ICommonFunctionality;
import ru.job4j.elevator.interfaces.IUserInput;
import ru.job4j.elevator.lift.Elevator;

import java.util.ArrayList;
import java.util.List;

/**
 * Class ElevatorMenu
 *
 * @author Petr B.
 * @since 23.01.2020, 15:19
 */
public class ElevatorMenu {
    private final List<ICommonFunctionality> operation;
    private final Elevator elevator;
    private int index;
    private boolean buttonCallElevator;
    private final IUserInput input;

    public ElevatorMenu(Elevator elevator, IUserInput input) {
        operation = new ArrayList<>();
        this.elevator = elevator;
        this.input = input;
    }

    public void initMenu() {
        operation.add(new CallElevator("Вызов лифта на этаж", index++));
        operation.add(new ChooseFloor("Выбор этажа", index++, elevator, input));
        operation.add(new Exit("Выход из программы", index++));
    }

    public void drawMenu() {
        for (ICommonFunctionality singleLineOfMenu : operation) {
            singleLineOfMenu.informationOfOperation();
        }
    }

    public boolean doWork(int indexOperation) {
        if (indexOperation == 0) {
            buttonCallElevator = true;
        }
        if (indexOperation > operation.size()) {
            throw new IndexOutOfBoundsException("Your number of operation incorrect! try to set one from these numbers - " + (operation.size() - 1));
        }
        return operation.get(indexOperation).executeOperation(buttonCallElevator);
    }
}
