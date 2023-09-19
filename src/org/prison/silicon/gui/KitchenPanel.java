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

public class KitchenPanel {
    JPanel kitchenPanel;
    JLabel title;
    Map<Integer, Inmate> currentInmates = new TreeMap<>();

    // Inmate image
    BufferedImage inmatePicture1 = ImageIO.read(new File("resources/images/prisoner.png"));
    Image inmate1 = inmatePicture1.getScaledInstance(25, 50, Image.SCALE_DEFAULT);


    public KitchenPanel() throws IOException {
        kitchenPanel = new JPanel();
        kitchenPanel.setLayout(null);
        title = new JLabel();
        title.setText("Kitchen Area");
        title.setFont(title.getFont().deriveFont(Font.BOLD));
        title.setBounds(100, 5, 150, 25);
        kitchenPanel.add(title);
        kitchenPanel.setBorder(BorderFactory.createLineBorder(Color.black, 5, false));
        //mediumSecurityUnitPanel.setBounds(200, 10, 200, 200);
        kitchenPanel.setBackground(Color.lightGray);
    }

    private void paintInmates() {
        // Add inmate clipart based on the number of inmates in the LowSecurityUnit area.
        int xAxis = 25;
        int yAxis = 75;

        // TODO: count the number of inmates in the "LowSecurityUnit" Map
        //  then wrap the below line in a for-loop and increments the x and y axis as needed
        try{
            for(Map.Entry<Integer, Inmate> inmate : currentInmates.entrySet()) {
                // TODO: create JPanel for each inmate with NORTH = image & South = inmate ID
                kitchenPanel.add (new JLabel(new ImageIcon((inmate1)))).setBounds(xAxis, yAxis, 25, 50);
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

    public JPanel getKitchenPanel() {
        return kitchenPanel;
    }

    public void updateInmateList(Map<Integer, Inmate> inmates) {
        currentInmates.putAll(inmates);
        paintInmates();
    }
}