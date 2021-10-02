package study;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class SetCollectionTest {
    private Set<Integer> numbers;

    @SuppressWarnings("OverwrittenKey")
    @BeforeEach
    void setUp() {
        numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
    }

    @DisplayName("Set의 size를 사용해 Set의 크기를 확인")
    @Test
    void sizeTest() {
        assertThat(numbers.size())
                .isEqualTo(3);
    }

    @DisplayName("Set에 contains를 사용해 1,2,3 값이 존재하는지 확인")
    @ValueSource(ints = {
            1, 2, 3
    })
    @ParameterizedTest
    void containsTest(int containsValue) {
        assertThat(numbers.contains(containsValue))
                .isTrue();
    }
}
