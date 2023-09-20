package org.prison.silicon.gui;

import org.junit.Test;
import org.prison.silicon.Prison;

import java.io.IOException;

public class MainGuiTest {
    @Test
    public void mainTest() throws IOException {
        Prison prison = null;
        new MainGui(prison);
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}