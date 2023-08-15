package blackjack.entity.card;

import blackjack.entity.common.Point;

class NumericCard extends AbstractCard {

    private static final int MIN_CARD_RANK_RANGE = 2;
    private static final int MAX_CARD_RANK_RANGE = 10;
    private static final String INVALID_CARD_RANK_RANGE_MESSAGE =
            "카드의 숫자는 " + MIN_CARD_RANK_RANGE + "부터 " + MAX_CARD_RANK_RANGE
                    + " 사이의 값만 허용됩니다.";

    private final int value;

    private NumericCard(final int value, final CardSuit suit) {
        super(suit);
        this.value = value;
    }

    public static Card of(final int value, final CardSuit suit) {
        checkIsNumericRankInRange(value);

        return new NumericCard(value, suit);
    }

    private static void checkIsNumericRankInRange(final int value) {
        if (value < MIN_CARD_RANK_RANGE || value > MAX_CARD_RANK_RANGE) {
            throw new IllegalArgumentException(INVALID_CARD_RANK_RANGE_MESSAGE);
        }
    }

    @Override
    public Point calculatePoint() {
        return Point.from(value);
    }

    @Override
    public String getFullName() {
        return value + suit.getValue();
    }
}
