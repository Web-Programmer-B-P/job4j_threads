package ru.job4j.elevator.abstractions;

import ru.job4j.elevator.interfaces.ICommonFunctionality;

/**
 * Class BaseFunctionality
 *
 * @author Petr B.
 * @since 23.01.2020, 16:22
 */
abstract public class BaseFunctionality implements ICommonFunctionality {
    private final String name;
    private final int index;

    public BaseFunctionality(String name, int index) {
        this.name = name;
        this.index = index;
    }

    @Override
    public void informationOfOperation() {
        System.out.printf("%s - %s\n", index, name);
    }

    @Override
    abstract public boolean executeOperation(boolean checkButtonCallElevator);
}
