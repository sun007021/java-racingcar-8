package racingcar.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OutputViewTest {

    @Test
    @DisplayName("자동차 위치를 - 기호로 포맷팅한다")
    void formatCarPosition() {
        // given
        String carName = "pobi";
        int position = 3;

        // when
        String result = OutputView.formatCarStatus(carName, position);

        // then
        assertThat(result).isEqualTo("pobi : ---");
    }

    @ParameterizedTest
    @CsvSource({
            "pobi, 0, 'pobi : '",
            "pobi, 1, 'pobi : -'",
            "pobi, 2, 'pobi : --'",
            "pobi, 5, 'pobi : -----'"
    })
    @DisplayName("다양한 위치를 정확히 포맷팅한다")
    void formatVariousPositions(String carName, int position, String expected) {
        // when
        String result = OutputView.formatCarStatus(carName, position);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("단독 우승자를 포맷팅한다")
    void formatSingleWinner() {
        // given
        List<String> winners = List.of("pobi");

        // when
        String result = OutputView.formatWinners(winners);

        // then
        assertThat(result).isEqualTo("최종 우승자 : pobi");
    }

    @Test
    @DisplayName("공동 우승자를 쉼표로 구분하여 포맷팅한다")
    void formatMultipleWinners() {
        // given
        List<String> winners = Arrays.asList("pobi", "jun");

        // when
        String result = OutputView.formatWinners(winners);

        // then
        assertThat(result).isEqualTo("최종 우승자 : pobi, jun");
    }

    @Test
    @DisplayName("세 명 이상의 공동 우승자도 포맷팅한다")
    void formatThreeWinners() {
        // given
        List<String> winners = Arrays.asList("pobi", "woni", "jun");

        // when
        String result = OutputView.formatWinners(winners);

        // then
        assertThat(result).isEqualTo("최종 우승자 : pobi, woni, jun");
    }

    @Test
    @DisplayName("위치가 0인 자동차는 - 없이 이름만 출력한다")
    void formatCarWithZeroPosition() {
        // given
        String carName = "pobi";
        int position = 0;

        // when
        String result = OutputView.formatCarStatus(carName, position);

        // then
        assertThat(result).isEqualTo("pobi : ");
    }
}