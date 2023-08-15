package blackjack.entity.player;

import blackjack.entity.card.Card;
import blackjack.entity.common.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

class Deck {

    private static final String INVALID_CARD_MESSAGE = "유효한 카드를 덱에 추가해주세요.";
    private static final String INVALID_CARDS_MESSAGE = "유효하지 않는 목록으로 덱을 초기화할 수 없습니다.";

    private final List<Card> cards;

    private Deck(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public static Deck from(final List<Card> cards) {
        checkIsCardsNonNull(cards);
        cards.forEach(Deck::checkIsCardNonNull);

        return new Deck(cards);
    }

    private static void checkIsCardsNonNull(final List<Card> cards) {
        Optional.ofNullable(cards)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_CARDS_MESSAGE));
    }

    private static void checkIsCardNonNull(final Card card) {
        Optional.ofNullable(card)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_CARD_MESSAGE));
    }

    public void deal(final Card card) {
        checkIsCardNonNull(card);

        cards.add(card);
    }

    public Point calculateTotalPoint() {
        return filteringToExchangeableValue()
                .findFirst()
                .map(this::calculatePointWithExchangeableCard)
                .orElseGet(() -> Point.from(calculateDefaultPoint()));
    }

    private Stream<Point> filteringToExchangeableValue() {
        return cards.stream()
                .map(Card::calculatePoint)
                .filter(Point::hasExchangeableValue);
    }

    private Point calculatePointWithExchangeableCard(final Point exchangeablePoint) {
        int defaultPoint = calculateDefaultPoint();
        int exchangeableCardCount = findExchangeableCardCount();
        int totalExchangeableCardDefault = exchangeablePoint.get() * exchangeableCardCount;
        int exchangeValue = (exchangeablePoint.exchange().orElse(0)) * exchangeableCardCount;

        return Point.of(
                defaultPoint, defaultPoint - totalExchangeableCardDefault + exchangeValue
        );
    }

    private int calculateDefaultPoint() {
        return cards.stream()
                .map(Card::calculatePoint)
                .map(Point::get)
                .reduce(Integer::sum)
                .orElse(0);
    }

    private int findExchangeableCardCount() {
        return (int) filteringToExchangeableValue().count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Deck deck = (Deck) o;
        return Objects.equals(cards, deck.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }
}
