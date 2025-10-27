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
    private final int rounds;

    public RacingGame(List<String> carNames, int rounds) {
        this.cars = carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
        this.rounds = rounds;
    }

    public List<Car> getCars() {
        return new ArrayList<>(cars);
    }

    public void play() {
        for (int i = 0; i < rounds; i++) {
            playRound();
        }
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
        int maxPosition = findMaxPosition();
        return cars.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    private int findMaxPosition() {
        return cars.stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElse(0);
    }
}