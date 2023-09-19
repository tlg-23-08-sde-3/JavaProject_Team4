package org.prison.silicon.gui;

import org.prison.silicon.Facility;
import org.prison.silicon.population.Inmate;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class LowSecurityUnitPanel {
    JPanel lowSecurityUnitPanel;
    JLabel title;
    Map<Integer, Inmate> currentInmates = new TreeMap<>();

    // Inmate image
    BufferedImage inmatePicture1 = ImageIO.read(new File("resources/images/prisoner.png"));
    Image inmate1 = inmatePicture1.getScaledInstance(25, 50, Image.SCALE_DEFAULT);

    public LowSecurityUnitPanel() throws IOException {
        lowSecurityUnitPanel = new JPanel();
        lowSecurityUnitPanel.setLayout(null);
        title = new JLabel();
        title.setText("Low Security Unit");
        title.setFont(title.getFont().deriveFont(Font.BOLD));
        title.setBounds(100, 5, 150, 25);
        lowSecurityUnitPanel.add(title);
        lowSecurityUnitPanel.setBorder(BorderFactory.createLineBorder(Color.black, 5, false));
        // lowSecurityUnitPanel.setBounds(10, 10, 200, 200);
        lowSecurityUnitPanel.setBackground(Color.lightGray);
    }

    // paints the inmate clipart for each inmate in currentInmates for this location
    private void paintInmates() {
        // Start x and y points
        int xAxis = 25;
        int yAxis = 75;

        try{
            for(Map.Entry<Integer, Inmate> inmate : currentInmates.entrySet()) {
                // TODO: create JPanel for each inmate with NORTH = image & South = inmate ID
                lowSecurityUnitPanel.add (new JLabel(new ImageIcon((inmate1)))).setBounds(xAxis, yAxis, 25, 50);
                if( xAxis <= 250){
                    xAxis += 50;
                } else {
                    xAxis = 25;
                    yAxis += 75;
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public JPanel getLowSecurityUnitPanel() {
        return lowSecurityUnitPanel;
    }

    public void updateInmateList(Map<Integer, Inmate> inmates) {
        currentInmates.putAll(inmates);
        paintInmates();
    }
}