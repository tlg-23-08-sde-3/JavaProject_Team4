package org.prison.silicon.gui;

import javax.swing.*;
import java.awt.*;

public class ScorePanel {
    JPanel scorePanel;
    JLabel title;
    JLabel riotStatusLabel;
    JPanel riotStatusBar;

    public ScorePanel() {

        // Main panel
        scorePanel = new JPanel();
        scorePanel.setLayout(null);
        title = new JLabel();
        title.setText("Prison Status");
        title.setFont(new Font("Serif", Font.BOLD, 35));
        title.setBounds(55, 5, 300, 40);
        scorePanel.add(title);
        scorePanel.setBorder(BorderFactory.createLineBorder(Color.black, 5, false));
        scorePanel.setBackground(Color.gray);

        // Riot Status bar and label
        riotStatusLabel = new JLabel("Riot Status: ");
        riotStatusLabel.setFont(new Font("Serif", Font.BOLD, 16));
        riotStatusLabel.setBounds(10, 95, 100, 30);
        riotStatusBar = new JPanel();
        // TODO: the "width" below needs to be the happiness percent between
        //  [0 - 100] & the JLabel below also needs to reflect the correct percent
        riotStatusBar.setBounds(100, 95, 200, 30);
        riotStatusBar.setBorder(BorderFactory.createLineBorder(Color.black, 2, false));
        riotStatusBar.add(new JLabel("100%"));
        // Add the riot status bar and label to the main scorePanel
        scorePanel.add(riotStatusBar);
        scorePanel.add(riotStatusLabel);


    }

    public JPanel getScorePanel() {
        return scorePanel;
    }
}