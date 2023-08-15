package blackjack.entity.card;


import java.util.Objects;
import java.util.Optional;

abstract class AbstractCard implements Card {

    private static final String INVALID_CARD_SUIT_MESSAGE = "유효한 카드의 무늬를 입력해주세요.";

    private final CardSuit suit;

    protected AbstractCard(final CardSuit suit) {
        checkIsSuitNonNull(suit);

        this.suit = suit;
    }

    private void checkIsSuitNonNull(final CardSuit suit) {
        Optional.ofNullable(suit)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_CARD_SUIT_MESSAGE));
    }

    @Override
    public String getFullName() {
        return getCardValue() + suit.getValue();
    }

    abstract public String getCardValue();

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractCard that = (AbstractCard) o;
        return suit == that.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit);
    }
}
