package blackjack.card;

import java.util.Objects;
import java.util.Optional;

class NumericCard implements Card {

    private static final int MIN_CARD_RANK_RANGE = 2;
    private static final int MAX_CARD_RANK_RANGE = 10;
    private static final String INVALID_CARD_RANK_RANGE_MESSAGE =
            "카드의 숫자는 " + MIN_CARD_RANK_RANGE + "부터 " + MAX_CARD_RANK_RANGE
                    + " 사이의 값만 허용됩니다.";
    private static final String INVALID_CARD_SUIT_MESSAGE = "유효한 카드의 무늬를 입력해주세요.";

    private final int rank;
    private final CardSuit suit;

    private NumericCard(final int rank, final CardSuit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public static Card of(final int rank, final CardSuit suit) {
        checkIsSuitNonNull(suit);
        checkIsNumericRankInRange(rank);

        return new NumericCard(rank, suit);
    }

    private static void checkIsSuitNonNull(final CardSuit suit) {
        Optional.ofNullable(suit)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_CARD_SUIT_MESSAGE));
    }

    private static void checkIsNumericRankInRange(final int rank) {
        if (rank < MIN_CARD_RANK_RANGE || rank > MAX_CARD_RANK_RANGE) {
            throw new IllegalArgumentException(INVALID_CARD_RANK_RANGE_MESSAGE);
        }
    }

    @Override
    public int calculatePoint() {
        return rank;
    }

    @Override
    public String getFullName() {
        return rank + suit.getValue();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NumericCard that = (NumericCard) o;
        return rank == that.rank && suit == that.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }
}
