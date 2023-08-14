package blackjack.card;

class NumericCard extends AbstractCard {

    private NumericCard(final NumericRank rank, final CardSuit suit) {
        super(rank, suit);
    }

    public static Card of(final NumericRank rank, final CardSuit suit) {
        return new NumericCard(rank, suit);
    }

    @Override
    public int calculatePoint() {
        return rank.calculatePoint();
    }

    @Override
    public String getFullName() {
        return rank.getValue() + suit.getValue();
    }
}
