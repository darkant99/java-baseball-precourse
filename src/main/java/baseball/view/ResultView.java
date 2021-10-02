package baseball.view;

import baseball.domain.GameResults;
import baseball.exception.InvalidInputException;

public interface ResultView {
    void printGameResult(GameResults gameResults);

    void printGameEnd();

    void printException(InvalidInputException e);
}
