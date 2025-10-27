package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RacingGameTest {

    @Test
    @DisplayName("게임 생성 시 자동차들이 정상적으로 저장된다")
    void createGameWithCars() {
        // given
        List<String> carNames = Arrays.asList("pobi", "woni", "jun");

        // when
        RacingGame game = new RacingGame(carNames, 5);

        // then
        assertThat(game.getCars()).hasSize(3);
    }

    @Test
    @DisplayName("라운드 실행 시 모든 자동차가 이동을 시도한다")
    void playOneRound() {
        // given
        List<String> carNames = Arrays.asList("pobi", "woni");
        RacingGame game = new RacingGame(carNames, 1);

        // when
        game.playRound();

        // then
        List<Car> cars = game.getCars();
        assertThat(cars).allMatch(car -> car.getPosition() >= 0);
    }

    @Test
    @DisplayName("여러 라운드를 실행할 수 있다")
    void playMultipleRounds() {
        // given
        List<String> carNames = Arrays.asList("pobi", "woni", "jun");
        int rounds = 5;
        RacingGame game = new RacingGame(carNames, rounds);

        // when
        game.play();

        // then
        List<Car> cars = game.getCars();
        assertThat(cars).allMatch(car -> car.getPosition() >= 0);
    }

    @Test
    @DisplayName("우승자를 판별할 수 있다 - 단독 우승")
    void findSingleWinner() {
        // given
        List<String> carNames = Arrays.asList("pobi", "woni", "jun");
        RacingGame game = new RacingGame(carNames, 5);

        // 특정 자동차만 전진시키기 (테스트를 위해 직접 조작)
        List<Car> cars = game.getCars();
        cars.get(0).moveForward(); // pobi 전진
        cars.get(0).moveForward(); // pobi 전진

        // when
        List<String> winners = game.getWinners();

        // then
        assertThat(winners).containsExactly("pobi");
    }

    @Test
    @DisplayName("우승자를 판별할 수 있다 - 공동 우승")
    void findMultipleWinners() {
        // given
        List<String> carNames = Arrays.asList("pobi", "woni", "jun");
        RacingGame game = new RacingGame(carNames, 5);

        // 두 자동차를 같은 거리로 전진시키기
        List<Car> cars = game.getCars();
        cars.get(0).moveForward(); // pobi
        cars.get(0).moveForward(); // pobi
        cars.get(1).moveForward(); // woni
        cars.get(1).moveForward(); // woni
        cars.get(2).moveForward(); // jun

        // when
        List<String> winners = game.getWinners();

        // then
        assertThat(winners).containsExactlyInAnyOrder("pobi", "woni");
    }

    @Test
    @DisplayName("모든 자동차가 같은 위치면 모두 우승자이다")
    void findAllWinnersWhenTied() {
        // given
        List<String> carNames = Arrays.asList("pobi", "woni", "jun");
        RacingGame game = new RacingGame(carNames, 5);

        // 모든 자동차를 같은 거리로
        List<Car> cars = game.getCars();
        cars.forEach(car -> {
            car.moveForward();
            car.moveForward();
        });

        // when
        List<String> winners = game.getWinners();

        // then
        assertThat(winners).containsExactlyInAnyOrder("pobi", "woni", "jun");
    }

    @Test
    @DisplayName("한 대의 자동차만 있어도 게임을 진행할 수 있다")
    void playGameWithSingleCar() {
        // given
        List<String> carNames = List.of("pobi");
        RacingGame game = new RacingGame(carNames, 1);

        // when
        game.playRound();
        List<String> winners = game.getWinners();

        // then
        assertThat(winners).containsExactly("pobi");
    }

    @Test
    @DisplayName("게임 시작 시 모든 자동차의 위치는 0이다")
    void initialPositionIsZero() {
        // given
        List<String> carNames = Arrays.asList("pobi", "woni", "jun");

        // when
        RacingGame game = new RacingGame(carNames, 5);

        // then
        List<Car> cars = game.getCars();
        assertThat(cars).allMatch(car -> car.getPosition() == 0);
    }

    @Test
    @DisplayName("현재 자동차 목록을 조회할 수 있다")
    void getCars() {
        // given
        List<String> carNames = Arrays.asList("pobi", "woni");
        RacingGame game = new RacingGame(carNames, 5);

        // when
        List<Car> cars = game.getCars();

        // then
        assertThat(cars).hasSize(2);
        assertThat(cars.get(0).getName()).isEqualTo("pobi");
        assertThat(cars.get(1).getName()).isEqualTo("woni");
    }
}