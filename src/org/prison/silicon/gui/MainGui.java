//package org.prison.silicon.gui;
package org.prison.silicon.gui;

import org.prison.silicon.Prison;
import org.prison.silicon.population.Inmate;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Map;

public class MainGui {
    JFrame mainFrame;
    LowSecurityUnitPanel lowSecurityUnitPanel = new LowSecurityUnitPanel();
    MediumSecurityUnitPanel mediumSecurityUnitPanel = new MediumSecurityUnitPanel();
    HighSecurityUnitPanel highSecurityUnitPanel = new HighSecurityUnitPanel();
    private Prison prison;

    YardPanel yardPanel = new YardPanel();
    KitchenPanel kitchenPanel = new KitchenPanel();
    WorkAreaPanel workAreaPanel = new WorkAreaPanel();
    ScorePanel scorePanel = new ScorePanel();

    public MainGui(Prison prison) throws IOException {
        UserInputPanel userInputPanel = new UserInputPanel(prison);

        // Medium Security Unit Panel
        JPanel medSec = new JPanel();
        medSec.setBorder(BorderFactory.createLineBorder(Color.black, 5, true));
        JLabel medSecTitle = new JLabel("Medium Security Unit");
        medSecTitle.setFont(medSecTitle.getFont().deriveFont(Font.BOLD));
        medSec.add(medSecTitle).setLocation(50, 30);
        medSec.setBackground(Color.lightGray);


        // Low Security Unit Panel
        JPanel highSec = new JPanel();
        highSec.setBorder(BorderFactory.createLineBorder(Color.black, 5, true));
        JLabel highSecTitle = new JLabel("High Security Unit");
        highSecTitle.setFont(highSecTitle.getFont().deriveFont(Font.BOLD));
        highSec.add(highSecTitle).setLocation(50, 30);
        highSec.setBackground(Color.lightGray);

        // Yard Panel
        JPanel yard = new JPanel();
        yard.setBorder(BorderFactory.createLineBorder(Color.black, 5, true));
        JLabel yardTitle = new JLabel("Yard Area");
        yardTitle.setFont(yardTitle.getFont().deriveFont(Font.BOLD));
        yard.add(yardTitle).setLocation(50, 30);
        yard.setBackground(Color.lightGray);

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
    }

    // Action method to repaint the GUI as needed
    public void repaintGui() {
        mainFrame.repaint();
    }

    // Update all Panel counts
    public void updateCounts(Map<Integer, Inmate> lowSecurityMap, Map<Integer, Inmate> mediumSecurityMap,
                             Map<Integer, Inmate> highSecurityMap, Map<Integer, Inmate> yardMap,
                             Map<Integer, Inmate> kitchenMap){

        lowSecurityUnitPanel.updateInmateList(lowSecurityMap);
        mediumSecurityUnitPanel.updateInmateList(mediumSecurityMap);
        highSecurityUnitPanel.updateInmateList(highSecurityMap);
        yardPanel.updateInmateList(yardMap);
        kitchenPanel.updateInmateList(kitchenMap);

        // repaint the frame after updating all areas
        mainFrame.repaint();
    }
}