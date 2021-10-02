package baseball.domain;

public enum GameResult {
    STRIKE, BALL, NOTHING;

    public static GameResult of(int thisIndex, int thatIndex) {
        if (thisIndex == -1 || thatIndex == -1) {
            return NOTHING;
        }
        return thisIndex == thatIndex ? STRIKE : BALL;
    }
}
