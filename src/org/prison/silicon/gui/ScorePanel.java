package org.prison.silicon.gui;

import org.prison.silicon.facility.Facility;
import org.prison.silicon.facility.FacilityList;
import org.prison.silicon.facility.Prison;
import org.prison.silicon.population.LowSecurityInmate;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ScorePanel {
    private final JPanel scorePanel;
    private final JPanel mainTitlePanel;
    private final JLabel title;
    private final Prison prison;
    private final Facility lowSecurityUnit;
    private final Facility mediumSecurityUnit;
    private final Facility highSecurityUnit;
    private final Facility yard;
    private final Facility kitchen;
    private final Facility workArea;

    public ScorePanel(Prison prison, Facility lowSecurityUnit, Facility mediumSecurityUnit, Facility highSecurityUnit, Facility yard,
                      Facility kitchen, Facility workArea) {

        scorePanel = new JPanel();
        mainTitlePanel = new JPanel();
        title = new JLabel();
        this.prison = prison;
        this.lowSecurityUnit = lowSecurityUnit;
        this.mediumSecurityUnit = mediumSecurityUnit;
        this.highSecurityUnit = highSecurityUnit;
        this.yard = yard;
        this.kitchen = kitchen;
        this.workArea = workArea;
        paintProgressBars();
    }

    private void mainScorePanelCreation() {
        mainTitlePanel.add(title);
        scorePanel.add(mainTitlePanel);
    }

    public void paintProgressBars(){
        scorePanel.removeAll();
        scorePanelSetup();
        mainScorePanelCreation();
        ProgressBarSetup("Overall Status: ", prison.getRiskRating());
        ProgressBarSetup("Low Security Unit: ", lowSecurityUnit.getRiskRating());
        ProgressBarSetup("Medium Security Unit: ", mediumSecurityUnit.getRiskRating());
        ProgressBarSetup("High Security Unit: ", highSecurityUnit.getRiskRating());
        ProgressBarSetup("Yard: ", yard.getRiskRating());
        ProgressBarSetup("Kitchen: ", kitchen.getRiskRating());
        ProgressBarSetup("Work Area: ", workArea.getRiskRating());
    }

    public void scorePanelSetup(){
        scorePanel.setBorder(BorderFactory.createLineBorder(Color.black, 5, false));
        scorePanel.setBackground(Color.gray);
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        mainTitlePanel.setBackground(Color.gray);
        title.setText("Prison Riot Status");
        title.setFont(new Font("Serif", Font.BOLD, 35));
        title.setBounds(60, 5, 300, 40);
    }

    public void ProgressBarSetup(String name, int rating){
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.gray);
        JLabel mainPanelLabel = new JLabel(name);
        mainPanel.add(mainPanelLabel);
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setValue(rating);
        mainPanel.add(progressBar);
        scorePanel.add(mainPanel);
    }

    public JPanel getScorePanel() {
        return scorePanel;
    }
}