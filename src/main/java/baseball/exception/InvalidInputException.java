package baseball.exception;

public class InvalidInputException extends IllegalArgumentException {
    public InvalidInputException() {
        super(ErrorMessage.INVALID_INPUT.message());
    }

    public InvalidInputException(Exception e) {
        super(e.getMessage());
    }
}
