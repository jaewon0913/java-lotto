package lotto.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.domain.Number;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class RandomNumbersGeneratorTest {

    @Test
    @DisplayName("번호를 랜덤하게 6개 생성할 수 있다.")
    void generateRandomNumbersTest() {

        // when
        List<Number> numbers = RandomNumbersGenerator.generateNumbers();

        // then
        assertThat(numbers.size()).isEqualTo(6);
    }

    @Test
    @RepeatedTest(10)
    @DisplayName("랜덤으로 생성한 로또 번호가 오름차순이어야 한다.")
    void generateNumbersAscTest() {

        // when
        List<Number> result = RandomNumbersGenerator.generateNumbers();

        // then
        for (int i = 0; i < result.size() - 1; i++) {
            assertThat(result.get(i)).isLessThan(result.get(i + 1));
        }
    }

}