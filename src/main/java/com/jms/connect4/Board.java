package com.jms.connect4;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class Board {
    public static final int ROWS = 6;
    public static final int COLS = 7;
    private final Piece[][] board;
    private final int playedRowIdx;
    private final int playedColIdx;

    private Board(Piece[][] board, int playedRowIdx, int playedColIdx) {
        this.board = board;
        this.playedRowIdx = playedRowIdx;
        this.playedColIdx = playedColIdx;
    }

    public static Board empty() {
        Piece[][] board  = new Piece[ROWS][COLS];
        for (int rowIdx = 0; rowIdx < ROWS; rowIdx++) {
            Arrays.fill(board[rowIdx], Piece.NONE);
        }
        return new Board(board, -1, -1);
    }

    public Board add(Piece piece, int colIdx) {
        Piece[][] newboard = new Piece[ROWS][];
        int colAdded = -1;
        int rowAdded = -1;
        for (int rowIdx = ROWS - 1; rowIdx > -1; rowIdx--) {
            Piece[] row = this.board[rowIdx];
            if (colAdded == -1 && row[colIdx].equals(Piece.NONE)) {
                row = Arrays.copyOf(row, COLS);
                row[colIdx] = piece;
                rowAdded = rowIdx;
                colAdded = colIdx;
            }
            newboard[rowIdx] = row;
        }
        return new Board(newboard, rowAdded, colAdded);
    }

    public boolean isPieceAdded() {
        return this.playedColIdx > -1 && this.playedRowIdx > -1;
    }

    public boolean isWinningPlay() {
        if (this.isPieceAdded()) {
            int northsouth = this.count(0, -1) + this.count(0, 1) + 1;
            int westeast = this.count(-1, 0) + this.count(1, 0) + 1;
            int northwestsoutheast = this.count(-1, -1) + this.count(1, 1) + 1;
            int northeastsouthwest = this.count(-1, 1) + this.count(1, -1) + 1;
            return northsouth >= 4 || westeast >= 4 || northwestsoutheast >= 4 || northeastsouthwest >= 4;
        }
        else {
            return false;
        }
    }

    public boolean isBoardFull() {
        return Arrays.stream(this.board[0]).noneMatch(Piece.NONE::equals);
    }

    private int count(int rowDelta, int colDelta) {
        int rowIdx = this.playedRowIdx;
        int colIdx = this.playedColIdx;
        int count = 0;
        Piece playedPiece = this.board[this.playedRowIdx][this.playedColIdx];
        while (true) {
            rowIdx += rowDelta;
            colIdx += colDelta;
            if (rowIdx < 0 || rowIdx >= ROWS || colIdx < 0 || colIdx >= COLS) {
                break;
            }
            else if (! this.board[rowIdx][colIdx].equals(playedPiece)) {
                break;
            }
            else {
                count += 1;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int row = 0; row < ROWS; row++) {
            builder.append("|")
                    .append(Arrays.stream(this.board[row])
                            .map(Piece::getAbbrev).collect(Collectors.joining("|")))
                .append("|\n");
        }
        return builder.toString();
    }
}
