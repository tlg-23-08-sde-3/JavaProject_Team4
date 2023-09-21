package org.prison.silicon.gui;

import org.junit.Test;

import org.prison.silicon.facility.Prison;

import static org.prison.silicon.facility.Facility.*;

import java.io.IOException;

public class MainGuiTest {
    public static Prison prison = new Prison("Prison", LOW_SECURITY_UNIT, MEDIUM_SECURITY_UNIT, HIGH_SECURITY_UNIT, YARD, KITCHEN, WORK_AREA);

    @Test
    public void mainTest() throws IOException {
        new MainGui(prison, LOW_SECURITY_UNIT, MEDIUM_SECURITY_UNIT, HIGH_SECURITY_UNIT, YARD, KITCHEN, WORK_AREA);
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}