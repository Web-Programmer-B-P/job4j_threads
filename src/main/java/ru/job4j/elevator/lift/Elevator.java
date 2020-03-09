package ru.job4j.elevator.lift;

import ru.job4j.elevator.exception.ParameterNumberException;

/**
 * Class Elevator
 *
 * @author Petr B.
 * @since 24.01.2020, 9:48
 */
public class Elevator {
    private final String[] args;

    public Elevator(String[] args) {
        this.args = args;
        init();
    }

    private void init() {
        if (args.length != 4) {
            throw new ParameterNumberException("Вы должны ввести 4ре параметра, было введено " + args.length + " попробуйте еще!"
                    + "\nПример запуска программы лифт: java -jar chapter_010/target/elevator.jar 10 2 1000 3000"
                    + "\nПараметр 1 это колличество этажей, например: 10"
                    + "\nПараметр 2 это высота этажа, например: 2 метра"
                    + "\nПараметр 3 это скорость лифта метров в секунду задается в милисекундах 1 секунда равна 1000, например: 1000"
                    + "\nПараметр 4 это время открытия/закрытия дверей лифта, например: 3000\n");
        }
    }

    public int getCountOfFloor() throws NumberFormatException {
        return Integer.parseInt(args[0]);
    }

    public int getHeightFloor() throws NumberFormatException {
        return Integer.parseInt(args[1]);
    }

    public int getSpeedMove() throws NumberFormatException {
        return Integer.parseInt(args[2]);
    }

    public int getTimeOpenAndCloseDoor() throws NumberFormatException {
        return Integer.parseInt(args[3]);
    }
}
