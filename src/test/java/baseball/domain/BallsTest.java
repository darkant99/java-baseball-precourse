package baseball.domain;

import baseball.exception.InvalidBallsSizeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class BallsTest {
    @DisplayName("생성자 테스트 - Ball은 중복되지 않는 3개의 수로 이루어져야 한다.")
    @ValueSource(strings= {
            "123",
            "456"
    })
    @ParameterizedTest
    void ctorTest(String ballTemplate) {
        assertDoesNotThrow(() -> Balls.of(ballTemplate));
    }

    @DisplayName("생성자 InvalidBallsSizeException 테스트")
    @ValueSource(strings= {
            "113",
            "12"
    })
    @ParameterizedTest
    void ctorInvalidBallsSizeExceptionTest(String ballTemplate) {
        assertThatThrownBy(() -> Balls.of(ballTemplate))
                .isInstanceOf(InvalidBallsSizeException.class);
    }

    @DisplayName("Balls matches 테스트 - 결과 반환 테스트")
    @CsvSource(
            value = {
                    "123=123=STRIKE=3",
                    "123=132=STRIKE=1",
                    "123=132=BALL=2",
                    "123=132=NOTHING=0"
            },
            delimiter = '='
    )
    @ParameterizedTest
    void matchesStrikeTest(String userBallTemplate, String computerBallTemplate,
                           String gameResultName, int expectCount) {
        Balls computerBalls = Balls.of(computerBallTemplate);
        Balls userBalls = Balls.of(userBallTemplate);

        GameResults result = computerBalls.matches(userBalls);
        GameResult expertResult = GameResult.valueOf(gameResultName);

        assertThat(result.score(expertResult))
                .isEqualTo(expectCount);
    }

    @DisplayName("Balls random 테스트 - 랜덤한 수가 서로 중복하지 않게 3개 뽑히는지 확인")
    @ValueSource(ints = {
            1000
    })
    @ParameterizedTest
    void randomTest(int testSize) {
        for (int i = 0; i < testSize; i++) {
            assertDoesNotThrow(Balls::random);
        }
    }
}
