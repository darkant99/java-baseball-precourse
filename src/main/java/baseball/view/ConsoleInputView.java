package baseball.view;

import nextstep.utils.Console;

public class ConsoleInputView implements InputView {
    @Override
    public String inputBallNumbers() {
        System.out.print(GameText.INPUT_BALL_NUMBERS.text());
        return Console.readLine();
    }

    @Override
    public boolean inputGameContinue() {
        System.out.println(GameText.INPUT_GAME_CONTINUE.text());

        return Console.readLine()
                .equals("1");
    }

    private enum GameText {
        INPUT_BALL_NUMBERS("숫자를 입력 해주세요 : "),
        INPUT_GAME_CONTINUE("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");

        private final String text;

        GameText(final String text) {
            this.text = text;
        }

        public String text() {
            return text;
        }
    }
}
