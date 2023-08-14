package blackjack.card;

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
