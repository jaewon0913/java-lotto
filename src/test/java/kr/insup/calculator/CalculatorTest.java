package kr.insup.calculator;

import kr.insup.calculator.domain.Calculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CalculatorTest {

    Calculator calculator = new Calculator();

    @DisplayName("문자열 받아 합 계산")
    @ParameterizedTest
    @CsvSource(value = {"1,2,3!6", "1:2,4!7", "10,4,2!16", "13,24,2!39"}, delimiter = '!')
    void 문자열_받아_합_계산(String input, int expected) {
        //given
        int result = calculator.calculate(input);

        //then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("문자열 하나일 때 계산")
    @ParameterizedTest
    @CsvSource({"6, 6", "4, 4", "103, 103"})
    void 문자열_하나일_때_계산(String input, int expected) {
        //given
        int result = calculator.calculate(input);

        //then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("문자열 비어있을 때 계산")
    @Test
    void 문자열_비어있을_때_계산() {
        //given
        int result = calculator.calculate("");

        //then
        assertThat(result).isEqualTo(0);
    }

    @DisplayName("커스텀 구분자 있을 때 계산")
    @ParameterizedTest
    @MethodSource("kr.insup.calculator.TestHelper#provideCalculateExample")
    void 커스텀_구분자_있을_때_계산(String input, int expected) {
        //given
        int result = calculator.calculate(input);

        //then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("숫자 이외의 값 또는 음수가 있을 때 예외 처리")
    @ParameterizedTest
    @ValueSource(strings = {"-1", "14:-1:6", "3,5!6", "//$\n-1$2$4", "//@\n0@q@2", "//(\n1(2)3"})
    void 숫자_이외의_값_또는_음수가_있을_때_예외_처리(String input) {
        //given,then
        assertThatThrownBy(() -> calculator.calculate(input))
                .isInstanceOf(NumberFormatException.class);
    }
}