package study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

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

    @DisplayName("(1,2) 값이 주어졋을때 substring을 활용해 ()를 제거 후 1,2가 반환 되는지 확인")
    @Test
    void substringTest() {
        String actual = "(1,2)";
        assertThat(actual.substring(1, actual.length() - 1))
                .isEqualTo("1,2");
    }

    @CsvSource({
            "abc,0,a",
            "abc,1,b",
            "abc,2,c"
    })
    @DisplayName("abc 값이 주어졌을때 charAt을 사용해 특정 위치의 문자를 잘 가져오는지 확인")
    @ParameterizedTest
    void charAtTest(String actual, int index, char expected) {
        assertThat(actual.charAt(index))
                .isEqualTo(expected);
    }

    @CsvSource({
            "abc,-1",
            "abc,3",
            "abc,4"
    })
    @DisplayName("abc 값이 주어졌을때 charAt을 사용할떄 문자열의 길이를 벗어나면 StringIndexOutOfBoundsException이 발생하는지 확인")
    @ParameterizedTest
    void charAtStringIndexOutOfBoundsExceptionTest(String actual, int index) {
        assertThatThrownBy(() -> actual.charAt(index))
                .isInstanceOf(StringIndexOutOfBoundsException.class);
    }
}
