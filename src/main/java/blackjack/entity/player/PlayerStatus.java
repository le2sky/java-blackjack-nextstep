package blackjack.entity.player;

enum PlayerStatus {

    PLAY, STAND, BUST;

    public boolean isBust() {
        return this == BUST;
    }

    public boolean isStand() {
        return this == STAND;
    }

    public boolean isPlay() {
        return this == PLAY;
    }
}
