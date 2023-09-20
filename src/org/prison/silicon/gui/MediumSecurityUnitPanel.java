package org.prison.silicon.gui;

import org.prison.silicon.population.Inmate;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class MediumSecurityUnitPanel {
    // Sub-Panels for the mediumSecurityUnitPanel
    private final JPanel mediumSecurityUnitPanel;
    // private final JLabel title;
    private final JPanel inmateMainPanel;
    private final JPanel titlePanel;
    // Inmate TreeMap
    private final Map<Integer, Inmate> currentInmates = new TreeMap<>();
    // Normal inmate image
    private final BufferedImage inmatePicture1 = ImageIO.read(new File("resources/images/prisoner.png"));
    private final Image normalInmateIcon = inmatePicture1.getScaledInstance(25, 50, Image.SCALE_DEFAULT);
    // Gang leader inmate icon
    private final BufferedImage inmatePicture2 = ImageIO.read(new File("resources/images/gangleader.png"));
    private final Image gangLeaderInmateIcon = inmatePicture2.getScaledInstance(25, 50, Image.SCALE_DEFAULT);


    // Constructor - throws IOException since it loading an image file with ImageIO.read
    public MediumSecurityUnitPanel() throws IOException {
        // mediumSecurityUnitPanel & mediumSecurityUnitPanel Title
        mediumSecurityUnitPanel = new JPanel();
        titlePanel = new JPanel();
        inmateMainPanel = new JPanel();
        mediumSecurityUnitPanelSettings();
        paintInmates();
        addPanels();
    }

    // mediumSecurityUnitPanelSettings method
    private void mediumSecurityUnitPanelSettings(){
        mediumSecurityUnitPanel.setLayout(new BoxLayout(mediumSecurityUnitPanel, BoxLayout.Y_AXIS));
        JLabel title = new JLabel();
        titlePanel.add(title);
        title.setBackground(Color.lightGray);
        titlePanel.setMaximumSize(new Dimension(320, 40));
        title.setText("Medium Security Unit");
        title.setFont(title.getFont().deriveFont(Font.BOLD));
        mediumSecurityUnitPanel.setBorder(BorderFactory.createLineBorder(Color.black, 5, false));
        mediumSecurityUnitPanel.setBackground(Color.lightGray);
    }

    // paints the inmate clipart for each inmate in currentInmates for this location
    private void paintInmates() {
        try{
            for(Map.Entry<Integer, Inmate> inmate : currentInmates.entrySet()) {
                JPanel inmateIconPanel = new JPanel();
                inmateIconPanel.setLayout(new BoxLayout(inmateIconPanel, BoxLayout.Y_AXIS));
                JLabel inmateIdLabel = new JLabel(inmate.getKey().toString());
                if(!inmate.getValue().isGangLeader()){
                    inmateIconPanel.add (new JLabel(new ImageIcon((normalInmateIcon)))).setBounds(3, 1, 35, 65);
                } else {
                    inmateIconPanel.add(new JLabel(new ImageIcon((gangLeaderInmateIcon)))).setBounds(3, 1, 35, 65);
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

    private void addPanels(){
        mediumSecurityUnitPanel.add(titlePanel);
        mediumSecurityUnitPanel.add(inmateMainPanel);
    }

    // Returns the mediumSecurityUnitPanel
    public JPanel getMediumSecurityUnitPanel() {
        mediumSecurityUnitPanel.repaint();
        return mediumSecurityUnitPanel;
    }

    public void getInmateMainPanel(){
        paintInmates();
    }

    public void updateInmateList(Map<Integer, Inmate> inmates) {
        currentInmates.putAll(inmates);
        paintInmates();
    }
}