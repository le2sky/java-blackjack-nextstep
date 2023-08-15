package blackjack.entity.card.face;

import blackjack.entity.card.AbstractCard;
import blackjack.entity.card.Card;
import blackjack.entity.card.CardSuit;
import blackjack.entity.common.Point;

class FaceCard extends AbstractCard {

    private FaceCard(final FaceRank rank, final CardSuit suit) {
        super(rank, suit);
    }

    public static Card of(final FaceRank rank, final CardSuit suit) {
        return new FaceCard(rank, suit);
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
