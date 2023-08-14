package blackjack.card;

class AceRank implements CardRank {

    @Override
    public Point calculatePoint() {
        return Point.from(0);
    }

    @Override
    public String getValue() {
        return null;
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
