package study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringTest {
    @DisplayName("1,2를 ,로 split 했을때 1과 2만을 포함 하는지 확인")
    @Test
    void splitTest1() {
        assertThat(
                "1,2".split(",")
        ).containsExactly("1", "2");
    }

    @DisplayName("1을 ,로 split 했을때 1만을 포함하는 배열이 반환되는지 확인")
    @Test
    void splitTest2() {
        assertThat(
                "1".split(",")
        ).containsExactly("1");
    }
}
