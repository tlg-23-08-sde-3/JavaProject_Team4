package org.prison.silicon.app;

import org.prison.silicon.facility.Facility;
import org.prison.silicon.facility.Prison;
import org.prison.silicon.gui.ScorePanel;
import org.prison.silicon.population.Inmate;
import org.prison.silicon.population.LowSecurityInmate;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HappinessThread extends Thread {
    private final Prison prison;
    ScorePanel scorePanel;

    public HappinessThread(Prison prison, ScorePanel scorePanel) {
        setName("GangLeaderHappinessThread");
        this.prison = prison;
        this.scorePanel = scorePanel;
    }

    @Override
    public void run() {
        super.run();
        try {
            for(int i = 0; i < 5000; i++){
                if(i % 24 == 0 && i != 0){
                    this.popUp();
                }
                Thread.sleep(5000);
                for(Facility facility : prison.getPrisonMap().keySet()){
                    for(Inmate inmate : facility.getCurrentInmates().values()){
                        inmate.setHappiness(inmate.getHappiness() - 10);
                    }

                }
                scorePanel.paintProgressBars();
            }
        } catch (InterruptedException ignored) {
        }
    }

    private void popUp() {
        String message = "Gang leaders are starting to get frustrated and their happiness is decreasing";
        String title = "Gang Leader Happiness Warning";
        JOptionPane.showMessageDialog(null,
                message,
                title,
                JOptionPane.INFORMATION_MESSAGE);
    }
}