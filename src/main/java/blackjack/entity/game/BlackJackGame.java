package blackjack.entity.game;

import blackjack.entity.card.CardFactory;
import blackjack.entity.player.Player;
import java.util.List;
import java.util.Optional;

public class BlackJackGame {

    private static final int MIN_PLAYERS_SIZE = 1;
    private static final String INVALID_PLAYERS_MESSAGE = "유효한 플레이어 목록을 입력해주세요.";
    private static final String INVALID_PLAYER_MESSAGE = "유효한 플레이어를 포함한 목록을 입력해주세요.";
    private static final String INVALID_PLAYERS_SIZE =
            "플레이어는 적어도 " + MIN_PLAYERS_SIZE + " 명 이상이어야 합니다.";
    private static final String INVALID_CARD_FACTORY_MESSAGE = "유효한 카드 생성기를 입력해주세요.";

    private final List<Player> players;
    private final CardFactory cardFactory;

    private BlackJackGame(final List<Player> players, CardFactory cardFactory) {
        this.players = players;
        this.cardFactory = cardFactory;
    }

    public static BlackJackGame of(final List<Player> players, final CardFactory cardFactory) {
        checkIsCardFactoryNonNull(cardFactory);
        checkIsPlayersNonNull(players);
        players.forEach(BlackJackGame::checkIsPlayerNonNull);
        checkPlayersSize(players);

        return new BlackJackGame(players, cardFactory);
    }

    private static void checkIsCardFactoryNonNull(final CardFactory cardFactory) {
        Optional.ofNullable(cardFactory)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_CARD_FACTORY_MESSAGE));
    }

    private static void checkIsPlayersNonNull(final List<Player> players) {
        Optional.ofNullable(players)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_PLAYERS_MESSAGE));
    }

    private static void checkIsPlayerNonNull(final Player player) {
        Optional.ofNullable(player)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_PLAYER_MESSAGE));
    }

    private static void checkPlayersSize(final List<Player> players) {
        if (players.size() < MIN_PLAYERS_SIZE) {
            throw new IllegalArgumentException(INVALID_PLAYERS_SIZE);
        }
    }

    public List<Player> getAllPlayer() {
        return players;
    }

    public void dealAllPlayer() {
        players.forEach(player -> player.deal(cardFactory.createOne()));
    }
}
