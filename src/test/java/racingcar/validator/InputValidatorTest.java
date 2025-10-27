package racingcar.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputValidatorTest {

    @Test
    @DisplayName("정상적인 자동차 이름들을 검증한다")
    void validateValidCarNames() {
        // given
        String carNames = "pobi,woni,jun";

        // when & then
        assertThatCode(() -> InputValidator.validateCarNames(carNames))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("자동차 이름 입력이 null이거나 빈 문자열이면 예외가 발생한다")
    void validateNullOrEmptyCarNames(String carNames) {
        // when & then
        assertThatThrownBy(() -> InputValidator.validateCarNames(carNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름");
    }

    @Test
    @DisplayName("자동차 이름 중 5자를 초과하는 이름이 있으면 예외가 발생한다")
    void validateCarNameLongerThan5() {
        // given
        String carNames = "pobi,javaji,woni";

        // when & then
        assertThatThrownBy(() -> InputValidator.validateCarNames(carNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름");
    }

    @ParameterizedTest
    @ValueSource(strings = {"pobi,,woni", ",pobi,woni", "pobi,woni,", "pobi, ,woni"})
    @DisplayName("자동차 이름 중 빈 이름이 있으면 예외가 발생한다")
    void validateCarNamesWithEmpty(String carNames) {
        // when & then
        assertThatThrownBy(() -> InputValidator.validateCarNames(carNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름");
    }

    @ParameterizedTest
    @ValueSource(strings = {"pobi,  ,woni", "pobi,   ,jun"})
    @DisplayName("자동차 이름 중 공백만 있는 이름이 있으면 예외가 발생한다")
    void validateCarNamesWithBlank(String carNames) {
        // when & then
        assertThatThrownBy(() -> InputValidator.validateCarNames(carNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름");
    }

    @Test
    @DisplayName("자동차 이름이 하나만 있어도 정상적으로 검증된다")
    void validateSingleCarName() {
        // given
        String carNames = "pobi";

        // when & then
        assertThatCode(() -> InputValidator.validateCarNames(carNames))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("정상적인 시도 횟수를 검증한다")
    void validateValidRoundCount() {
        // given
        String roundCount = "5";

        // when & then
        assertThatCode(() -> InputValidator.validateRoundCount(roundCount))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("시도 횟수가 null이거나 빈 문자열이면 예외가 발생한다")
    void validateNullOrEmptyRoundCount(String roundCount) {
        // when & then
        assertThatThrownBy(() -> InputValidator.validateRoundCount(roundCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("시도 횟수");
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "1.5", "일", "1a", " "})
    @DisplayName("시도 횟수가 숫자가 아니면 예외가 발생한다")
    void validateNonNumericRoundCount(String roundCount) {
        // when & then
        assertThatThrownBy(() -> InputValidator.validateRoundCount(roundCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("시도 횟수");
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1", "-100"})
    @DisplayName("시도 횟수가 0 이하이면 예외가 발생한다")
    void validateRoundCountLessThanOrEqualToZero(String roundCount) {
        // when & then
        assertThatThrownBy(() -> InputValidator.validateRoundCount(roundCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("시도 횟수");
    }

    @Test
    @DisplayName("시도 횟수가 정수 범위를 벗어나면 예외가 발생한다")
    void validateRoundCountOutOfIntegerRange() {
        // given
        String roundCount = "9999999999999999999";

        // when & then
        assertThatThrownBy(() -> InputValidator.validateRoundCount(roundCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("시도 횟수");
    }

    @Test
    @DisplayName("시도 횟수가 10이면 정상적으로 검증된다")
    void validateRoundCountOne() {
        // given
        String roundCount = "10";

        // when & then
        assertThatCode(() -> InputValidator.validateRoundCount(roundCount))
                .doesNotThrowAnyException();
    }
}