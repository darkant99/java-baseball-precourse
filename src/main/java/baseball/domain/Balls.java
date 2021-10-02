package baseball.domain;

import baseball.exception.InvalidBallsSizeException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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

    public GameResults matches(final Balls thatBalls) {
        List<GameResult> gameResults = new ArrayList<>();
        for (int thisIndex = 0; thisIndex < balls.size(); thisIndex++) {
            gameResults.add(
                    match(thatBalls, thisIndex)
            );
        }

        return GameResults.of(gameResults);
    }

    private GameResult match(final Balls thatBalls, final int thisIndex) {
        Ball currentBall = balls.get(thisIndex);
        return GameResult.of(
                thisIndex, thatBalls.balls.indexOf(currentBall)
        );
    }
}
