package baseball.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

    @DisplayName("Balls matches 테스트 - 스트라이크 반환 테스트")
    @CsvSource(
            value = "1,2,3=1,3,2=1",
            delimiter = '='
    )
    @ParameterizedTest
    void matchesStrikeTest(String userBallTemplate, String computerBallTemplate,
                           int expectCount) {
        Balls computerBalls = newBalls(computerBallTemplate);
        Balls userBalls = newBalls(userBallTemplate);

        GameResult result = computerBalls.matches(userBalls);
        assertThat(result.strike())
                .isEqualTo(expectCount);
    }

    @DisplayName("Balls matches 테스트 - 볼 반환 테스트")
    @CsvSource(
            value = "1,2,3=1,3,2=2",
            delimiter = '='
    )
    @ParameterizedTest
    void matchesBallTest(String userBallTemplate, String computerBallTemplate,
                           int expectCount) {
        Balls computerBalls = newBalls(computerBallTemplate);
        Balls userBalls = newBalls(userBallTemplate);

        GameResult result = computerBalls.matches(userBalls);
        assertThat(result.ball())
                .isEqualTo(expectCount);
    }

    @DisplayName("Balls matches 테스트 - 낫싱 반환 테스트")
    @CsvSource(
            value = "1,2,3=1,3,2=1=0",
            delimiter = '='
    )
    @ParameterizedTest
    void matchesNothingTest(String userBallTemplate, String computerBallTemplate,
                           int expectCount) {
        Balls computerBalls = newBalls(computerBallTemplate);
        Balls userBalls = newBalls(userBallTemplate);

        GameResult result = computerBalls.matches(userBalls);
        assertThat(result.nothing())
                .isEqualTo(expectCount);
    }
}
