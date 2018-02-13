package com.jms.connect4;

import java.io.IOException;

public final class Game {
    private final Terminal term;
    private Player player;
    private Board board;

    private Game(Terminal term) {
        this.term = term;
        this.player = Player.PLAYER1;
        this.board = Board.empty();
    }

    public static Game newGame(Terminal term) {
        return new Game(term);
    }

    public void play() throws IOException {
        boolean quit = false;
        while (! quit &&
                ! board.isWinningPlay() &&
                ! board.isBoardFull()) {
            this.term.println(board.toString());
            this.term.print(player.toString() + String.format(" - choose column (1-%d): ", Board.COLS));
            String line = this.term.readLine();
            if (line == null || line.toUpperCase().startsWith("Q")) {
                quit = true;
            }
            else {
                int column = 0;
                try {
                    column = Integer.parseInt(line);
                }
                catch (NumberFormatException e) {
                    // will be handled by check of column below
                }
                if (column < 1 || column > Board.COLS) {
                    this.term.println("Invalid choice");
                }
                else {
                    Board newBoard = board.add(this.player.getColor(), column - 1);
                    if (newBoard.isPieceAdded()) {
                        this.board = newBoard;
                        if (! this.board.isWinningPlay()) {
                            this.player = this.player.next();
                        }
                    }
                    else {
                        this.term.println("Invalid choice");
                    }
                }
            }
        }
        if (this.board.isWinningPlay()) {
            this.term.println(this.board.toString());
            this.term.println(this.player.toString() + " wins!");
        }
        else if (this.board.isBoardFull()) {
            this.term.println(this.board.toString());
            this.term.println("Game ends in draw");
        }
        else if (quit) {
            this.term.println("Quiting...");
        }
    }
}
