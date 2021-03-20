package LottoTest;

import lotto.domain.LottoGame;
import lotto.domain.LottoMatchNumbers;
import lotto.domain.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class LottoGameTest {
    @Test
    @DisplayName("LottoGame 인스턴스 생성 테스트")
    void Given_LottoNumber_When_New_Then_InstanceCreated() {
        //given
        List<Integer> givenLottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        //when
        LottoGame lottoGame = new LottoGame(givenLottoNumbers);

        //then
        assertThat(lottoGame).isEqualTo(new LottoGame(givenLottoNumbers));
    }

    @Test
    @DisplayName("LottoNumber를 인자로 받는 LottoGame 인스턴스 생성 테스트")
    void Given_LottoNumber_When_New_Then_InstanceCreate() {
        LottoNumber fixedLottoNumber = new FixedLottoNumber();

        //when
        LottoGame lottoGame = new LottoGame(fixedLottoNumber);

        //then
        assertThat(lottoGame).isEqualTo(new LottoGame(fixedLottoNumber.numbers()));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 6, 7})
    @DisplayName("로또 번호에 특정 번호가 있는지 확인 테스트")
    void Given_Number_When_Contains_Then_Expected(int number) {
        //given
        List<Integer> givenLottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        LottoGame lottoGame = new LottoGame(givenLottoNumbers);
        boolean expected = givenLottoNumbers.contains(number);

        //when
        boolean containsNumber = lottoGame.contains(number);

        //then
        assertThat(containsNumber).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideWinningNumbers")
    @DisplayName("당첨번호와 일치되는 로또 숫자의 개수 일치 여부 테스트")
    void Given_WinningNumbers_When_Result_Then_NumberOfMatchedNumbers(List<Integer> winningNumbers, int expected) {
        LottoGame lottoGame = new LottoGame(new FixedLottoNumber());

        //when
        LottoMatchNumbers lottoMatchNumbers = lottoGame.matchNumbers(winningNumbers);

        //then
        assertThat(lottoMatchNumbers).isEqualTo(new LottoMatchNumbers(expected));
    }

    private static Stream<Arguments> provideWinningNumbers() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), 6),
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 7), 5),
                Arguments.of(Arrays.asList(1, 2, 3, 4, 8, 7), 4),
                Arguments.of(Arrays.asList(1, 2, 3, 9, 8, 7), 3),
                Arguments.of(Arrays.asList(1, 2, 10, 9, 8, 7), 2),
                Arguments.of(Arrays.asList(1, 11, 10, 9, 8, 7), 1),
                Arguments.of(Arrays.asList(12, 11, 10, 9, 8, 7), 0)
        );
    }
}