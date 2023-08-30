package blackjack.entity.player;

import blackjack.entity.common.Money;
import java.util.Objects;
import java.util.Optional;

class PlayerState {

    private static final String INVALID_MONEY_MESSAGE = "알 수 없는 금액입니다.";
    private static final String INVALID_STATUS_MESSAGE =
            "알 수 없는 상태로 플레이어의 상태를 초기화할 수 없습니다.";

    private Money money;
    private Money revenue;
    private PlayerStatus status;

    private PlayerState(final Money money, final PlayerStatus status) {
        this.money = money;
        this.status = status;
        this.revenue = Money.from(0);
    }

    private PlayerState(final Money money, final Money revenue, final PlayerStatus status) {
        this.money = money;
        this.status = status;
        this.revenue = revenue;
    }

    public static PlayerState from(final Money money) {
        checkIsMoneyNonNull(money);

        return new PlayerState(money, PlayerStatus.PLAY);
    }

    public static PlayerState of(final Money money, final Money revenue,
            final PlayerStatus status) {
        checkIsMoneyNonNull(money);
        checkIsMoneyNonNull(revenue);
        checkIsStatusNonNull(status);

        return new PlayerState(money, revenue, status);
    }

    private static void checkIsMoneyNonNull(final Money money) {
        Optional.ofNullable(money)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_MONEY_MESSAGE));
    }

    private static void checkIsStatusNonNull(final PlayerStatus status) {
        Optional.ofNullable(status)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_STATUS_MESSAGE));
    }

    public void bust() {
        revenue = revenue.minus(money);
        money = Money.from(0);
        status = PlayerStatus.BUST;
    }

    public void stand() {
        status = PlayerStatus.STAND;
    }

    public boolean isBust() {
        return status.isBust();
    }

    public boolean isStand() {
        return status.isStand();
    }

    public boolean isPlay() {
        return status.isPlay();
    }

    public void earnMoney(final Money earned) {
        checkIsMoneyNonNull(earned);

        revenue = revenue.plus(earned);
        money = money.plus(earned);
    }

    public void lossMoney(final Money loss) {
        checkIsMoneyNonNull(loss);

        revenue = revenue.minus(loss);
        money = money.minus(loss);
    }

    public double getMoney() {
        return money.getAmount();
    }

    public Money getRevenue() {
        return revenue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PlayerState that = (PlayerState) o;
        return Objects.equals(money, that.money) && Objects.equals(revenue,
                that.revenue) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money, revenue, status);
    }
}
