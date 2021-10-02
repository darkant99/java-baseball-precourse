package baseball.exception;

import baseball.domain.Ball;

public enum ErrorMessage {
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
