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

public class LowSecurityUnitPanel{
    // Sub-Panels for the lowSecurityUnitPanel
    private final JPanel lowSecurityUnitPanel;
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
    public LowSecurityUnitPanel() throws IOException {
        // lowSecurityUnitPanel & lowSecurityUnitPanel Title
        lowSecurityUnitPanel = new JPanel();
        titlePanel = new JPanel();
        inmateMainPanel = new JPanel();
        lowSecurityUnitPanelSettings();
        paintInmates();
        addPanels();
    }

    // lowSecurityUnitPanelSettings method
    private void lowSecurityUnitPanelSettings(){
        lowSecurityUnitPanel.setLayout(new BoxLayout(lowSecurityUnitPanel, BoxLayout.Y_AXIS));
        lowSecurityUnitPanel.setBackground(Color.gray);
        JLabel title = new JLabel();
        titlePanel.add(title);
        title.setBackground(Color.gray);
        titlePanel.setMaximumSize(new Dimension(320, 40));
        title.setText("Low Security Unit");
        title.setFont(title.getFont().deriveFont(Font.BOLD));
        lowSecurityUnitPanel.setBorder(BorderFactory.createLineBorder(Color.black, 5, false));
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
        lowSecurityUnitPanel.add(titlePanel);
        lowSecurityUnitPanel.add(inmateMainPanel);
    }

    // Returns the lowSecurityUnitPanel
    public JPanel getLowSecurityUnitPanel() {
        lowSecurityUnitPanel.repaint();
        return lowSecurityUnitPanel;
    }

    public void getInmateMainPanel(){
        paintInmates();
    }

    public void updateInmateList(Map<Integer, Inmate> inmates) {
        currentInmates.putAll(inmates);
        paintInmates();
    }
}