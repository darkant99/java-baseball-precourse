package baseball.domain;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GameResults implements Iterable<Map.Entry<GameResult, Integer>> {
    private final Map<GameResult, Integer> countEachResult;

    private GameResults(final Map<GameResult, Integer> countEachResult) {
        this.countEachResult = countEachResult;
    }

    /**
     * GameResult별 갯수를 그룹화 한다.
     * */
    public static GameResults of(final List<GameResult> results) {
        Map<GameResult, Integer> countEachResult = newCountEachResult();

        for (GameResult iGameResult : results) {
            increaseResultCount(countEachResult, iGameResult);
        }
        return new GameResults(countEachResult);
    }

    private static Map<GameResult, Integer> newCountEachResult() {
        Map<GameResult, Integer> countEachResult = new TreeMap<>();

        for (GameResult iGameResult : GameResult.values()) {
            countEachResult.put(iGameResult, 0);
        }
        return countEachResult;
    }

    private static void increaseResultCount(Map<GameResult, Integer> countEachResult, GameResult gameResult) {
        int currentCount = countEachResult.get(gameResult);
        countEachResult.put(gameResult, currentCount + 1);
    }

    /**
     * GameResult별 갯수를 반환
     * */
    public int score(final GameResult expertResult) {
        return countEachResult.get(expertResult);
    }

    /**
     * 모든 GameResult가 스트라이크인지 확인 한다.
     * */
    public boolean isAllStrike() {
        return countEachResult.get(GameResult.STRIKE) == allSize();
    }

    /**
     * 모든 GameResult가 낫싱인지 확인 한다.
     * */
    public boolean isAllNothing() {
        return countEachResult.get(GameResult.NOTHING) == allSize();
    }

    private int allSize() {
        int sumValue = 0;
        for (int iCount : countEachResult.values()) {
            sumValue += iCount;
        }
        return sumValue;
    }

    @Override
    public Iterator<Map.Entry<GameResult, Integer>> iterator() {
        return countEachResult.entrySet().iterator();
    }
}
