package org.prison.silicon.gui;

import org.prison.silicon.facility.Facility;
import org.prison.silicon.facility.Prison;

import javax.swing.*;
import java.awt.*;

public class ScorePanel {
    private final JPanel scorePanel;
    private final JPanel mainTitlePanel;
    private final JLabel title;
    // Riot Status bar and label
    private final JPanel riotProgressBarPanel;
    private final JLabel riotProgressBarLabel;
    private final JProgressBar riotProgressBar;

    // Riot Status bar and label
    private final JPanel lowSecurityProgressBarPanel;
    private final JLabel lowSecurityProgressBarLabel;
    private final JProgressBar lowSecurityProgressBar;

    // Riot Status bar and label
    private final JPanel mediumSecurityProgressBarPanel;
    private final JLabel mediumSecurityProgressBarLabel;
    private final JProgressBar mediumSecurityProgressBar;

    // Riot Status bar and label
    private final JPanel highSecurityProgressBarPanel;
    private final JLabel highSecurityProgressBarLabel;
    private final JProgressBar highSecurityProgressBar;

    // Riot Status bar and label
    private final JPanel yardProgressBarPanel;
    private final JLabel yardProgressBarLabel;
    private final JProgressBar yardProgressBar;

    // Riot Status bar and label
    private final JPanel kitchenProgressBarPanel;
    private final JLabel kitchenProgressBarLabel;
    private final JProgressBar kitchenProgressBar;

    // Riot Status bar and label
    private final JPanel workAreaProgressBarPanel;
    private final JLabel workAreaProgressBarLabel;
    private final JProgressBar workAreaProgressBar;


    public ScorePanel(Prison prison, Facility lowSecurityUnit, Facility mediumSecurityUnit, Facility highSecurityUnit, Facility yard,
                      Facility kitchen, Facility workArea) {

        // Main panel
        scorePanel = new JPanel();
        scorePanel.setBorder(BorderFactory.createLineBorder(Color.black, 5, false));
        scorePanel.setBackground(Color.gray);
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        mainTitlePanel = new JPanel();
        mainTitlePanel.setBackground(Color.gray);
        title = new JLabel();
        mainTitlePanel.add(title);
        title.setText("Prison Riot Status");
        title.setFont(new Font("Serif", Font.BOLD, 35));
        title.setBounds(60, 5, 300, 40);
        scorePanel.add(mainTitlePanel);

        // Riot progerss bar
        riotProgressBarPanel = new JPanel();
        riotProgressBarPanel.setBackground(Color.gray);
        riotProgressBarLabel = new JLabel("Overall Statis: ");
        riotProgressBarPanel.add(riotProgressBarLabel);
        riotProgressBar = new JProgressBar(0, 100);
        riotProgressBar.setStringPainted(true);
        // TODO: The below is set to the overall prison risk rating
        riotProgressBar.setValue(prison.getRiskRating());
        riotProgressBarPanel.add(riotProgressBar);
        scorePanel.add(riotProgressBarPanel);

        // Low Security progress bar
        lowSecurityProgressBarPanel = new JPanel();
        lowSecurityProgressBarPanel.setBackground(Color.gray);
        lowSecurityProgressBarLabel = new JLabel("Low Security Unit: ");
        lowSecurityProgressBarPanel.add(lowSecurityProgressBarLabel);
        lowSecurityProgressBar = new JProgressBar(0, 100);
        lowSecurityProgressBar.setStringPainted(true);
        // TODO: The below needs to be changed to the low security unit risk rating
        lowSecurityProgressBar.setValue(lowSecurityUnit.getRiskRating());
        lowSecurityProgressBarPanel.add(lowSecurityProgressBar);
        scorePanel.add(lowSecurityProgressBarPanel);

        // Medium Security progress bar
        mediumSecurityProgressBarPanel = new JPanel();
        mediumSecurityProgressBarPanel.setBackground(Color.gray);
        mediumSecurityProgressBarLabel = new JLabel("Medium Security Unit: ");
        mediumSecurityProgressBarPanel.add(mediumSecurityProgressBarLabel);
        mediumSecurityProgressBar = new JProgressBar(0, 100);
        mediumSecurityProgressBar.setStringPainted(true);
        // TODO: The below needs to be changed to the medium security unit risk rating
        mediumSecurityProgressBar.setValue(mediumSecurityUnit.getRiskRating());
        mediumSecurityProgressBarPanel.add(mediumSecurityProgressBar);
        scorePanel.add(mediumSecurityProgressBarPanel);

        // High Security progress bar
        highSecurityProgressBarPanel = new JPanel();
        highSecurityProgressBarPanel.setBackground(Color.gray);
        highSecurityProgressBarLabel = new JLabel("High Security Unit: ");
        highSecurityProgressBarPanel.add(highSecurityProgressBarLabel);
        highSecurityProgressBar = new JProgressBar(0, 100);
        highSecurityProgressBar.setStringPainted(true);
        // TODO: The below needs to be changed to the high security unit risk rating
        highSecurityProgressBar.setValue(highSecurityUnit.getRiskRating());
        highSecurityProgressBarPanel.add(highSecurityProgressBar);
        scorePanel.add(highSecurityProgressBarPanel);

        // Yard progress bar
        yardProgressBarPanel = new JPanel();
        yardProgressBarPanel.setBackground(Color.gray);
        yardProgressBarLabel = new JLabel("Yard: ");
        yardProgressBarPanel.add(yardProgressBarLabel);
        yardProgressBar = new JProgressBar(0, 100);
        yardProgressBar.setStringPainted(true);
        // TODO: The below needs to be changed to the yard risk rating
        yardProgressBar.setValue(yard.getRiskRating());
        yardProgressBarPanel.add(yardProgressBar);
        scorePanel.add(yardProgressBarPanel);

        // Kitchen progress bar
        kitchenProgressBarPanel = new JPanel();
        kitchenProgressBarPanel.setBackground(Color.gray);
        kitchenProgressBarLabel = new JLabel("Kitchen: ");
        kitchenProgressBarPanel.add(kitchenProgressBarLabel);
        kitchenProgressBar = new JProgressBar(0, 100);
        kitchenProgressBar.setStringPainted(true);
        // TODO: The below needs to be changed to the kitchen risk rating
        kitchenProgressBar.setValue(kitchen.getRiskRating());
        kitchenProgressBarPanel.add(kitchenProgressBar);
        scorePanel.add(kitchenProgressBarPanel);

        // Work Area progress bar
        workAreaProgressBarPanel = new JPanel();
        workAreaProgressBarPanel.setBackground(Color.gray);
        workAreaProgressBarLabel = new JLabel("Work Area: ");
        workAreaProgressBarPanel.add(workAreaProgressBarLabel);
        workAreaProgressBar = new JProgressBar(0, 100);
        workAreaProgressBar.setStringPainted(true);
        // TODO: The below needs to be changed to the work area risk rating
        workAreaProgressBar.setValue(workArea.getRiskRating());
        workAreaProgressBarPanel.add(workAreaProgressBar);
        scorePanel.add(workAreaProgressBarPanel);
    }

    public JPanel getScorePanel() {
        return scorePanel;
    }
}