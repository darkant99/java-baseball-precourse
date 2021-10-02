package baseball.domain;

import baseball.exception.OutOfValueBoundsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class BallTest {
    @DisplayName("Ball.of 정상 생성 테스트")
    @ValueSource(ints = {
            1, 9
    })
    @ParameterizedTest
    void ofTest(int number) {
        assertDoesNotThrow(() -> Ball.of(number));
    }

    @DisplayName("Ball.of OutOfValueBoundsException 테스트")
    @ValueSource(ints = {
            0, 10
    })
    @ParameterizedTest
    void ofOutOfValueBoundsExceptionTest(int number) {
        assertThatThrownBy(() -> Ball.of(number))
                .isInstanceOf(OutOfValueBoundsException.class);
    }
}