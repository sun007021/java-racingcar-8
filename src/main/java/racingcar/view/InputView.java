package racingcar.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {
    private static final String CAR_NAMES_INPUT_MESSAGE = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String ROUND_COUNT_INPUT_MESSAGE = "시도할 횟수는 몇 회인가요?";

    private InputView() {
    }

    public static List<String> readCarNames() {
        System.out.println(CAR_NAMES_INPUT_MESSAGE);
        String input = Console.readLine();
        return parseCarNames(input);
    }

    public static List<String> parseCarNames(String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static int readRoundCount() {
        System.out.println(ROUND_COUNT_INPUT_MESSAGE);
        String input = Console.readLine();
        return parseRoundCount(input);
    }

    public static int parseRoundCount(String input) {
        return Integer.parseInt(input);
    }
}