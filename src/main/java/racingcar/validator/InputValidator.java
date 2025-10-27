package racingcar.validator;

public class InputValidator {
    private static final int MAX_NAME_LENGTH = 5;

    private InputValidator() {
    }

    public static void validateCarNames(String carNames) {
        if (carNames == null || carNames.isEmpty()) {
            throw new IllegalArgumentException("자동차 이름은 null이거나 빈 문자열일 수 없습니다.");
        }

        String[] names = carNames.split(",", -1);
        for (String name : names) {
            validateSingleCarName(name.trim());
        }
    }

    private static void validateSingleCarName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("자동차 이름은 빈 문자열일 수 없습니다.");
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException("자동차 이름은 공백만으로 이루어질 수 없습니다.");
        }
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("자동차 이름은 5자 이하여야 합니다.");
        }
    }

    public static void validateRoundCount(String roundCount) {
        if (roundCount == null || roundCount.isEmpty()) {
            throw new IllegalArgumentException("시도 횟수는 null이거나 빈 문자열일 수 없습니다.");
        }

        if (roundCount.isBlank()) {
            throw new IllegalArgumentException("시도 횟수는 공백일 수 없습니다.");
        }

        int count;
        try {
            count = Integer.parseInt(roundCount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("시도 횟수는 숫자여야 합니다.");
        }

        if (count <= 0) {
            throw new IllegalArgumentException("시도 횟수는 0보다 커야 합니다.");
        }
    }
}