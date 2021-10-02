package baseball.domain;

import baseball.exception.InvalidBallsSizeException;
import nextstep.utils.Randoms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Balls {
    public static final int NORMAL_SIZE = 3;

    private final List<Ball> balls;

    private Balls(final List<Ball> balls) {
        validateSize(balls);

        this.balls = balls;
    }

    private void validateSize(final List<Ball> balls) {
        if (balls.size() != NORMAL_SIZE || new HashSet<>(balls).size() != NORMAL_SIZE) {
            throw new InvalidBallsSizeException();
        }
    }

    /**
     * 중복된 수 없이 랜덤한 3개의 Ball로 구성된 Balls를 반환한다.
     * */
    public static Balls random() {
        List<Ball> balls = new ArrayList<>();
        while(balls.size() < 3) {
            addRandomBall(balls);
        }

        return new Balls(balls);
    }

    private static void addRandomBall(List<Ball> balls) {
        Ball newBall = Ball.of(
                Randoms.pickNumberInRange(Ball.MIN_NUMBER, Ball.MAX_NUMBER)
        );
        if (balls.contains(newBall)) {
            addRandomBall(balls); // 이미 존재하는 볼이라면 재귀 호출
            return;
        }
        balls.add(newBall);
    }

    public static Balls of(String ballNumbers) {
        List<Ball> balls = new ArrayList<>();
        for (char iNumberOneLetter : ballNumbers.toCharArray()) {
            int iNumber = Integer.parseInt(
                    String.valueOf(iNumberOneLetter)
            );
            balls.add(Ball.of(iNumber));
        }
        return new Balls(balls);
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
