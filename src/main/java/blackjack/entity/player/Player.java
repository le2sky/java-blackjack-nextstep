package blackjack.entity.player;

import blackjack.entity.card.Card;
import blackjack.entity.common.Money;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

public class Player {

    private static final int MIN_BET_MONEY_AMOUNT = 0;
    private static final String INVALID_MONEY_AMOUNT_MESSAGE =
            "초기 베팅 금액은 적어도 " + MIN_BET_MONEY_AMOUNT + " 이상이어야 합니다.";
    private static final String INVALID_DEAL_STATE_MESSAGE =
            "스탠드나 버스트 상태에서는 신규 카드를 받을 수 없습니다.";
    private static final String SHOW_CARD_JOIN_DELIMITER = ", ";

    private final Name name;
    private final Deck deck;
    private final PlayerState state;

    private Player(final String name, final double money) {
        this.state = PlayerState.from(Money.from(money));
        this.name = Name.from(name);
        this.deck = Deck.from(Collections.emptyList());
    }

    public static Player of(final String name, final double money) {
        checkMoneyAmount(money);

        return new Player(name, money);
    }

    private static void checkMoneyAmount(final double money) {
        if (money < MIN_BET_MONEY_AMOUNT) {
            throw new IllegalArgumentException(INVALID_MONEY_AMOUNT_MESSAGE);
        }
    }

    public void stand() {
        state.stand();
    }

    public void deal(final Card card) {
        checkIsNotPlayable();

        deck.deal(card);

        if (deck.isBust()) {
            state.bust();
        }
    }

    private void checkIsNotPlayable() {
        if (!isPlayable()) {
            throw new IllegalStateException(INVALID_DEAL_STATE_MESSAGE);
        }
    }

    public boolean isPlayable() {
        return state.isPlay();
    }

    public int calculateTotalPoint() {
        return deck.calculateTotalPoint();
    }

    public String showCard() {
        return deck.getCard().stream()
                .map(Card::getFullName)
                .collect(Collectors.joining(SHOW_CARD_JOIN_DELIMITER));
    }

    public String getName() {
        return name.getValue();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(name, player.name) && Objects.equals(state,
                player.state) && Objects.equals(deck, player.deck);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, state, deck);
    }
}
