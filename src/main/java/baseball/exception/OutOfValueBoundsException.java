package baseball.exception;

public class OutOfValueBoundsException extends RuntimeException {
    public OutOfValueBoundsException(ErrorMessage errorMessage) {
        super(errorMessage.message());
    }
}
