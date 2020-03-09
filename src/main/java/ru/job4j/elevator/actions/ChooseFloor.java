package ru.job4j.elevator.actions;

import ru.job4j.elevator.abstractions.BaseFunctionality;
import ru.job4j.elevator.exception.ParameterNumberException;
import ru.job4j.elevator.interfaces.IUserInput;
import ru.job4j.elevator.lift.Elevator;

/**
 * Class ChooseFloor
 *
 * @author Petr B.
 * @since 23.01.2020, 16:17
 */
public class ChooseFloor extends BaseFunctionality {
    private final Elevator elevator;
    private final IUserInput input;

    public ChooseFloor(String name, int index, Elevator elevator, IUserInput input) {
        super(name, index);
        this.elevator = elevator;
        this.input = input;
    }

    @Override
    public boolean executeOperation(boolean buttonCallElevator) {
        boolean res = false;
        if (buttonCallElevator) {
            try {
                int answer = input.getAnswer("\tВыберите этаж: ");
                if (answer < 1 || answer > elevator.getCountOfFloor()) {
                    throw new ParameterNumberException("\tIn this house doesn't have this floor " + answer);
                }
                System.out.println("\tВы выбрали этаж - " + answer + " осторожно двери закрываются");
                Thread.sleep(elevator.getTimeOpenAndCloseDoor());
                doMove(answer);
                System.out.println("\tОсторожно двери открываются");
                Thread.sleep(elevator.getTimeOpenAndCloseDoor());
            } catch (InterruptedException nfe) {
                nfe.printStackTrace();
            }
            res = true;
        } else {
            System.out.println("Эта операция доступна после вызова лифта!!!");
        }
        return res;
    }

    private void doMove(int countOfFloor) throws InterruptedException {
        for (int index = 1; index <= countOfFloor; index++) {
            if (index < countOfFloor) {
                Thread.sleep(elevator.getSpeedMove() * elevator.getHeightFloor());
                System.out.println("\tПроезжаем этаж " + index);
            } else {
                Thread.sleep(elevator.getSpeedMove() * elevator.getHeightFloor());
                System.out.println("\tВаш этаж " + index);
            }
        }
    }
}
