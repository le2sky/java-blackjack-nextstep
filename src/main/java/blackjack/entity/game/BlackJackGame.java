package blackjack.entity.game;

import blackjack.entity.player.Player;
import java.util.List;
import java.util.Optional;

public class BlackJackGame {

    private static final int MIN_PLAYERS_SIZE = 1;
    private static final String INVALID_PLAYERS_MESSAGE = "유효한 플레이어 목록을 입력해주세요.";
    private static final String INVALID_PLAYER_MESSAGE = "유효한 플레이어를 포함한 목록을 입력해주세요.";
    private static final String INVALID_PLAYERS_SIZE =
            "플레이어는 적어도 " + MIN_PLAYERS_SIZE + " 명 이상이어야 합니다.";

    private final List<Player> player;

    private BlackJackGame(final List<Player> players) {
        this.player = players;
    }

    public static BlackJackGame from(final List<Player> players) {
        checkIsPlayersNonNull(players);
        players.forEach(BlackJackGame::checkIsPlayerNonNull);
        checkPlayersSize(players);

        return new BlackJackGame(players);
    }

    private static void checkIsPlayersNonNull(final List<Player> players) {
        Optional.ofNullable(players)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_PLAYERS_MESSAGE));
    }

    private static void checkIsPlayerNonNull(final Player player) {
        Optional.ofNullable(player)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_PLAYER_MESSAGE));
    }

    private static void checkPlayersSize(List<Player> players) {
        if (players.size() < MIN_PLAYERS_SIZE) {
            throw new IllegalArgumentException(INVALID_PLAYERS_SIZE);
        }
    }
}
