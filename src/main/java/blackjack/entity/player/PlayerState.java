package blackjack.entity.player;

import blackjack.entity.common.Money;
import java.util.Objects;
import java.util.Optional;

class PlayerState {

    private static final String INVALID_MONEY_MESSAGE =
            "알 수 없는 금액으로 플레이어의 상태를 초기화할 수 없습니다.";
    private static final String INVALID_STATUS_MESSAGE =
            "알 수 없는 상태로 플레이어의 상태를 초기화할 수 없습니다.";

    private Money money;
    private PlayerStatus status;

    private PlayerState(final Money money, PlayerStatus status) {
        this.money = money;
        this.status = status;
    }

    public static PlayerState from(final Money money) {
        checkIsMoneyNonNull(money);

        return new PlayerState(money, PlayerStatus.PLAY);
    }

    public static PlayerState of(final Money money, final PlayerStatus status) {
        checkIsMoneyNonNull(money);
        checkIsStatusNonNull(status);

        return new PlayerState(money, status);
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
        money = money.minus(money.multiply(2));
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PlayerState that = (PlayerState) o;
        return Objects.equals(money, that.money) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money, status);
    }
}
