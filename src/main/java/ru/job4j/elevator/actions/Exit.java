package ru.job4j.elevator.actions;

import ru.job4j.elevator.abstractions.BaseFunctionality;

/**
 * Class Exit
 *
 * @author Petr B.
 * @since 23.01.2020, 17:24
 */
public class Exit extends BaseFunctionality {
    public Exit(String name, int index) {
        super(name, index);
    }

    @Override
    public boolean executeOperation(boolean buttonCallElevator) {
        return false;
    }
}
