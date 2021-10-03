package baseball.domain;

import baseball.exception.ErrorMessage;
import baseball.exception.OutOfValueBoundsException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Ball {
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 9;

    private static final Map<Integer, Ball> CACHED;

    static {
        CACHED = new HashMap<>();
        for (int iNumber = MIN_NUMBER; iNumber <= MAX_NUMBER; iNumber++) {
            CACHED.put(iNumber, new Ball(iNumber));
        }
    }

    private final int number;

    private Ball(final int number) {
        this.number = number;
    }

    /**
     * int 값을 포장한 Ball를 반환 한다.
     * */
    public static Ball of(final int number) {
        validateNumberBounds(number);

        return CACHED.get(number);
    }

    private static void validateNumberBounds(int number) {
        if (!CACHED.containsKey(number)) {
            throw new OutOfValueBoundsException(ErrorMessage.OUT_OF_BALL_NUMBER_BOUNDS);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Ball ball = (Ball) o;
        return number == ball.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
