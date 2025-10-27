package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RacingGame {
    private static final int RANDOM_NUMBER_MIN = 0;
    private static final int RANDOM_NUMBER_MAX = 9;
    private static final int MOVE_FORWARD_THRESHOLD = 4;

    private final List<Car> cars;

    public RacingGame(List<String> carNames) {
        this.cars = carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }

    public List<Car> getCars() {
        return new ArrayList<>(cars);
    }
    
    public void playRound() {
        for (Car car : cars) {
            tryMove(car);
        }
    }

    private void tryMove(Car car) {
        int randomNumber = Randoms.pickNumberInRange(RANDOM_NUMBER_MIN, RANDOM_NUMBER_MAX);
        if (randomNumber >= MOVE_FORWARD_THRESHOLD) {
            car.moveForward();
        }
    }

    public List<String> getWinners() {
        // TODO: 구현 예정
        return null;
    }
}