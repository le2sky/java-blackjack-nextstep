package blackjack.entity.player;

import blackjack.entity.common.Money;
import java.util.Collections;
import java.util.Objects;

class Player {

    private static final int MIN_BET_MONEY_AMOUNT = 0;
    private static final String INVALID_MONEY_AMOUNT_MESSAGE =
            "초기 베팅 금액은 적어도 " + MIN_BET_MONEY_AMOUNT + "보다 커야 합니다.";

    private final Name name;
    private final Money money;
    private final Deck deck;

    private Player(final String name, final double money) {
        this.money = Money.from(money);
        this.name = Name.from(name);
        this.deck = Deck.from(Collections.emptyList());
    }

    public static Player of(final String name, final double money) {
        checkMoneyAmount(money);

        return new Player(name, money);
    }

    private static void checkMoneyAmount(final double money) {
        if (money <= MIN_BET_MONEY_AMOUNT) {
            throw new IllegalArgumentException(INVALID_MONEY_AMOUNT_MESSAGE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(name, player.name) && Objects.equals(money,
                player.money) && Objects.equals(deck, player.deck);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, money, deck);
    }
}
