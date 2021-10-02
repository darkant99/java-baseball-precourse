package baseball.domain;

import baseball.exception.InvalidBallsSizeException;
import nextstep.utils.Randoms;

import java.util.*;

public class Balls {
    public static final int NORMAL_SIZE = 3;
    private static final List<Ball> BALL_TEMPLATE;

    static {
        BALL_TEMPLATE = new ArrayList<>();
        for (int iBallNumber = Ball.MIN_NUMBER; iBallNumber < Ball.MAX_NUMBER; iBallNumber++) {
            BALL_TEMPLATE.add(Ball.of(iBallNumber));
        }
    }

    private final List<Ball> balls;

    private Balls(final List<Ball> balls) {
        validateSize(balls);

        this.balls = balls;
    }

    /**
     * 중복된 수 없이 랜덤한 3개의 Ball로 구성된 Balls를 반환한다.
     * */
    public synchronized static Balls random() {
        //noinspection ComparatorMethodParameterNotUsed
        BALL_TEMPLATE.sort((o1, o2) -> Randoms.pickNumberInRange(-1, 1));

        return new Balls(BALL_TEMPLATE.subList(0, 3));
    }

    public static Balls of(List<Ball> balls) {
        return new Balls(balls);
    }

    private void validateSize(final List<Ball> balls) {
        if (new HashSet<>(balls).size() != NORMAL_SIZE) {
            throw new InvalidBallsSizeException();
        }
    }

    /**
     * GameResult의 결과를 계산한다.
     *
     * @param thatBalls 비교할 Balls
     * @throws InvalidBallsSizeException 중복을 포함하지 않은 Ball이 3개가 아닐 경우 발생한다.
     * */
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
