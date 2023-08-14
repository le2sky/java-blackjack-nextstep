package blackjack.card;

import java.util.Objects;
import java.util.Optional;

class FaceCard implements Card {

    private static final String INVALID_FACE_SHAPE_MESSAGE = "유효한 얼굴 모양을 입력해주세요.";
    private static final String INVALID_CARD_SUIT_MESSAGE = "유효한 카드의 무늬를 입력해주세요.";
    private static final int FACE_CARD_POINT_AMOUNT = 10;

    private final FaceShape shape;
    private final CardSuit suit;

    private FaceCard(final FaceShape shape, final CardSuit suit) {
        this.shape = shape;
        this.suit = suit;
    }

    public static Card of(final FaceShape shape, final CardSuit suit) {
        checkIsShapeNonNull(shape);
        checkIsSuitNonNull(suit);

        return new FaceCard(shape, suit);
    }

    private static void checkIsShapeNonNull(final FaceShape shape) {
        Optional.ofNullable(shape)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_FACE_SHAPE_MESSAGE));
    }

    private static void checkIsSuitNonNull(final CardSuit suit) {
        Optional.ofNullable(suit)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_CARD_SUIT_MESSAGE));
    }

    @Override
    public int calculatePoint() {
        return FACE_CARD_POINT_AMOUNT;
    }

    @Override
    public String getFullName() {
        return shape.getValue() + suit.getValue();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FaceCard faceCard = (FaceCard) o;
        return shape == faceCard.shape && suit == faceCard.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, suit);
    }
}
