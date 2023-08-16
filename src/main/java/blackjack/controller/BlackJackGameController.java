package blackjack.controller;

import blackjack.entity.game.BlackJackGame;
import blackjack.view.InputView;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGameController {

    public void run() {
        List<BlackJackGamePlayer> blackJackGamePlayers = createPlayers(InputView.readPlayerNames());

        setPlayerMoney(blackJackGamePlayers);

        play(BlackJackGame.from(PlayerMapper.mapToPlayers(blackJackGamePlayers)));
    }

    private List<BlackJackGamePlayer> createPlayers(final String[] names) {
        return Arrays.stream(names)
                .map((name) -> new BlackJackGamePlayer(name, 0))
                .collect(Collectors.toList());
    }

    private void setPlayerMoney(final List<BlackJackGamePlayer> blackJackGamePlayers) {
        blackJackGamePlayers.forEach((player) -> {
            player.money = InputView.readMoney(player.name);
        });
    }

    private void play(final BlackJackGame blackJackGame) {
        // TODO: 게임 로직
    }
}
