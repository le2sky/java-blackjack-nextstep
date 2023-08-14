package blackjack.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NumericCardTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        Card card = NumericCard.of(2, CardSuit.SPADE);
        Card other = NumericCard.of(2, CardSuit.SPADE);

        assertThat(card).isEqualTo(other);
    }

    @DisplayName("카드의 무늬(suit)가 알 수 없는 값(null)인 경우는 허용되지 않는다")
    @Test
    void checkSuit() {
        assertThatThrownBy(() -> NumericCard.of(2, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효한 카드의 무늬를 입력해주세요.");
    }

    @DisplayName("카드의 숫자는 2부터 10까지만 허용한다.")
    @Test
    void checkRankRange() {
        assertThatThrownBy(() -> NumericCard.of(1, CardSuit.SPADE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드의 숫자는 2부터 10 사이의 값만 허용됩니다.");

        assertThatThrownBy(() -> NumericCard.of(11, CardSuit.SPADE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드의 숫자는 2부터 10 사이의 값만 허용됩니다.");
    }

    @DisplayName("숫자 카드의 포인트는 자신의 숫자와 동일하다.")
    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5, 6, 7, 8, 9, 10})
    void calculatePoint(int source) {
        Card card = NumericCard.of(source, CardSuit.DIAMOND);

        int result = card.calculatePoint();

        assertThat(result).isEqualTo(source);
    }

    @DisplayName("숫자 카드의 이름은 숫자와 무늬의 조합이다.")
    @Test
    void getFullName() {
        Card card = NumericCard.of(2, CardSuit.DIAMOND);

        String result = card.getFullName();

        assertThat(result).isEqualTo("2다이아몬드");
    }
}
