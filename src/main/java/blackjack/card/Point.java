package blackjack.card;

import java.util.Optional;

class Point implements Exchangeable<Integer> {

    private final int value;
    private final Integer exchangeableValue;

    private Point(final int value) {
        this.value = value;
        this.exchangeableValue = null;
    }

    private Point(final int value, final int exchangeableValue) {
        this.value = value;
        this.exchangeableValue = exchangeableValue;
    }

    public static Point from(final int value) {
        return new Point(value);
    }

    public static Point of(final int value, final int exchangeableValue) {
        return new Point(value, exchangeableValue);
    }

    @Override
    public boolean hasExchangeableValue() {
        return Optional.ofNullable(exchangeableValue)
                .isPresent();
    }

    @Override
    public Optional<Integer> exchange() {
        return Optional.ofNullable(exchangeableValue);
    }

    public int get() {
        return value;
    }

    public Point plus(final Point other) {
        return Point.from(get() + other.get());
    }
}
