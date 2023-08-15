package blackjack.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AceCardTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        Card card = AceCard.of(new AceRank(), CardSuit.HEART);
        Card other = AceCard.of(new AceRank(), CardSuit.HEART);

        assertThat(card).isEqualTo(other);
    }

    @DisplayName("카드의 랭크가 알 수 없는 값(null)인 경우는 허용되지 않는다")
    @Test
    void checkFaceRank() {
        assertThatThrownBy(() -> AceCard.of(null, CardSuit.CLOVER))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효한 랭크를 입력해주세요.");
    }

    @DisplayName("카드의 무늬(suit)가 알 수 없는 값(null)인 경우는 허용되지 않는다")
    @Test
    void checkSuit() {
        assertThatThrownBy(() -> AceCard.of(new AceRank(), null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효한 카드의 무늬를 입력해주세요.");
    }

    @DisplayName("에이스 카드의 기본값은 1점이며 대체할 수 있는 점수는 10점이다.")
    @Test
    void calculatePoint() {
        Card card = AceCard.of(new AceRank(), CardSuit.CLOVER);

        Point point = card.calculatePoint();

        assertThat(point.get()).isEqualTo(1);
        assertThat(point.hasExchangeableValue()).isTrue();
        assertThat(point.exchange().get()).isEqualTo(10);
    }

    @DisplayName("에이스 카드의 이름은 영문 A와 무늬의 조합이다.")
    @Test
    void getFullName() {
        Card card = AceCard.of(new AceRank(), CardSuit.DIAMOND);

        String result = card.getFullName();

        assertThat(result).isEqualTo("A다이아몬드");
    }
}
