package com.jms.connect4;

public enum Piece {
    NONE(" "), RED("R"), GREEN("G");
    private final String abbrev;

    Piece(String abbrev) {
        this.abbrev = abbrev;
    }

    public String getAbbrev() {
        return this.abbrev;
    }
}
