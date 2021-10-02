package baseball.domain;

import baseball.exception.ErrorMessage;
import baseball.exception.OutOfValueBoundsException;

import java.util.HashMap;
import java.util.Map;

public class Ball {
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 9;

    private static final Map<Integer, Ball> cached;

    static {
        cached = new HashMap<>();
        for (int iNumber = MIN_NUMBER; iNumber <= MAX_NUMBER; iNumber++) {
            cached.put(iNumber, new Ball(iNumber));
        }
    }

    private final int number;

    private Ball(final int number) {
        this.number = number;
    }

    public static Ball of(final int number) {
        validateNumberBounds(number);

        return cached.get(number);
    }

    private static void validateNumberBounds(int number) {
        if (!cached.containsKey(number)) {
            throw new OutOfValueBoundsException(ErrorMessage.OUT_OF_BALL_NUMBER_BOUNDS);
        }
    }
}
