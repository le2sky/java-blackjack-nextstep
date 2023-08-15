package blackjack.entity.card;

import blackjack.entity.common.Point;

interface Card {

    Point calculatePoint();

    String getFullName();
}
