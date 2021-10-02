package baseball.exception;

public class InvalidBallsSizeException extends RuntimeException {
    public InvalidBallsSizeException(ErrorMessage errorMessage) {
        super(errorMessage.message());
    }

    public InvalidBallsSizeException() {
        this(ErrorMessage.INVALID_BALLS_SIZE);
    }
}
