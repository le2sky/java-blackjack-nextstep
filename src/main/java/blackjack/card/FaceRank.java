package blackjack.card;

import java.util.Objects;
import java.util.Optional;

class FaceRank implements CardRank {

    private static final int FACE_CARD_POINT_AMOUNT = 10;
    private static final String INVALID_CARD_SHAPE_MESSAGE = "유효한 카드의 얼굴 모양을 입력해주세요.";

    private final FaceShape shape;

    private FaceRank(final FaceShape shape) {
        this.shape = shape;
    }

    public static FaceRank from(final FaceShape shape) {
        checkIsFaceShapeNonNull(shape);

        return new FaceRank(shape);
    }

    private static void checkIsFaceShapeNonNull(final FaceShape shape) {
        Optional.ofNullable(shape)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_CARD_SHAPE_MESSAGE));
    }

    @Override
    public Point calculatePoint() {
        return Point.from(FACE_CARD_POINT_AMOUNT);
    }

    @Override
    public String getValue() {
        return shape.getValue();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FaceRank faceRank = (FaceRank) o;
        return shape == faceRank.shape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape);
    }
}
