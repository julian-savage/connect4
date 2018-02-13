package com.jms.connect4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public final class Main {
    public static void main(String[] args) throws IOException {
        Game game = Game.newGame(new Terminal() {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            PrintStream out = System.out;
            @Override
            public void println(String x) {
                out.println(x);
            }

            @Override
            public void print(String x) {
                out.print(x);
            }

            @Override
            public String readLine() throws IOException {
                return in.readLine();
            }
        });
        game.play();
    }
}
