package baseball.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultsTest {
    private GameResults newGameResults(String resultsTemplate) {
        List<GameResult> gameResults = new ArrayList<>();
        for (String iGameResultName : resultsTemplate.split(",")) {
            gameResults.add(
                    GameResult.valueOf(iGameResultName)
            );
        }

        return GameResults.of(gameResults);
    }

    @DisplayName("GameResults.score 테스트")
    @CsvSource(
            value = {
                    "STRIKE,STRIKE,STRIKE=STRIKE=3",
                    "BALL,BALL,BALL=BALL=3",
                    "NOTHING,NOTHING,NOTHING=NOTHING=3",

                    "STRIKE,STRIKE,NOTHING=STRIKE=2",
                    "BALL,BALL,NOTHING=BALL=2",
                    "NOTHING,NOTHING,STRIKE=NOTHING=2",

                    "STRIKE,BALL,NOTHING=STRIKE=1",
                    "STRIKE,BALL,NOTHING=BALL=1",
                    "STRIKE,BALL,NOTHING=NOTHING=1",

            },
            delimiter = '='
    )
    @ParameterizedTest
    void score(String resultsTemplate, String expertName, int expertCount) {
        GameResults gameResults = newGameResults(resultsTemplate);
        GameResult expertResult = GameResult.valueOf(expertName);

        assertThat(
                gameResults.score(expertResult)
        ).isEqualTo(expertCount);
    }

    @DisplayName("GameResults.isAllStrike 테스트")
    @CsvSource(
            value = {
                    "STRIKE,STRIKE,STRIKE=true",
                    "STRIKE,STRIKE,BALL=false",
            },
            delimiter = '='
    )
    @ParameterizedTest
    void isAllStrikeTest(String resultsTemplate, boolean expert) {
        assertThat(newGameResults(resultsTemplate).isAllStrike())
                .isEqualTo(expert);
    }
}