//package org.prison.silicon.gui;
package org.prison.silicon.gui;

import org.prison.silicon.facility.Prison;

import org.prison.silicon.population.Inmate;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Map;

public class MainGui {
    JFrame mainFrame;
    LowSecurityUnitPanel lowSecurityUnitPanel;
    MediumSecurityUnitPanel mediumSecurityUnitPanel;
    HighSecurityUnitPanel highSecurityUnitPanel;
    UserInputPanel userInputPanel;
    YardPanel yardPanel;
    KitchenPanel kitchenPanel;
    WorkAreaPanel workAreaPanel;
    ScorePanel scorePanel;


    public MainGui(Prison prison) throws IOException {

        System.out.println("main painted");

        userInputPanel = new UserInputPanel(prison);
        lowSecurityUnitPanel = new LowSecurityUnitPanel();
        mediumSecurityUnitPanel = new MediumSecurityUnitPanel();
        highSecurityUnitPanel = new HighSecurityUnitPanel();
        yardPanel = new YardPanel();
        kitchenPanel = new KitchenPanel();
        workAreaPanel = new WorkAreaPanel();
        scorePanel = new ScorePanel();

        mainFrame = new JFrame();
        mainFrame.setTitle("JailBreak");
        mainFrame.add(lowSecurityUnitPanel.getLowSecurityUnitPanel());
        mainFrame.add(mediumSecurityUnitPanel.getMediumSecurityUnitPanel());
        mainFrame.add(highSecurityUnitPanel.getHighSecurityUnitPanel());
        mainFrame.add(scorePanel.getScorePanel());
        mainFrame.add(yardPanel.getYardPanel());
        mainFrame.add(kitchenPanel.getKitchenPanel());
        mainFrame.add(workAreaPanel.getWorkAreaPanel());
        mainFrame.add(userInputPanel.getUserInputPanel());

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new GridLayout(2, 2, 10, 10));
        mainFrame.setSize(1300, 800);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    // Action method to repaint the GUI as needed
    public void repaintGui() {
        mainFrame.repaint();
    }

    // Update all Panel counts
    public void updateCounts(Map<Integer, Inmate> lowSecurityMap, Map<Integer, Inmate> mediumSecurityMap,
                             Map<Integer, Inmate> highSecurityMap, Map<Integer, Inmate> yardMap,
                             Map<Integer, Inmate> kitchenMap, Map<Integer, Inmate> workAreaMap){

        lowSecurityUnitPanel.updateInmateList(lowSecurityMap);
        mediumSecurityUnitPanel.updateInmateList(mediumSecurityMap);
        highSecurityUnitPanel.updateInmateList(highSecurityMap);
        yardPanel.updateInmateList(yardMap);
        kitchenPanel.updateInmateList(kitchenMap);
        workAreaPanel.updateInmateList(workAreaMap);

        // repaint the frame after updating all areas
        mainFrame.repaint();
    }
}