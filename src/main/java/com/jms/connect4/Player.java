package com.jms.connect4;

public enum Player {
    PLAYER1(Piece.RED) {
        @Override
        public String toString() {
            return "Player 1 [" + this.getColor().name() + "]";
        }

        @Override
        public Player next() {
            return PLAYER2;
        }
    },
    PLAYER2(Piece.GREEN) {
        @Override
        public String toString() {
            return "Player 2 [" + this.getColor().name() + "]";
        }

        @Override
        public Player next() {
            return PLAYER1;
        }
    };
    private final Piece color;

    Player(Piece color) {
        this.color = color;
    }

    public abstract Player next();

    public Piece getColor() {
        return color;
    }
}
