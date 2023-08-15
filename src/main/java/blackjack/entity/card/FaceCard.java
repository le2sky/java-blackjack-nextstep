package blackjack.entity.card;

import blackjack.entity.common.Point;
import java.util.Optional;

class FaceCard extends AbstractCard {

    private static final int FACE_CARD_POINT_AMOUNT = 10;
    private static final String INVALID_CARD_SHAPE_MESSAGE = "유효한 카드의 얼굴 모양을 입력해주세요.";

    private final FaceShape shape;

    private FaceCard(final FaceShape shape, final CardSuit suit) {
        super(suit);
        this.shape = shape;
    }

    public static Card of(final FaceShape shape, final CardSuit suit) {
        checkIsFaceShapeNonNull(shape);

        return new FaceCard(shape, suit);
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
    public String getCardValue() {
        return shape.getValue();
    }
}
