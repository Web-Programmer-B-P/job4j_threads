package ru.job4j.elevator.actions;

import ru.job4j.elevator.abstractions.BaseFunctionality;

/**
 * Class CallElevator
 *
 * @author Petr B.
 * @since 23.01.2020, 16:14
 */
public class CallElevator extends BaseFunctionality {

    public CallElevator(String name, int index) {
        super(name, index);
    }

    @Override
    public boolean executeOperation(boolean buttonCallElevator) {
        System.out.println("Лифт приехал заходите");
        return true;
    }
}
