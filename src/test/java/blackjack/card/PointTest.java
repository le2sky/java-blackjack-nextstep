package blackjack.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PointTest {

    @DisplayName("포인트가 대체 값이 존재하는지 확인할 수 있다.")
    @Test
    void hasExchangeableValue() {
        Point point = Point.from(10);
        Point hadExchangeableValue = Point.of(1, 10);

        boolean result1 = point.hasExchangeableValue();
        boolean result2 = hadExchangeableValue.hasExchangeableValue();

        assertThat(result1).isFalse();
        assertThat(result2).isTrue();
    }

    @DisplayName("포인트의 대체 값을 받아올 수 있다.")
    @Test
    void exchange() {
        Point point = Point.of(1, 10);

        Optional<Integer> exchanged = point.exchange();

        assertThat(exchanged.isPresent()).isTrue();
        assertThat(exchanged.get()).isEqualTo(10);
    }

    @DisplayName("포인트의 기본 값을 받아올 수 있다.")
    @Test
    void get() {
        Point point = Point.of(1, 10);

        int result = point.get();

        assertThat(result).isEqualTo(1);
    }

    @DisplayName("포인트 끼리는 더할 수 있다.")
    @Test
    void plus() {
        Point point = Point.of(1, 10);
        Point other = Point.of(1, 10);

        Point result = point.plus(other);

        assertThat(result.get()).isEqualTo(2);
        assertThat(result.hasExchangeableValue()).isFalse();
    }
}
