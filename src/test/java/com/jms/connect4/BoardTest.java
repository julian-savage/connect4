package com.jms.connect4;

import org.junit.Assert;
import org.junit.Test;

public class BoardTest {
    private static final Piece[] PIECES = new Piece[] { Piece.RED, Piece.GREEN };

    @Test
    public void testEmptyBoard() {
        Board board = Board.empty();
        String expectedBoard =
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n";
        Assert.assertEquals(expectedBoard, board.toString());
        Assert.assertFalse(board.isPieceAdded());
        Assert.assertFalse(board.isWinningPlay());
    }

    @Test
    public void testAddPiece() {
        Board board = Board.empty().add(Piece.RED, 3);
        String expectedBoard =
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | |R| | | |\n";
        Assert.assertEquals(expectedBoard, board.toString());
        Assert.assertTrue(board.isPieceAdded());
        Assert.assertFalse(board.isWinningPlay());
    }

    @Test
    public void testSampleMoves() {
        Integer[] moves = new Integer[] { 4, 4, 5, 5, 3, 2, 6 };

        Board board = Board.empty();
        for (int move = 0; move < moves.length; move++) {
            board = board.add(PIECES[move % 2], moves[move] - 1);
        }

        String expectedBoard =
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | |G|G| | |\n" +
                "| |G|R|R|R|R| |\n";
        Assert.assertEquals(expectedBoard, board.toString());
        Assert.assertTrue(board.isWinningPlay());
    }

    @Test
    public void testAlternatingColors() {
        Board board = Board.empty();
        for (int move = 0; move < 7; move++) {
            board = board.add(PIECES[move%2], move);
        }
        String expectedBoard =
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "|R|G|R|G|R|G|R|\n";
        Assert.assertEquals(expectedBoard, board.toString());
        Assert.assertFalse(board.isWinningPlay());
    }

    @Test
    public void testDiagonalWin() {
        int[] moves = new int[] { 1, 1, 1, 1, 2, 2, 3, 4, 5, 2, 4 };

        Board board = Board.empty();
        for (int move = 0; move < moves.length; move++) {
            board = board.add(PIECES[move % 2], moves[move] - 1);
        }
        String expectedBoard =
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "|G| | | | | | |\n" +
                "|R|G| | | | | |\n" +
                "|G|G| |R| | | |\n" +
                "|R|R|R|G|R| | |\n";
        Assert.assertEquals(expectedBoard, board.toString());
        Assert.assertFalse(board.isWinningPlay());
        Assert.assertTrue(board.add(Piece.GREEN, 2).isWinningPlay());
    }

    @Test
    public void testColumnFill() {
        Board board = Board.empty();
        for (int move = 0; move < 6; move++) {
            board = board.add(PIECES[move % 2], 0);
        }
        String expectedBoard =
                "|G| | | | | | |\n" +
                "|R| | | | | | |\n" +
                "|G| | | | | | |\n" +
                "|R| | | | | | |\n" +
                "|G| | | | | | |\n" +
                "|R| | | | | | |\n";
        Assert.assertEquals(expectedBoard, board.toString());
        Assert.assertTrue(board.isPieceAdded());
        Assert.assertFalse(board.add(Piece.RED, 0).isPieceAdded());
    }

    @Test
    public void testBoardFill() {
        Board board = Board.empty();
        for (int move = 0; move < Board.COLS * Board.ROWS ; move++) {
            board = board.add(PIECES[move % 2], move * 2 % Board.COLS);
            if (board.isWinningPlay()) {
                break;
            }
        }
        String expectedBoard =
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n";
        Assert.assertEquals(expectedBoard, board.toString());
        Assert.assertTrue(board.isBoardFull());
        Assert.assertFalse(board.isWinningPlay());
    }
}
