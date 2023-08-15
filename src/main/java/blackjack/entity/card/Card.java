package blackjack.entity.card;

import blackjack.entity.common.Point;

public interface Card {

    Point calculatePoint();

    String getFullName();
}
