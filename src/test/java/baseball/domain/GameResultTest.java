package baseball.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameResultTest {
    @DisplayName("GameResult of - 인덱스를 비교해 결과값을 결정 한다.")
    @CsvSource({
            "1,1,STRIKE",
            "0,1,BALL",
            "1,-1,NOTHING"
    })
    @ParameterizedTest
    void ofTest(int thisIndex, int thatIndex, String gameResultName) {
        assertThat(
                GameResult.of(thisIndex, thatIndex)
        ).isEqualTo(
                GameResult.valueOf(gameResultName)
        );
    }
}