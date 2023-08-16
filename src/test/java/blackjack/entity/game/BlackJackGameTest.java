package blackjack.entity.game;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {

    @DisplayName("게임 플레이어 목록은 알 수 없는 값(null)이 될 수 없다.")
    @Test
    void checkIsPlayersNonNull() {
        assertThatThrownBy(() -> BlackJackGame.from(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효한 플레이어 목록을 입력해주세요.");
    }

    @DisplayName("게임 플레이어 목록에 알 수 없는 플레이가 존재할 수 없다.")
    @Test
    void checkIsPlayerNonNull() {
        assertThatThrownBy(() -> BlackJackGame.from(Collections.singletonList(null)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효한 플레이어를 포함한 목록을 입력해주세요.");
    }

    @DisplayName("플레이어는 적어도 1명 이상이어야 한다.")
    @Test
    void checkPlayersSize() {
        assertThatThrownBy(() -> BlackJackGame.from(Collections.emptyList()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어는 적어도 1 명 이상이어야 합니다.");
    }
}
