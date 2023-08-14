package blackjack;

import java.util.Objects;
import java.util.Optional;

class Money {

    private static final String INVALID_OTHER_MONEY_MESSAGE =
            "알 수 없는 금액과 해당 연산을 수행할 수 없습니다.";

    private final double amount;

    private Money(final double amount) {
        this.amount = amount;
    }

    public static Money from(final double amount) {
        return new Money(amount);
    }

    public Money plus(final Money other) {
        checkIsOtherNonNull(other);

        return new Money(amount + other.getAmount());
    }

    public Money minus(final Money other) {
        checkIsOtherNonNull(other);

        return new Money(amount - other.getAmount());
    }

    private static void checkIsOtherNonNull(final Money other) {
        Optional.ofNullable(other)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_OTHER_MONEY_MESSAGE));
    }

    public double getAmount() {
        return amount;
    }

    public Money multiply(final double multiplier) {
        return new Money(amount * multiplier);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return Double.compare(money.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
