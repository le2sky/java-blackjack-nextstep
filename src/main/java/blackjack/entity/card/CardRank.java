package blackjack.entity.card;

import blackjack.entity.common.Point;

public interface CardRank {

    Point calculatePoint();

    String getValue();
}
