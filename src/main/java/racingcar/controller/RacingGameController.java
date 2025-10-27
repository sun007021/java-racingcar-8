package racingcar.controller;

import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.validator.InputValidator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;

public class RacingGameController {

    public void run() {
        List<String> carNames = readAndValidateCarNames();
        int roundCount = readAndValidateRoundCount();

        RacingGame game = new RacingGame(carNames, roundCount);

        OutputView.printResultMessage();
        playGameWithOutput(game, roundCount);

        List<String> winners = game.getWinners();
        OutputView.printWinners(winners);
    }

    private List<String> readAndValidateCarNames() {
        List<String> carNames = InputView.readCarNames();
        String input = String.join(",", carNames);
        InputValidator.validateCarNames(input);
        return carNames;
    }

    private int readAndValidateRoundCount() {
        int roundCount = InputView.readRoundCount();
        InputValidator.validateRoundCount(String.valueOf(roundCount));
        return roundCount;
    }

    private void playGameWithOutput(RacingGame game, int roundCount) {
        for (int i = 0; i < roundCount; i++) {
            game.playRound();
            printRoundResult(game);
        }
    }

    private void printRoundResult(RacingGame game) {
        List<Car> cars = game.getCars();
        for (Car car : cars) {
            OutputView.printCarStatus(car.getName(), car.getPosition());
        }
        OutputView.printRoundResult();
    }
}