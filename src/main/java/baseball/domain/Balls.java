package baseball.domain;

import baseball.exception.InvalidBallsSizeException;

import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public class Balls {
    public static final int NORMAL_SIZE = 3;

    private final List<Ball> balls;

    public Balls(final List<Ball> balls) {
        validateSize(balls);

        this.balls = balls;
    }

    private void validateSize(final List<Ball> balls) {
        if (new HashSet<>(balls).size() != NORMAL_SIZE) {
            throw new InvalidBallsSizeException();
        }
    }

    public GameResults matches(final Balls balls) {
        return new GameResults(null);
    }
}
