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

public class YardPanel {
    private final JPanel yardPanel;
    private final JLabel title;
    private final Map<Integer, Inmate> currentInmates = new TreeMap<>();

    // Normal inmate image
    BufferedImage inmatePicture1 = ImageIO.read(new File("resources/images/prisoner.png"));
    Image normalInmateIcon = inmatePicture1.getScaledInstance(25, 50, Image.SCALE_DEFAULT);

    // Gang leader inmate icon
    BufferedImage inmatePicture2 = ImageIO.read(new File("resources/images/gangleader.png"));
    Image gangLeaderInmateIcon = inmatePicture2.getScaledInstance(25, 50, Image.SCALE_DEFAULT);

    public YardPanel() throws IOException {
        yardPanel = new JPanel();
        yardPanel.setLayout(null);
        title = new JLabel();
        title.setText("Yard Area");
        title.setFont(title.getFont().deriveFont(Font.BOLD));
        title.setBounds(120, 5, 150, 25);
        yardPanel.add(title);
        yardPanel.setBorder(BorderFactory.createLineBorder(Color.black, 5, false));
        //mediumSecurityUnitPanel.setBounds(200, 10, 200, 200);
        yardPanel.setBackground(Color.lightGray);
    }

    private void paintInmates() {
        // Start x and y points
        int xAxis = 20;
        int yAxis = 40;

        try{
            for(Map.Entry<Integer, Inmate> inmate : currentInmates.entrySet()) {
                // TODO: create JPanel for each inmate with NORTH = image & South = inmate ID
                JPanel inmateIconPanel = new JPanel();
                inmateIconPanel.setBackground(Color.lightGray);
                inmateIconPanel.setLayout(null);
                inmateIconPanel.setBounds(xAxis, yAxis, 40, 80);
                JLabel testlabel = new JLabel(inmate.getKey().toString());
                testlabel.setBounds(3, 60, 35, 20);
                if(!inmate.getValue().isGangLeader()){
                    inmateIconPanel.add (new JLabel(new ImageIcon((normalInmateIcon)))).setBounds(3, 1, 35, 65);
                } else {
                    inmateIconPanel.add(new JLabel(new ImageIcon((gangLeaderInmateIcon)))).setBounds(3, 1, 35, 65);
                }
                inmateIconPanel.add(testlabel);
                yardPanel.add(inmateIconPanel);
                if( xAxis <= 250){
                    xAxis += 41;
                } else {
                    xAxis = 20;
                    yAxis += 85;
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public JPanel getYardPanel() {
        return yardPanel;
    }

    public void updateInmateList(Map<Integer, Inmate> inmates) {
        currentInmates.putAll(inmates);
        paintInmates();
    }
}