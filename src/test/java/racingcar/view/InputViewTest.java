package racingcar.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class InputViewTest {

    @Test
    @DisplayName("쉼표로 구분된 자동차 이름을 파싱한다")
    void parseCarNames() {
        // given
        String input = "pobi,woni,jun";

        // when
        List<String> carNames = InputView.parseCarNames(input);

        // then
        assertThat(carNames).containsExactly("pobi", "woni", "jun");
    }

    @Test
    @DisplayName("자동차 이름이 하나만 있어도 파싱된다")
    void parseSingleCarName() {
        // given
        String input = "pobi";

        // when
        List<String> carNames = InputView.parseCarNames(input);

        // then
        assertThat(carNames).containsExactly("pobi");
    }

    @ParameterizedTest
    @CsvSource({
            "'pobi,woni,jun', 3",
            "'pobi,woni', 2",
            "'pobi', 1",
            "'a,b,c,d,e', 5"
    })
    @DisplayName("여러 개의 자동차 이름을 정확히 파싱한다")
    void parseMultipleCarNames(String input, int expectedSize) {
        // when
        List<String> carNames = InputView.parseCarNames(input);

        // then
        assertThat(carNames).hasSize(expectedSize);
    }

    @Test
    @DisplayName("시도 횟수 문자열을 정수로 변환한다")
    void parseRoundCount() {
        // given
        String input = "5";

        // when
        int roundCount = InputView.parseRoundCount(input);

        // then
        assertThat(roundCount).isEqualTo(5);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1",
            "5, 5",
            "10, 10",
            "100, 100"
    })
    @DisplayName("다양한 시도 횟수를 정확히 파싱한다")
    void parseVariousRoundCounts(String input, int expected) {
        // when
        int roundCount = InputView.parseRoundCount(input);

        // then
        assertThat(roundCount).isEqualTo(expected);
    }

    @Test
    @DisplayName("자동차 이름 사이의 공백은 제거된다")
    void trimSpacesInCarNames() {
        // given
        String input = "pobi, woni, jun";

        // when
        List<String> carNames = InputView.parseCarNames(input);

        // then
        assertThat(carNames).containsExactly("pobi", "woni", "jun");
    }
}