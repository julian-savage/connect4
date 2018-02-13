package com.jms.connect4;

import java.io.IOException;

public interface Terminal {
    void println(String x);
    void print(String x);
    String readLine() throws IOException;
}
