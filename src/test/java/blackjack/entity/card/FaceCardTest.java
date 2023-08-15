package blackjack.entity.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.entity.common.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FaceCardTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        Card card = FaceCard.of(FaceShape.KING, CardSuit.SPADE);
        Card other = FaceCard.of(FaceShape.KING, CardSuit.SPADE);

        assertThat(card).isEqualTo(other);
    }

    @DisplayName("카드의 얼굴 모양(shape)가 알 수 없는 값(null)인 경우는 허용되지 않는다")
    @Test
    void checkShape() {
        assertThatThrownBy(() -> FaceCard.of(null, CardSuit.CLOVER))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효한 카드의 얼굴 모양을 입력해주세요.");
    }

    @DisplayName("카드의 무늬(suit)가 알 수 없는 값(null)인 경우는 허용되지 않는다")
    @Test
    void checkSuit() {
        assertThatThrownBy(() -> FaceCard.of(FaceShape.QUEEN, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효한 카드의 무늬를 입력해주세요.");
    }

    @DisplayName("얼굴 모양 카드의 포인트는 항상 10이다.")
    @Test
    void calculatePoint() {
        Card card = FaceCard.of(FaceShape.QUEEN, CardSuit.SPADE);

        Point result = card.calculatePoint();

        assertThat(result.get()).isEqualTo(10);
    }

    @DisplayName("얼굴 카드의 이름은 얼굴 모양과 무늬의 조합이다.")
    @Test
    void getFullName() {
        Card card = FaceCard.of(FaceShape.QUEEN, CardSuit.SPADE);

        String result = card.getFullName();

        assertThat(result).isEqualTo("Q스페이드");
    }
}
