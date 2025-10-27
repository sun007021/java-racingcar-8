package racingcar.domain;

public class Car {
    private static final int MAX_NAME_LENGTH = 5;
    private static final int INITIAL_POSITION = 0;

    private final String name;
    private int position;

    public Car(String name) {
        validateName(name);
        this.name = name;
        this.position = INITIAL_POSITION;
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("자동차 이름은 null이거나 빈 문자열일 수 없습니다.");
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException("자동차 이름은 공백만으로 이루어질 수 없습니다.");
        }
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("자동차 이름은 5자 이하여야 합니다.");
        }
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void moveForward() {
        position++;
    }
}