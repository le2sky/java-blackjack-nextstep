package blackjack.entity.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.entity.common.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerStateTest {


    @DisplayName("생성 테스트")
    @Test
    void create() {
        PlayerState playerState = PlayerState.from(Money.from(0));
        PlayerState other = PlayerState.from(Money.from(0));

        assertThat(playerState).isEqualTo(other);
    }

    @DisplayName("알 수 없는 금액(null)으로 초기화할 수 없다.")
    @Test
    void checkIsMoneyNonNull() {
        assertThatThrownBy(() -> PlayerState.from(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("알 수 없는 금액으로 플레이어의 상태를 초기화할 수 없습니다.");
    }

    @DisplayName("알 수 없는 상태(null)로 초기화할 수 없다.")
    @Test
    void checkIsStatusNonNull() {
        assertThatThrownBy(() -> PlayerState.of(Money.from(0), null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("알 수 없는 상태로 플레이어의 상태를 초기화할 수 없습니다.");
    }

    @DisplayName("버스트되면 모든 베팅 금액을 잃는다.")
    @Test
    void bust() {
        PlayerState playerState = PlayerState.from(Money.from(10000));
        PlayerState expected = PlayerState.of(Money.from(-10000), PlayerStatus.BUST);

        playerState.bust();

        assertThat(playerState).isEqualTo(expected);
        assertThat(playerState.isBust()).isTrue();
    }

    @DisplayName("스탠드로 상태를 변경한다.")
    @Test
    void stand() {
        PlayerState playerState = PlayerState.from(Money.from(10000));
        PlayerState expected = PlayerState.of(Money.from(10000), PlayerStatus.STAND);

        playerState.stand();

        assertThat(playerState).isEqualTo(expected);
        assertThat(playerState.isStand()).isTrue();
    }
}
