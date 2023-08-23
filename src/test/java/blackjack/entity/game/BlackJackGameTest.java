package blackjack.entity.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.entity.card.Card;
import blackjack.entity.card.CardFactory;
import blackjack.entity.common.Point;
import blackjack.entity.player.Player;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {

    @DisplayName("게임 플레이어 목록은 알 수 없는 값(null)이 될 수 없다.")
    @Test
    void checkIsPlayersNonNull() {
        assertThatThrownBy(
                () -> BlackJackGame.of(null, new CardFactoryStub()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효한 플레이어 목록을 입력해주세요.");
    }

    @DisplayName("게임 플레이어 목록에 알 수 없는 플레이가 존재할 수 없다.")
    @Test
    void checkIsPlayerNonNull() {
        assertThatThrownBy(
                () -> BlackJackGame.of(Collections.singletonList(null), new CardFactoryStub()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효한 플레이어를 포함한 목록을 입력해주세요.");
    }

    @DisplayName("플레이어는 적어도 1명 이상이어야 한다.")
    @Test
    void checkPlayersSize() {
        assertThatThrownBy(() -> BlackJackGame.of(Collections.emptyList(),
                new CardFactoryStub()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어는 적어도 1 명 이상이어야 합니다.");
    }

    @DisplayName("카드 팩토리가 알 수 없는 값(null)인 경우는 허용되지 않는다.")
    @Test
    void checkIsFactoryNonNull() {
        assertThatThrownBy(
                () -> BlackJackGame.of(Collections.singletonList(
                        Player.of("lee", 1000)), null
                ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효한 카드 생성기를 입력해주세요.");
    }

    @DisplayName("게임의 사용자를 반환한다.")
    @Test
    void getAllPlayer() {
        BlackJackGame game = BlackJackGame.of(Arrays.asList(
                Player.of("pobi", 1000),
                Player.of("lee", 1000)
        ), new CardFactoryStub());

        List<Player> result = game.getAllPlayer();

        assertThat(result)
                .hasSize(2)
                .extracting("name")
                .containsExactlyInAnyOrder("pobi", "lee");
    }

    @DisplayName("모든 플레이어에게 카드를 한장씩 나눠준다.")
    @Test
    void dealAllPlayers() {
        CardFactoryStub cardFactory = new CardFactoryStub();
        cardFactory.givenCard(createCard(1, "1스페이스"));
        cardFactory.givenCard(createCard(4, "4스페이드"));
        cardFactory.givenCard(createCard(5, "5하트"));
        Player pobi = Player.of("pobi", 1000);
        Player lee = Player.of("lee", 1000);
        BlackJackGame game = BlackJackGame.of(Arrays.asList(pobi, lee), cardFactory);

        game.dealAllPlayer();

        assertThat(pobi.calculateTotalPoint()).isEqualTo(4);
        assertThat(lee.calculateTotalPoint()).isEqualTo(5);
    }

    private Card createCard(final int point, final String fullName) {
        return new Card() {

            @Override
            public Point calculatePoint() {
                return Point.from(point);
            }

            @Override
            public String getFullName() {
                return fullName;
            }
        };
    }

    static class CardFactoryStub implements CardFactory {

        private final Queue<Card> stubQueue = new LinkedList<>();

        public void givenCard(Card card) {
            stubQueue.offer(card);
        }

        @Override
        public Card createOne() {
            return stubQueue.poll();
        }
    }
}
