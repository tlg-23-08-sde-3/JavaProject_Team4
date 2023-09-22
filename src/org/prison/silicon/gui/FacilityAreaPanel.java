package org.prison.silicon.gui;

import org.prison.silicon.facility.Facility;
import org.prison.silicon.population.Inmate;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class FacilityAreaPanel {

    // Instance variables
    private final Facility facilityUnit;
    private final JPanel facilityUnitPanel;
    private final JPanel inmateMainPanel;
    private final JPanel titlePanel;
    private final JLabel title;
    private final Map<Integer, Inmate> currentInmates = new TreeMap<>();

    // Images
    private BufferedImage normalInmateImage;
    private BufferedImage gangLeaderImage;

    {
        try {
            gangLeaderImage = ImageIO.read(new File("resources/images/gangleader.png"));
            normalInmateImage = ImageIO.read(new File("resources/images/prisoner.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final Image normalInmateIcon = normalInmateImage.getScaledInstance(25, 50, Image.SCALE_DEFAULT);
    private final Image gangLeaderInmateIcon = gangLeaderImage.getScaledInstance(25, 50, Image.SCALE_DEFAULT);

    // Constructor
    public FacilityAreaPanel(Facility facilityUnit, String name) {
        this.facilityUnit = facilityUnit;
        facilityUnitPanel = new JPanel();
        titlePanel = new JPanel();
        inmateMainPanel = new JPanel();
        title = new JLabel(name + ", Max Inmates: 30");
        facilityUnitSetup();
        paintInmates();
        addPanels();
    }

    // lowSecurityUnitPanelSettings method
    private void facilityUnitSetup() {
        facilityUnitPanel.setLayout(new BoxLayout(facilityUnitPanel, BoxLayout.Y_AXIS));
        facilityUnitPanel.setBackground(Color.gray);
        titlePanel.add(title);
        title.setBackground(Color.gray);
        titlePanel.setMaximumSize(new Dimension(320, 40));
        title.setFont(title.getFont().deriveFont(Font.BOLD));
        facilityUnitPanel.setBorder(BorderFactory.createLineBorder(Color.black, 5, false));
    }

    // Paints the inmate clip are for each area of the prison
    // First the inmateMainPanel is cleared then repainted
    public void paintInmates() {
        inmateMainPanel.removeAll();
        currentInmates.clear();
        currentInmates.putAll(facilityUnit.getInmateMap());
        try {
            for (Map.Entry<Integer, Inmate> inmate : currentInmates.entrySet()) {
                JPanel inmateIconPanel = new JPanel();
                inmateIconPanel.setLayout(new BoxLayout(inmateIconPanel, BoxLayout.Y_AXIS));
                JLabel inmateIdLabel = new JLabel(inmate.getKey().toString());
                if (!inmate.getValue().isGangLeader()) {
                    inmateIconPanel.add(new JLabel(new ImageIcon((normalInmateIcon))));
                } else {
                    inmateIconPanel.add(new JLabel(new ImageIcon((gangLeaderInmateIcon))));
                }
                inmateIconPanel.add(inmateIdLabel);
                inmateMainPanel.add(inmateIconPanel);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        inmateMainPanel.revalidate();
        inmateMainPanel.repaint();
    }

    // Adds the titlePanel and inmateMainPanel to the facilityUnitPanel that is returned to MainAppPanel
    private void addPanels() {
        facilityUnitPanel.add(titlePanel);
        facilityUnitPanel.add(inmateMainPanel);
    }

    // Returns the lowSecurityUnitPanel
    public JPanel getFacilityUnitPanel() {
        facilityUnitPanel.repaint();
        return facilityUnitPanel;
    }

    // Updates the currentInmate Map with the latest position
    public void updateInmateList(Map<Integer, Inmate> inmates) {
        currentInmates.putAll(inmates);
        paintInmates();
    }
}