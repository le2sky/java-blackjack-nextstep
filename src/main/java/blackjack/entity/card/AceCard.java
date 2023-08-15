package blackjack.entity.card;

import blackjack.entity.common.Point;

class AceCard extends AbstractCard {

    private static final int DEFAULT_ACE_CARD_POINT_AMOUNT = 1;
    private static final int ALTER_ACE_CARD_POINT_AMOUNT = 10;
    private static final String ACE_CARD_VALUE = "A";

    private AceCard(final CardSuit suit) {
        super(suit);
    }

    public static Card from(final CardSuit suit) {
        return new AceCard(suit);
    }

    @Override
    public Point calculatePoint() {
        return Point.of(DEFAULT_ACE_CARD_POINT_AMOUNT, ALTER_ACE_CARD_POINT_AMOUNT);
    }

    @Override
    public String getCardValue() {
        return ACE_CARD_VALUE;
    }
}
