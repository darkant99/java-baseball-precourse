package baseball.exception;

public class OutOfValueBoundsException extends IllegalArgumentException {
    public OutOfValueBoundsException(ErrorMessage errorMessage) {
        super(errorMessage.message());
    }
}
