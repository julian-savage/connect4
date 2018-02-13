package com.jms.connect4;

import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {
    @Test
    public void testToString() {
        Assert.assertEquals("Player 1 [RED]", Player.PLAYER1.toString());
        Assert.assertEquals("Player 2 [GREEN]", Player.PLAYER2.toString());
    }

    @Test
    public void testNextPlayer() {
        Assert.assertEquals(Player.PLAYER2, Player.PLAYER1.next());
        Assert.assertEquals(Player.PLAYER1, Player.PLAYER2.next());
    }
}
