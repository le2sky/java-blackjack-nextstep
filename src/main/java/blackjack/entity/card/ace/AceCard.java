package blackjack.entity.card.ace;

import blackjack.entity.card.AbstractCard;
import blackjack.entity.card.Card;
import blackjack.entity.card.CardSuit;
import blackjack.entity.common.Point;

class AceCard extends AbstractCard {

    private AceCard(final AceRank rank, final CardSuit suit) {
        super(rank, suit);
    }

    public static Card of(final AceRank rank, final CardSuit suit) {
        return new AceCard(rank, suit);
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
