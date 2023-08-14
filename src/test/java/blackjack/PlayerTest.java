package blackjack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("플레이어는 초기 베팅 금액과 자신의 이름을 알고 있다.")
    @Test
    void playerInformation() {
        Player player = Player.of("name", 100);
        Player other = Player.of("name", 100);

        assertThat(player).isEqualTo(other);
    }
    
    @DisplayName("플레이어의 초기 베팅 금액은 0보다 커야한다.")
    @Test
    void checkBetAmount() {
        assertThatThrownBy(() -> Player.of("name", 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("초기 베팅 금액은 적어도 0보다 커야 합니다.");
    }
}
