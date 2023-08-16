package blackjack;

import blackjack.controller.BlackJackGameController;

public class Main {

    public static void main(String[] args) {
        BlackJackGameController controller = new BlackJackGameController();
        controller.run();
    }
}
