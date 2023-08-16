package blackjack.controller;

class CreatePlayerExceptionMessage {

    private final String message;
    private final BlackJackGamePlayer player;

    public CreatePlayerExceptionMessage(final String message, final BlackJackGamePlayer player) {
        this.message = message;
        this.player = player;
    }

    @Override
    public String toString() {
        return "CreatePlayerExceptionMessage{" +
                "message='" + message + '\'' +
                ", player=" + player +
                '}';
    }
}
