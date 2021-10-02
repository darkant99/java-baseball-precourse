package baseball.domain;

import java.util.List;
import java.util.Map;

public class GameResults {
    private final Map<GameResult, Integer> countEachResult;

    public GameResults(final Map<GameResult, Integer> countEachResult) {
        this.countEachResult = countEachResult;
    }

    public int score(final GameResult expertResult) {
        return -1;
    }
}
