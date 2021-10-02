package baseball;

import baseball.domain.Balls;
import baseball.domain.GameResults;
import baseball.view.ConsoleInputView;
import baseball.view.ConsoleResultView;
import baseball.view.InputView;
import baseball.view.ResultView;

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
            } while(inputView.inputGameContinue());
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
            return Balls.of(
                    inputView.inputBallNumbers()
            );
        }
    }
}
