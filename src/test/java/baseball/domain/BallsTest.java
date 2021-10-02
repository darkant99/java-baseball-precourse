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
