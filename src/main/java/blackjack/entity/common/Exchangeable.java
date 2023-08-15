package blackjack.entity.common;

import java.util.Optional;

interface Exchangeable<T> {

    boolean hasExchangeableValue();

    Optional<T> exchange();
}
