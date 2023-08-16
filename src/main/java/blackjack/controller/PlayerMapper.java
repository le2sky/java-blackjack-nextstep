package blackjack.controller;

import blackjack.entity.player.Player;
import java.util.List;
import java.util.stream.Collectors;

class PlayerMapper {

    public static List<Player> mapToPlayers(final List<BlackJackGamePlayer> blackJackGamePlayers) {
        return blackJackGamePlayers.stream()
                .map(PlayerMapper::mapToPlayer)
                .collect(Collectors.toList());
    }

    private static Player mapToPlayer(final BlackJackGamePlayer blackJackGamePlayer) {
        try {
            return blackJackGamePlayer.mapToPlayer();
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    new CreatePlayerExceptionMessage(e.getMessage(), blackJackGamePlayer).toString()
            );
        }
    }
}
