package com.jms.connect4;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameTest {
    private static final class Term implements Terminal {
        private final Deque<String> lines;
        private final StringBuilder builder = new StringBuilder();

        private Term(List<String> lines) {
            this.lines = new ArrayDeque<>(lines);
        }

        @Override
        public void println(String x) {
            builder.append(x).append("\n");
        }

        @Override
        public void print(String x) {
            builder.append(x);
        }

        @Override
        public String readLine() throws IOException {
            String result = lines.poll();
            if (result != null) {
                this.println(result);
            }
            return result;
        }

        @Override
        public String toString() {
            return this.builder.toString();
        }
    }

    @Test
    public void testSampleGame() throws IOException {
        Term term = new Term(Arrays.asList("4", "4", "5", "5", "3", "2", "6"));
        Game game = Game.newGame(term);
        game.play();
        String expectedResult =
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "\n" +
                "Player 1 [RED] - choose column (1-7): 4\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | |R| | | |\n" +
                "\n" +
                "Player 2 [GREEN] - choose column (1-7): 4\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | |G| | | |\n" +
                "| | | |R| | | |\n" +
                "\n" +
                "Player 1 [RED] - choose column (1-7): 5\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | |G| | | |\n" +
                "| | | |R|R| | |\n" +
                "\n" +
                "Player 2 [GREEN] - choose column (1-7): 5\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | |G|G| | |\n" +
                "| | | |R|R| | |\n" +
                "\n" +
                "Player 1 [RED] - choose column (1-7): 3\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | |G|G| | |\n" +
                "| | |R|R|R| | |\n" +
                "\n" +
                "Player 2 [GREEN] - choose column (1-7): 2\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | |G|G| | |\n" +
                "| |G|R|R|R| | |\n" +
                "\n" +
                "Player 1 [RED] - choose column (1-7): 6\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | |G|G| | |\n" +
                "| |G|R|R|R|R| |\n" +
                "\n" +
                "Player 1 [RED] wins!\n";
        Assert.assertEquals(expectedResult, term.toString());
    }

    @Test
    public void testFillBoard() throws IOException {
        Term term = new Term(Stream.iterate(0, t->t+1)
                .limit(Board.COLS*Board.ROWS)
                .map(t->(t*2 % Board.COLS + 1))
                .map(Object::toString)
                .collect(Collectors.toList()));
        Game game = Game.newGame(term);
        game.play();
        String expectedResult =
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "\n" +
                "Player 1 [RED] - choose column (1-7): 1\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "|R| | | | | | |\n" +
                "\n" +
                "Player 2 [GREEN] - choose column (1-7): 3\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "|R| |G| | | | |\n" +
                "\n" +
                "Player 1 [RED] - choose column (1-7): 5\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "|R| |G| |R| | |\n" +
                "\n" +
                "Player 2 [GREEN] - choose column (1-7): 7\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "|R| |G| |R| |G|\n" +
                "\n" +
                "Player 1 [RED] - choose column (1-7): 2\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "|R|R|G| |R| |G|\n" +
                "\n" +
                "Player 2 [GREEN] - choose column (1-7): 4\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "|R|R|G|G|R| |G|\n" +
                "\n" +
                "Player 1 [RED] - choose column (1-7): 6\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Player 2 [GREEN] - choose column (1-7): 1\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "|G| | | | | | |\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Player 1 [RED] - choose column (1-7): 3\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "|G| |R| | | | |\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Player 2 [GREEN] - choose column (1-7): 5\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "|G| |R| |G| | |\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Player 1 [RED] - choose column (1-7): 7\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "|G| |R| |G| |R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Player 2 [GREEN] - choose column (1-7): 2\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "|G|G|R| |G| |R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Player 1 [RED] - choose column (1-7): 4\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "|G|G|R|R|G| |R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Player 2 [GREEN] - choose column (1-7): 6\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Player 1 [RED] - choose column (1-7): 1\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "|R| | | | | | |\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Player 2 [GREEN] - choose column (1-7): 3\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "|R| |G| | | | |\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Player 1 [RED] - choose column (1-7): 5\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "|R| |G| |R| | |\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Player 2 [GREEN] - choose column (1-7): 7\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "|R| |G| |R| |G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Player 1 [RED] - choose column (1-7): 2\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "|R|R|G| |R| |G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Player 2 [GREEN] - choose column (1-7): 4\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "|R|R|G|G|R| |G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Player 1 [RED] - choose column (1-7): 6\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "|R|R|G|G|R|R|G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Player 2 [GREEN] - choose column (1-7): 1\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "|G| | | | | | |\n" +
                "|R|R|G|G|R|R|G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Player 1 [RED] - choose column (1-7): 3\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "|G| |R| | | | |\n" +
                "|R|R|G|G|R|R|G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Player 2 [GREEN] - choose column (1-7): 5\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "|G| |R| |G| | |\n" +
                "|R|R|G|G|R|R|G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Player 1 [RED] - choose column (1-7): 7\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "|G| |R| |G| |R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Player 2 [GREEN] - choose column (1-7): 2\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "|G|G|R| |G| |R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Player 1 [RED] - choose column (1-7): 4\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "|G|G|R|R|G| |R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Player 2 [GREEN] - choose column (1-7): 6\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Player 1 [RED] - choose column (1-7): 1\n" +
                "| | | | | | | |\n" +
                "|R| | | | | | |\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Player 2 [GREEN] - choose column (1-7): 3\n" +
                "| | | | | | | |\n" +
                "|R| |G| | | | |\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Player 1 [RED] - choose column (1-7): 5\n" +
                "| | | | | | | |\n" +
                "|R| |G| |R| | |\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Player 2 [GREEN] - choose column (1-7): 7\n" +
                "| | | | | | | |\n" +
                "|R| |G| |R| |G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Player 1 [RED] - choose column (1-7): 2\n" +
                "| | | | | | | |\n" +
                "|R|R|G| |R| |G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Player 2 [GREEN] - choose column (1-7): 4\n" +
                "| | | | | | | |\n" +
                "|R|R|G|G|R| |G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Player 1 [RED] - choose column (1-7): 6\n" +
                "| | | | | | | |\n" +
                "|R|R|G|G|R|R|G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Player 2 [GREEN] - choose column (1-7): 1\n" +
                "|G| | | | | | |\n" +
                "|R|R|G|G|R|R|G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Player 1 [RED] - choose column (1-7): 3\n" +
                "|G| |R| | | | |\n" +
                "|R|R|G|G|R|R|G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Player 2 [GREEN] - choose column (1-7): 5\n" +
                "|G| |R| |G| | |\n" +
                "|R|R|G|G|R|R|G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Player 1 [RED] - choose column (1-7): 7\n" +
                "|G| |R| |G| |R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Player 2 [GREEN] - choose column (1-7): 2\n" +
                "|G|G|R| |G| |R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Player 1 [RED] - choose column (1-7): 4\n" +
                "|G|G|R|R|G| |R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Player 2 [GREEN] - choose column (1-7): 6\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "|G|G|R|R|G|G|R|\n" +
                "|R|R|G|G|R|R|G|\n" +
                "\n" +
                "Game ends in draw\n";
        Assert.assertEquals(expectedResult, term.toString());
    }

    @Test
    public void testQuitGame() throws IOException {
        Term term = new Term(Arrays.asList("4", "4", "Q"));
        Game game = Game.newGame(term);
        game.play();
        String expectedResult =
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "\n" +
                "Player 1 [RED] - choose column (1-7): 4\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | |R| | | |\n" +
                "\n" +
                "Player 2 [GREEN] - choose column (1-7): 4\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | |G| | | |\n" +
                "| | | |R| | | |\n" +
                "\n" +
                "Player 1 [RED] - choose column (1-7): Q\n" +
                "Quiting...\n";
        Assert.assertEquals(expectedResult, term.toString());
    }

    @Test
    public void testInvalidInput() throws IOException {
        Term term = new Term(Arrays.asList("1", "1", "1", "1", "1", "1", "1", "8", "Z"));
        Game game = Game.newGame(term);
        game.play();
        String expectedResult =
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "\n" +
                "Player 1 [RED] - choose column (1-7): 1\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "|R| | | | | | |\n" +
                "\n" +
                "Player 2 [GREEN] - choose column (1-7): 1\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "|G| | | | | | |\n" +
                "|R| | | | | | |\n" +
                "\n" +
                "Player 1 [RED] - choose column (1-7): 1\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "|R| | | | | | |\n" +
                "|G| | | | | | |\n" +
                "|R| | | | | | |\n" +
                "\n" +
                "Player 2 [GREEN] - choose column (1-7): 1\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "|G| | | | | | |\n" +
                "|R| | | | | | |\n" +
                "|G| | | | | | |\n" +
                "|R| | | | | | |\n" +
                "\n" +
                "Player 1 [RED] - choose column (1-7): 1\n" +
                "| | | | | | | |\n" +
                "|R| | | | | | |\n" +
                "|G| | | | | | |\n" +
                "|R| | | | | | |\n" +
                "|G| | | | | | |\n" +
                "|R| | | | | | |\n" +
                "\n" +
                "Player 2 [GREEN] - choose column (1-7): 1\n" +
                "|G| | | | | | |\n" +
                "|R| | | | | | |\n" +
                "|G| | | | | | |\n" +
                "|R| | | | | | |\n" +
                "|G| | | | | | |\n" +
                "|R| | | | | | |\n" +
                "\n" +
                "Player 1 [RED] - choose column (1-7): 1\n" +
                "Invalid choice\n" +
                "|G| | | | | | |\n" +
                "|R| | | | | | |\n" +
                "|G| | | | | | |\n" +
                "|R| | | | | | |\n" +
                "|G| | | | | | |\n" +
                "|R| | | | | | |\n" +
                "\n" +
                "Player 1 [RED] - choose column (1-7): 8\n" +
                "Invalid choice\n" +
                "|G| | | | | | |\n" +
                "|R| | | | | | |\n" +
                "|G| | | | | | |\n" +
                "|R| | | | | | |\n" +
                "|G| | | | | | |\n" +
                "|R| | | | | | |\n" +
                "\n" +
                "Player 1 [RED] - choose column (1-7): Z\n" +
                "Invalid choice\n" +
                "|G| | | | | | |\n" +
                "|R| | | | | | |\n" +
                "|G| | | | | | |\n" +
                "|R| | | | | | |\n" +
                "|G| | | | | | |\n" +
                "|R| | | | | | |\n" +
                "\n" +
                "Player 1 [RED] - choose column (1-7): Quiting...\n";
        Assert.assertEquals(expectedResult, term.toString());
    }
}
