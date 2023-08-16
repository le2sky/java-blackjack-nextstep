package blackjack.controller;

import blackjack.entity.player.Player;

class BlackJackGamePlayer {

    public String name;
    public double money;

    public BlackJackGamePlayer(final String name, final double money) {
        this.name = name;
        this.money = money;
    }

    public Player mapToPlayer() {
        return Player.of(name, money);
    }

    @Override
    public String toString() {
        return "BlackJackGamePlayer{" +
                "name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
