package baseball.domain;

public enum GameResult {
    STRIKE,
    BALL,
    NOTHING;

    /**
     * GameResult를 결정한다.
     *
     * @param thisIndex 현재 Balls의 인덱스
     * @param thatIndex 비교할 Ball에서 찾은 인덱스
     */
    public static GameResult of(int thisIndex, int thatIndex) {
        if (thisIndex == -1 || thatIndex == -1) {
            return NOTHING;
        }
        if (thisIndex == thatIndex) {
            return STRIKE;
        }
        return BALL;
    }
}
