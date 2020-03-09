package ru.job4j.elevator.interfaces;

/**
 * Class ICommonFunctionality
 *
 * @author Petr B.
 * @since 23.01.2020, 16:05
 */
public interface ICommonFunctionality {
    boolean executeOperation(boolean checkButtonCallElevator);
    void informationOfOperation();
}
