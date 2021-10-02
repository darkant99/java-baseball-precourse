package baseball.domain;

import java.util.List;

public class Balls {
    private Ball balls;

    public Balls(final List<Ball> asList) {

    }

    public GameResults matches(final Balls userBalls) {
        return new GameResults();
    }
}
