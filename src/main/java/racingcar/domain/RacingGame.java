package racingcar.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RacingGame {
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
        // TODO: 무작위 값 생성 및 이동 판단 로직 구현 예정
    }

    public List<String> getWinners() {
        // TODO: 구현 예정
        return null;
    }
}