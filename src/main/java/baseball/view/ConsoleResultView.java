package baseball.view;

import baseball.domain.GameResult;
import baseball.domain.GameResults;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConsoleResultView implements ResultView {
    private static final String SPACE = " ";
    private static final String GAME_RESULT_FORMAT = "%d %s";

    @Override
    public void printGameResult(final GameResults gameResults) {
        if (gameResults.isAllNothing()) {
            System.out.println(GameResultNameText.NOTHING.text());
            return;
        }

        List<String> texts = new ArrayList<>();
        for (Map.Entry<GameResult, Integer> iGameResultAndCount : gameResults) {
            addGameResultText(texts, iGameResultAndCount);
        }

        String joinedText = String.join(SPACE, texts);
        System.out.println(joinedText);
    }

    private void addGameResultText(List<String> texts, Map.Entry<GameResult, Integer> entry) {
        GameResult gameResult = entry.getKey();
        int count = entry.getValue();

        // 낫싱은 표현하지 않는다.
        if (gameResult == GameResult.NOTHING || count <= 0) {
            return;
        }
        String gameResultText = String.format(
                GAME_RESULT_FORMAT,
                count,
                GameResultNameText.of(gameResult).text()
        );
        texts.add(gameResultText);
    }

    private enum GameResultNameText {
        STRIKE("스트라이크"),
        BALL("볼"),
        NOTHING("낫싱");

        private final String text;

        GameResultNameText(final String text) {
            this.text = text;
        }

        public String text() {
            return text;
        }

        public static GameResultNameText of(GameResult gameResult) {
            if (gameResult == GameResult.STRIKE) {
                return STRIKE;
            }
            if (gameResult == GameResult.BALL) {
                return BALL;
            }
            return NOTHING;
        }
    }
}
