package org.prison.silicon.gui;

import org.prison.silicon.population.Inmate;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class HighSecurityUnitPanel {
    JPanel highSecurityUnitPanel;
    JLabel title;
    Map<Integer, Inmate> currentInmates;

    // Inmate image
    BufferedImage inmatePicture1 = ImageIO.read(new File("resources/images/prisoner.png"));
    Image inmate1 = inmatePicture1.getScaledInstance(25, 50, Image.SCALE_DEFAULT);

    public HighSecurityUnitPanel() throws IOException {
        highSecurityUnitPanel = new JPanel();
        highSecurityUnitPanel.setLayout(null);
        title = new JLabel();
        title.setText("High Security Unit");
        title.setFont(title.getFont().deriveFont(Font.BOLD));
        title.setBounds(100, 5, 150, 25);
        highSecurityUnitPanel.add(title);
        highSecurityUnitPanel.setBorder(BorderFactory.createLineBorder(Color.black, 5, false));
        // highSecurityUnitPanel.setBounds(400, 10, 200, 200);
        highSecurityUnitPanel.setBackground(Color.lightGray);


        // Add inmate clipart based on the number of inmates in the LowSecurityUnit area.
        int xAxis = 25;
        int yAxis = 75;

        // TODO: count the number of inmates in the "LowSecurityUnit" Map
        //  then wrap the below line in a for-loop and increments the x and y axis as needed
        try{
            for(Map.Entry<Integer, Inmate> inmate : currentInmates.entrySet()) {
                // TODO: create JPanel for each inmate with NORTH = image & South = inmate ID
                highSecurityUnitPanel.add (new JLabel(new ImageIcon((inmate1)))).setBounds(xAxis, yAxis, 25, 50);
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

    public JPanel getHighSecurityUnitPanel() {
        return highSecurityUnitPanel;
    }

    public void updateInmateList(Map<Integer, Inmate> inmates) {
        currentInmates = inmates;
    }
}