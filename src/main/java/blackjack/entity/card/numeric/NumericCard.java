package blackjack.entity.card.numeric;

import blackjack.entity.card.AbstractCard;
import blackjack.entity.card.Card;
import blackjack.entity.card.CardSuit;
import blackjack.entity.common.Point;

class NumericCard extends AbstractCard {

    private NumericCard(final NumericRank rank, final CardSuit suit) {
        super(rank, suit);
    }

    public static Card of(final NumericRank rank, final CardSuit suit) {
        return new NumericCard(rank, suit);
    }

    @Override
    public Point calculatePoint() {
        return rank.calculatePoint();
    }

    @Override
    public String getFullName() {
        return rank.getValue() + suit.getValue();
    }
}
