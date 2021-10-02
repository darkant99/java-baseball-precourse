package baseball.exception;

import baseball.domain.Ball;
import baseball.domain.Balls;

public enum ErrorMessage {
    INVALID_BALLS_SIZE(
            String.format(
                    "공의 수는 %d개로 입력 해주세요.",
                    Balls.NORMAL_SIZE
            )
    ),
    OUT_OF_BALL_NUMBER_BOUNDS(
            String.format(
                    "공의 수는 %d~%d 사이의 값을 사용 해주세요.",
                    Ball.MIN_NUMBER,
                    Ball.MAX_NUMBER
            )
    );

    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
