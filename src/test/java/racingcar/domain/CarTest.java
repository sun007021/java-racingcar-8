package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarTest {

    @Test
    @DisplayName("자동차 생성 시 이름이 정상적으로 저장된다")
    void createCarWithName() {
        // given
        String name = "pobi";

        // when
        Car car = new Car(name);

        // then
        assertThat(car.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("자동차 생성 시 초기 위치는 0이다")
    void createCarWithInitialPosition() {
        // given
        String name = "pobi";

        // when
        Car car = new Car(name);

        // then
        assertThat(car.getPosition()).isEqualTo(0);
    }

    @Test
    @DisplayName("자동차가 전진하면 위치가 1 증가한다")
    void moveForward() {
        // given
        Car car = new Car("pobi");
        int initialPosition = car.getPosition();

        // when
        car.move();

        // then
        assertThat(car.getPosition()).isEqualTo(initialPosition + 1);
    }

    @Test
    @DisplayName("자동차가 여러 번 전진하면 위치가 누적된다")
    void moveForwardMultipleTimes() {
        // given
        Car car = new Car("pobi");

        // when
        car.move();
        car.move();
        car.move();

        // then
        assertThat(car.getPosition()).isEqualTo(3);
    }

    @Test
    @DisplayName("자동차 이름이 5자를 초과하면 예외가 발생한다")
    void createCarWithNameLongerThan5() {
        // given
        String name = "pobi12";

        // when & then
        assertThatThrownBy(() -> new Car(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("자동차 이름이 null이거나 빈 문자열이면 예외가 발생한다")
    void createCarWithNullOrEmptyName(String name) {
        // when & then
        assertThatThrownBy(() -> new Car(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름");
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "  ", "   "})
    @DisplayName("자동차 이름이 공백만 있으면 예외가 발생한다")
    void createCarWithBlankName(String name) {
        // when & then
        assertThatThrownBy(() -> new Car(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름");
    }

    @Test
    @DisplayName("다른 자동차와 위치를 비교할 수 있다")
    void comparePosition() {
        // given
        Car car1 = new Car("pobi");
        Car car2 = new Car("woni");

        car1.move();
        car1.move();
        car2.move();

        // when & then
        assertThat(car1.getPosition()).isGreaterThan(car2.getPosition());
    }

    @Test
    @DisplayName("자동차 이름은 5자까지 가능하다")
    void createCarWithName5Characters() {
        // given
        String name = "pobi1";

        // when
        Car car = new Car(name);

        // then
        assertThat(car.getName()).isEqualTo(name);
    }
}