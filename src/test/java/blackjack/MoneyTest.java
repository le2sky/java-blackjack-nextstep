package blackjack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        Money money = Money.from(1000);
        Money other = Money.from(1000);

        assertThat(money).isEqualTo(other);
    }

    @DisplayName("돈을 더한다.")
    @Test
    void plus() {
        Money money = Money.from(1000);
        Money other = Money.from(1000);

        Money newMoney = money.plus(other);

        assertThat(newMoney.getAmount()).isEqualTo(2000);
    }

    @DisplayName("더하기 연산에서 대상 금액이 알 수 없는 값(null)인 경우는 허용하지 않는다.")
    @Test
    void checkOtherNullForPlus() {
        Money money = Money.from(1000);

        assertThatThrownBy(() -> money.plus(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("알 수 없는 금액과 해당 연산을 수행할 수 없습니다.");
    }

    @DisplayName("돈을 뺀다.")
    @Test
    void minus() {
        Money money = Money.from(1000);
        Money other = Money.from(1000);

        Money newMoney = money.minus(other);

        assertThat(newMoney.getAmount()).isEqualTo(0);
    }

    @DisplayName("빼기 연산에서 대상 금액이 알 수 없는 값(null)인 경우는 허용하지 않는다.")
    @Test
    void checkOtherNullForMinus() {
        Money money = Money.from(1000);

        assertThatThrownBy(() -> money.minus(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("알 수 없는 금액과 해당 연산을 수행할 수 없습니다.");
    }

    @DisplayName("돈을 곱한다.")
    @Test
    void multiply() {
        Money money = Money.from(1000);

        Money newMoney = money.multiply(2);

        assertThat(newMoney.getAmount()).isEqualTo(2000);
    }
}
