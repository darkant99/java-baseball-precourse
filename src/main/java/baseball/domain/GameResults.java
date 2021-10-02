package baseball.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameResults {
    private final Map<GameResult, Integer> countEachResult;

    private GameResults(final Map<GameResult, Integer> countEachResult) {
        this.countEachResult = countEachResult;
    }

    public static GameResults of(final List<GameResult> results) {
        Map<GameResult, Integer> countEachResult = new HashMap<>();

        for (GameResult iGameResult : GameResult.values()) {
            countEachResult.put(iGameResult, 0);
        }

        for (GameResult iGameResult : results) {
            increaseResultCount(countEachResult, iGameResult);
        }
        return new GameResults(countEachResult);
    }

    private static void increaseResultCount(Map<GameResult, Integer> countEachResult, GameResult gameResult) {
        int currentCount = countEachResult.get(gameResult);
        countEachResult.put(gameResult, currentCount + 1);
    }

    public int score(final GameResult expertResult) {
        return countEachResult.get(expertResult);
    }
}
