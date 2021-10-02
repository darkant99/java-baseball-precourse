package baseball;

import baseball.domain.Balls;
import baseball.domain.GameResults;
import baseball.exception.InvalidInputException;
import baseball.view.ConsoleInputView;
import baseball.view.ConsoleResultView;
import baseball.view.InputView;
import baseball.view.ResultView;

import java.util.NoSuchElementException;

public class Application {
    public static void main(String[] args) {
        new Solution(
                new ConsoleInputView(),
                new ConsoleResultView()
        ).run();
    }

    private static class Solution {
        private final InputView inputView;
        private final ResultView resultView;

        private Solution(final InputView inputView, final ResultView resultView) {
            this.inputView = inputView;
            this.resultView = resultView;
        }

        public void run() {
            do {
                Balls computerBalls = Balls.random();

                runningRound(computerBalls);
            } while(inputContinue());
        }

        private void runningRound(Balls computerBalls) {
            GameResults gameResults;
            do {
                Balls userBalls = inputBalls();

                gameResults = userBalls.matches(computerBalls);
                resultView.printGameResult(gameResults);
            } while(!gameResults.isAllStrike());
            resultView.printGameEnd();
        }

        private Balls inputBalls() {
            try {
                return Balls.of(inputView.inputBallNumbers());
            } catch (NoSuchElementException e) {
                // 테스트 게임 종료에 해당
                throw e;
            } catch (Exception e) {
                resultView.printException(new InvalidInputException(e));

                return inputBalls();
            }
        }

        private boolean inputContinue() {
            String inputText = inputView.inputGameContinue();

            if (inputText.equals("1")) {
                return true;
            }
            if (inputText.equals("2")) {
                return false;
            }
            resultView.printException(new InvalidInputException());
            return inputContinue();
        }
    }
}
