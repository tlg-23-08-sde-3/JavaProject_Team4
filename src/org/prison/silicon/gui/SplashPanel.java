package org.prison.silicon.gui;

import org.prison.silicon.facility.Prison;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.prison.silicon.facility.Facility.*;
import static org.prison.silicon.facility.Facility.WORK_AREA;

public class SplashPanel {
    private final Prison prison;
    JPanel mainSplashPanel;
    JPanel leftSplashPanel;
    JPanel rightSplashPanel;
    JLabel title;
    JPanel titlePanel;
    JButton continueButton;

    // Gang leader inmate icon

    private final BufferedImage guard = ImageIO.read(new File("resources/images/guard.png"));
    private final Image guardImage = guard.getScaledInstance(400, 600, Image.SCALE_DEFAULT);

    public SplashPanel(Prison prison, JFrame mainFrame) throws IOException {

        this.prison = prison;

        mainSplashPanel = new JPanel();
        mainSplashPanel.setBackground(Color.white);
        mainSplashPanel.setLayout(new GridLayout(1, 2, 25, 25));
        leftSplashPanel = new JPanel();
        leftSplashPanel.setSize(650, 800);
        leftSplashPanel.setSize(1300, 800);
        leftSplashPanel.setVisible(true);
        leftSplashPanel.add(new JLabel(new ImageIcon((guardImage)))).setBounds(300, 100, 400, 600);



        rightSplashPanel = new JPanel();
        rightSplashPanel.setLayout(new BoxLayout(rightSplashPanel, BoxLayout.Y_AXIS));
        rightSplashPanel.setSize(1300, 800);
        rightSplashPanel.setVisible(true);
        title = new JLabel();
        title.setText("How To Play JailBreak");
        title.setFont(new Font("Serif", Font.BOLD, 35));

        titlePanel = new JPanel();
        titlePanel.setBackground(Color.white);
        titlePanel.add(title);

        mainSplashPanel.add(leftSplashPanel);
        mainSplashPanel.add(rightSplashPanel);
        rightSplashPanel.add(titlePanel);
        rightSplashPanel.add(new JTextArea("Eat moves inmate to Kitchen\n" +
                "Work moves inmate to Work Area\n" +
                "Sleep moves inmate to a Security Unit based upon their security rating."));

        rightSplashPanel.add(new JTextArea("Inmate number first digit designates their security rating\n" +
                "1xxx is low security\n" +
                "2xxx is medium security\n" +
                "3xxx is high security"));

        rightSplashPanel.add(new JTextArea("Gang leaders are designated with a different image"));

        rightSplashPanel.add(new JTextArea("Location Riot Status is composite score based on\n" +
                "-average inmate happiness for that location\n" +
                "  - happiness is raised or lowered based upon what you tell inmates to do\n" +
                "  - working lowers happiness, sleeping and eating increase happiness\n" +
                "-the capacity of a location\n" +
                "  - the more inmates you add to an area the greater chance of a riot\n" +
                "- number of gang leaders in that location\n" +
                "  - more gang leaders together increase your riot chance"));

        rightSplashPanel.add(new JTextArea("Prison Riot Status is an average rating for all locations\n" +
                "- If an individual location reaches 100% riot status,\n" +
                "  your Prison status will increase by 25% for each location above 100"));


        continueButton = new JButton("Continue");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.white);
        buttonPanel.add(continueButton);
        rightSplashPanel.add(buttonPanel);

        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loadGUI();
                    mainFrame.dispose();
                    System.out.println("test");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public JPanel getSplashPanel(){
        return mainSplashPanel;
    }

    private void loadGUI() throws IOException {
        // launch GUI
        MainAppPanel mainGui = new MainAppPanel(prison, LOW_SECURITY_UNIT, MEDIUM_SECURITY_UNIT, HIGH_SECURITY_UNIT, YARD, KITCHEN, WORK_AREA);


        // Update inmate counts in each area by passing Maps in this order
        //      [lowSecurityUnit, mediumSecurityUnit, highSecurityUnit, yard, kitchen]
        mainGui.updateCounts(LOW_SECURITY_UNIT.getInmateMap(),
                MEDIUM_SECURITY_UNIT.getInmateMap(),
                HIGH_SECURITY_UNIT.getInmateMap(),
                YARD.getInmateMap(),
                KITCHEN.getInmateMap(),
                WORK_AREA.getInmateMap());
    }
}