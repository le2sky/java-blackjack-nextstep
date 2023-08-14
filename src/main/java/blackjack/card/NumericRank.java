package blackjack.card;

import java.util.Objects;

class NumericRank implements CardRank {

    private static final int MIN_CARD_RANK_RANGE = 2;
    private static final int MAX_CARD_RANK_RANGE = 10;
    private static final String INVALID_CARD_RANK_RANGE_MESSAGE =
            "카드의 숫자는 " + MIN_CARD_RANK_RANGE + "부터 " + MAX_CARD_RANK_RANGE
                    + " 사이의 값만 허용됩니다.";

    private final int value;

    private NumericRank(final int value) {
        this.value = value;
    }

    public static NumericRank from(final int value) {
        checkIsNumericRankInRange(value);

        return new NumericRank(value);
    }

    private static void checkIsNumericRankInRange(final int value) {
        if (value < MIN_CARD_RANK_RANGE || value > MAX_CARD_RANK_RANGE) {
            throw new IllegalArgumentException(INVALID_CARD_RANK_RANGE_MESSAGE);
        }
    }

    @Override
    public int calculatePoint() {
        return value;
    }

    @Override
    public String getValue() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NumericRank that = (NumericRank) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
