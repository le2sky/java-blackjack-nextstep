package blackjack.entity.card.ace;

import blackjack.entity.card.CardRank;
import blackjack.entity.common.Point;

class AceRank implements CardRank {

    private static final int DEFAULT_ACE_CARD_POINT_AMOUNT = 1;
    private static final int ALTER_ACE_CARD_POINT_AMOUNT = 10;
    public static final String ACE_CARD_VALUE = "A";

    @Override
    public Point calculatePoint() {
        return Point.of(DEFAULT_ACE_CARD_POINT_AMOUNT, ALTER_ACE_CARD_POINT_AMOUNT);
    }

    @Override
    public String getValue() {
        return ACE_CARD_VALUE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        return o != null && getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
