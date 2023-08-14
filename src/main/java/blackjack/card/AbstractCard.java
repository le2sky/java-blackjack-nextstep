package blackjack.card;


import java.util.Objects;
import java.util.Optional;

abstract class AbstractCard implements Card {

    private static final String INVALID_CARD_SUIT_MESSAGE = "유효한 카드의 무늬를 입력해주세요.";
    private static final String INVALID_RANK_MESSAGE = "유효한 랭크를 입력해주세요.";

    protected final CardRank rank;
    protected final CardSuit suit;

    protected AbstractCard(final CardRank rank, final CardSuit suit) {
        checkIsSuitNonNull(suit);
        checkIsRankNonNull(rank);

        this.rank = rank;
        this.suit = suit;
    }

    private void checkIsSuitNonNull(final CardSuit suit) {
        Optional.ofNullable(suit)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_CARD_SUIT_MESSAGE));
    }

    private void checkIsRankNonNull(final CardRank shape) {
        Optional.ofNullable(shape)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_RANK_MESSAGE));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractCard that = (AbstractCard) o;
        return Objects.equals(rank, that.rank) && suit == that.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }
}
