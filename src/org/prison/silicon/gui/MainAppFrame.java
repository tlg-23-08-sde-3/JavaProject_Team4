//package org.prison.silicon.gui;
package org.prison.silicon.gui;

import org.prison.silicon.facility.Prison;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class MainAppFrame {

    // Instance variables
    private final Prison prison;
    JFrame mainFrame;
    SplashPanel introPagePanel;

    // Constructor
    public MainAppFrame(Prison prison) throws IOException {
        this.prison = prison;
        mainFrame = new JFrame();
        mainFrame.setTitle("JailBreak");
        mainFrame.setLayout(new GridLayout(1, 2, 10, 10));
        mainFrame.setSize(1300, 800);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
        runApp();
    }

    // Launches the Slash page
    private void runApp() throws IOException {
        introPagePanel = new SplashPanel(prison, mainFrame);
        mainFrame.add(introPagePanel.getSplashPanel());
        mainFrame.validate();
        mainFrame.repaint();
    }
}