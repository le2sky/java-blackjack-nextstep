package blackjack.view;

import blackjack.entity.player.Player;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String WRITE_INITIAL_DEAL_TEMPLATE =
            "%s와 %s에게 2장의 카드를 나누었습니다." + System.lineSeparator();
    private static final String WRITE_PLAYER_CARD_TEMPLATE =
            "%s의 카드 : %s" + System.lineSeparator();
    private static final String NAME_JOINING_DELIMITER = ", ";

    public static void writeInitialDealResult(final Player dealer, final List<Player> allPlayer) {
        System.out.format(WRITE_INITIAL_DEAL_TEMPLATE, dealer.getName(), joiningName(allPlayer));

        writePlayerCard(dealer);

        allPlayer.forEach(OutputView::writePlayerCard);
    }

    private static void writePlayerCard(final Player player) {
        System.out.format(WRITE_PLAYER_CARD_TEMPLATE, player.getName(), player.showCard());
    }

    private static String joiningName(final List<Player> allPlayer) {
        return allPlayer.stream()
                .map(Player::getName)
                .collect(Collectors.joining(NAME_JOINING_DELIMITER));
    }
}
