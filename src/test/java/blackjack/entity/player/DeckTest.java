package blackjack.entity.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.entity.card.Card;
import blackjack.entity.common.Point;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        Card card = generateCard(3, "3스페이드");
        Deck deck = Deck.from(Collections.singletonList(card));
        Deck other = Deck.from(Collections.singletonList(card));

        assertThat(deck).isEqualTo(other);
    }

    @DisplayName("덱을 초기화할 때 주어진 목록은 알 수 없는 값(null)이 될 수 없다.")
    @Test
    void fromNull() {
        assertThatThrownBy(() -> Deck.from(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않는 목록으로 덱을 초기화할 수 없습니다.");
    }

    @DisplayName("카드 대신 알 수 없는 카드(null)가 포함된 목록으로 덱을 초기화하는 경우는 허용되지 않는다.")
    @Test
    void from() {
        assertThatThrownBy(() -> Deck.from(Arrays.asList(generateCard(3, "3스페이드"), null)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효한 카드를 덱에 추가해주세요.");
    }

    @DisplayName("카드를 추가한다.")
    @Test
    void deal() {
        Card card = generateCard(3, "3스페이드");
        Deck deck = Deck.from(Collections.emptyList());
        Deck expectedDeck = Deck.from(Collections.singletonList(card));

        deck.deal(card);

        assertThat(deck).isEqualTo(expectedDeck);
    }

    @DisplayName("카드 대신 알 수 없는 카드(null)를 나눠주는 경우는 허용되지 않는다.")
    @Test
    void checkIsCardNonNull() {
        Deck deck = Deck.from(Collections.emptyList());

        assertThatThrownBy(() -> deck.deal(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효한 카드를 덱에 추가해주세요.");
    }


    @DisplayName("카드의 총합을 계산한다.")
    @Test
    void calculateTotalPoint() {
        Deck deck = Deck.from(Collections.emptyList());
        deck.deal(generateCard(3, "3스페이드"));
        deck.deal(generateCard(6, "6클로버"));
        deck.deal(generateCard(9, "9하트"));

        int result = deck.calculateTotalPoint();

        assertThat(result).isEqualTo(18);
    }

    @DisplayName("아무 카드도 존재하지 않는다면 0점이다.")
    @Test
    void calculateTotalPointWithAnyCard() {
        Deck deck = Deck.from(Collections.emptyList());

        int result = deck.calculateTotalPoint();

        assertThat(result).isEqualTo(0);
    }

    @DisplayName("에이스 카드가 포함되어 있는 경우 계산 테스")
    @Test
    void calculateTotalPointWithAce() {
        Deck deck = Deck.from(Collections.emptyList());
        deck.deal(generateAceCard());
        deck.deal(generateCard(3, "3스페이드"));
        deck.deal(generateCard(6, "6클로버"));
        deck.deal(generateCard(9, "9하트"));

        int result = deck.calculateTotalPoint();

        assertThat(result).isEqualTo(19);
    }


    @DisplayName("에이스 대체 점수 사용 테스트")
    @Test
    void calculateTotalPointComplex() {
        Deck deck = Deck.from(Collections.emptyList());
        deck.deal(generateAceCard());
        deck.deal(generateAceCard());
        deck.deal(generateAceCard());

        int result = deck.calculateTotalPoint();

        assertThat(result).isEqualTo(21);
    }

    private Card generateAceCard() {
        return new Card() {

            @Override
            public Point calculatePoint() {
                return Point.of(1, 10);
            }

            @Override
            public String getFullName() {
                return "A스페이드";
            }
        };
    }


    private Card generateCard(int point, String fullName) {
        return new Card() {
            @Override
            public Point calculatePoint() {
                return Point.from(point);
            }

            @Override
            public String getFullName() {
                return Optional.ofNullable(fullName)
                        .orElse("3스페이드");
            }
        };
    }
}
