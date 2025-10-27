package racingcar.view;

import java.util.List;

public class OutputView {
    private static final String RESULT_MESSAGE = "실행 결과";
    private static final String WINNER_PREFIX = "최종 우승자 : ";
    private static final String POSITION_SYMBOL = "-";
    private static final String CAR_STATUS_FORMAT = "%s : %s";
    private static final String WINNER_DELIMITER = ", ";

    private OutputView() {
    }

    public static void printResultMessage() {
        System.out.println();
        System.out.println(RESULT_MESSAGE);
    }

    public static void printCarStatus(String carName, int position) {
        System.out.println(formatCarStatus(carName, position));
    }

    public static String formatCarStatus(String carName, int position) {
        String positionDisplay = POSITION_SYMBOL.repeat(position);
        return String.format(CAR_STATUS_FORMAT, carName, positionDisplay);
    }

    public static void printRoundResult() {
        System.out.println();
    }

    public static void printWinners(List<String> winners) {
        System.out.println(formatWinners(winners));
    }

    public static String formatWinners(List<String> winners) {
        return WINNER_PREFIX + String.join(WINNER_DELIMITER, winners);
    }
}