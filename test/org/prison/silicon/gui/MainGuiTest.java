package org.prison.silicon.gui;

import org.junit.Test;

import java.io.IOException;

public class MainGuiTest {
    @Test
    public void mainTest() throws IOException {
        new MainGui();
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}