package blackjack.entity.player;

import blackjack.entity.card.Card;
import blackjack.entity.common.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Deck {

    private static final String INVALID_CARD_MESSAGE = "유효한 카드를 덱에 추가해주세요.";
    private static final String INVALID_CARDS_MESSAGE = "유효하지 않는 목록으로 덱을 초기화할 수 없습니다.";
    private static final int BLACK_JACK_AMOUNT = 21;

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

    public int calculateTotalPoint() {
        int total = calculatePointWithoutExchangeable();

        List<Point> exchangeablePoints = findExchangeablePoints();

        for (Point exchangeablePoint : exchangeablePoints) {
            total = nextTotal(total, exchangeablePoint);
        }

        return total;
    }

    private int calculatePointWithoutExchangeable() {
        return mapToPointStream()
                .filter(point -> !point.hasExchangeableValue())
                .mapToInt(Point::get)
                .reduce(Integer::sum)
                .orElse(0);
    }

    private List<Point> findExchangeablePoints() {
        return mapToPointStream()
                .filter(Point::hasExchangeableValue)
                .collect(Collectors.toList());
    }

    private Stream<Point> mapToPointStream() {
        return cards.stream()
                .map(Card::calculatePoint);
    }

    private int nextTotal(final int total, final Point exchangeablePoint) {
        int defaultValue = exchangeablePoint.get();
        int exchangeValue = exchangeablePoint
                .exchange()
                .orElse(defaultValue);

        return selectNextTotal(total + exchangeValue, total + defaultValue);
    }

    private int selectNextTotal(final int total, final int other) {
        if (!isBust(total)) {
            return total;
        }

        return other;
    }

    private boolean isBust(final int amount) {
        return amount > BLACK_JACK_AMOUNT;
    }

    @Override
    public boolean equals(final Object o) {
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
