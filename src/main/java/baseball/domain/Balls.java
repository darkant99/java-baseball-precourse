package baseball.domain;

import baseball.exception.InvalidBallsSizeException;

import java.util.List;

public class Balls {
    public static final int NORMAL_SIZE = 3;

    private final List<Ball> balls;

    public Balls(final List<Ball> balls) {
        validateSize(balls);

        this.balls = balls;
    }

    private void validateSize(final List<Ball> balls) {
        if (balls.size() != NORMAL_SIZE) {
            throw new InvalidBallsSizeException();
        }
    }

    public GameResults matches(final Balls balls) {
        return new GameResults(null);
    }
}
