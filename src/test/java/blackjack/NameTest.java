package blackjack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        Name name = Name.from("name");
        Name other = Name.from("name");

        assertThat(name).isEqualTo(other);
    }

    @DisplayName("이름이 알 수 없는 값(null)인 경우는 허용되지 않는다.")
    @Test
    void checkNameNull() {
        assertThatThrownBy(() -> Name.from(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효한 이름을 입력해주세요.");
    }

    @DisplayName("이름의 길이는 적어도 공백을 제외한 한 글자 이상이다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "\n", "\t"})
    void checkNameLength(String source) {
        assertThatThrownBy(() -> Name.from(source))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름의 길이는 적어도 공백을 제외한 1 글자 이상입니다.");
    }
}
