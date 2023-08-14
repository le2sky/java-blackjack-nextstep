package blackjack;

import java.util.Objects;
import java.util.Optional;

class Name {

    private static final int MIN_NAME_LENGTH = 1;
    private static final String INVALID_NAME_MESSAGE = "유효한 이름을 입력해주세요.";
    private static final String INVALID_NAME_LENGTH_MESSAGE =
            "이름의 길이는 적어도 공백을 제외한 " + MIN_NAME_LENGTH + " 글자 이상입니다.";

    private final String name;

    private Name(final String name) {
        this.name = name;
    }

    public static Name from(final String name) {
        checkIsNameNonNull(name);
        checkNameLength(name);

        return new Name(name);
    }

    private static void checkIsNameNonNull(final String name) {
        Optional.ofNullable(name)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_NAME_MESSAGE));
    }

    private static void checkNameLength(final String name) {
        if (name.trim().length() < MIN_NAME_LENGTH) {
            throw new IllegalArgumentException(INVALID_NAME_LENGTH_MESSAGE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
