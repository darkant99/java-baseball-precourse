package baseball.domain;

import baseball.exception.InvalidBallsSizeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class BallsTest {
    private Balls newBalls(String ballTemplate) {
        List<Ball> balls = new ArrayList<>();
        for (String iBallString : ballTemplate.split(",")) {
            Ball iBall = Ball.of(
                    Integer.parseInt(iBallString)
            );
            balls.add(iBall);
        }

        return new Balls(balls);
    }

    @DisplayName("생성자 테스트 - Ball은 중복되지 않는 3개의 수로 이루어져야 한다.")
    @ValueSource(strings= {
            "1,2,3",
            "4,5,6"
    })
    void ctorTest(String ballTemplate) {
        assertDoesNotThrow(() -> newBalls(ballTemplate));
    }

    @DisplayName("생성자 InvalidBallsSizeException 테스트")
    @ValueSource(strings= {
            "1,1,3",
            "1,2"
    })
    void ctorInvalidBallsSizeExceptionTest(String ballTemplate) {
        assertThatThrownBy(() -> newBalls(ballTemplate))
                .isInstanceOf(InvalidBallsSizeException.class);
    }

    @DisplayName("Balls matches 테스트 - 결과 반환 테스트")
    @CsvSource(
            value = {
                    "1,2,3=1,3,2=STRIKE=1",
                    "1,2,3=1,3,2=BALL=2",
                    "1,2,3=1,3,2=NOTHING=0"
            },
            delimiter = '='
    )
    @ParameterizedTest
    void matchesStrikeTest(String userBallTemplate, String computerBallTemplate,
                           String gameResultName, int expectCount) {
        Balls computerBalls = newBalls(computerBallTemplate);
        Balls userBalls = newBalls(userBallTemplate);

        GameResults result = computerBalls.matches(userBalls);
        GameResult expertResult = GameResult.valueOf(gameResultName);

        assertThat(result.score(expertResult))
                .isEqualTo(expectCount);
    }
}
