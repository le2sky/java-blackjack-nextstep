package blackjack.card;

import java.util.Optional;

interface Exchangeable<T> {

    boolean hasExchangeableValue();

    Optional<T> exchange();
}
