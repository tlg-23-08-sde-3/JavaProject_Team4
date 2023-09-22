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

    // Instance variables
    private final JFrame mainFrame;
    private final Prison prison;
    private final JPanel mainSplashPanel;
    private final JPanel leftSplashPanel;
    private final JPanel rightSplashPanel;
    private final JLabel title;
    private final JPanel titlePanel;
    private final JPanel buttonPanel;
    private final JButton continueButton;

    // Images
    private BufferedImage guard;

    {
        try {
            guard = ImageIO.read(new File("resources/images/guard.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Instance variables
    private final Image guardImage = guard.getScaledInstance(400, 600, Image.SCALE_DEFAULT);


    // Constructors
    public SplashPanel(Prison prison, JFrame mainFrame) {

        this.prison = prison;
        this.mainFrame = mainFrame;
        mainSplashPanel = new JPanel();
        leftSplashPanel = new JPanel();
        rightSplashPanel = new JPanel();
        title = new JLabel();
        titlePanel = new JPanel();
        buttonPanel = new JPanel();
        continueButton = new JButton("Continue");

        mainSplashPanelSetup();
        LeftSplashPanelSetup();
        RightSplashPanelSetup();
        titleSetup();
        addPanels();
        mainTextBody();
        buttonSetup();


        // Focus on the continueButton when created
        mainFrame.getRootPane().setDefaultButton(continueButton);

        // Action listener
        continueButtonActionListener();
    }

    // continueButton action listener
    public void continueButtonActionListener(){
        // continueButton action listener that loads the GUI once the continue button is clicked
        //      and closes the splash page / rules page
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mainGui();
                    mainFrame.dispose();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    // private method used to setup the mainSplashPanel
    private void mainSplashPanelSetup(){
        // mainPanel setup
        mainSplashPanel.setBackground(Color.white);
        mainSplashPanel.setLayout(new GridLayout(1, 2, 25, 25));
    }

    // private method used to setup the leftSplashPanel
    private void LeftSplashPanelSetup(){
        leftSplashPanel.setSize(650, 800);
        leftSplashPanel.setSize(1300, 800);
        leftSplashPanel.setVisible(true);
        leftSplashPanel.add(new JLabel(new ImageIcon((guardImage))));
    }

    // private method used to setup the rightSplashPanel
    private void RightSplashPanelSetup(){
        rightSplashPanel.setLayout(new BoxLayout(rightSplashPanel, BoxLayout.Y_AXIS));
        rightSplashPanel.setSize(1300, 800);
        rightSplashPanel.setVisible(true);
    }

    // private method used to setup the title
    private void titleSetup(){
        title.setText("How To Play JailBreak");
        title.setFont(new Font("Serif", Font.BOLD, 35));
        titlePanel.setBackground(Color.white);
        titlePanel.add(title);
    }

    // private method used to setup the button
    private void buttonSetup(){
        buttonPanel.setBackground(Color.white);
        buttonPanel.add(continueButton);
        rightSplashPanel.add(buttonPanel);
    }

    // private method used to add the panels to the mainSplashPanel
    private void addPanels(){
        mainSplashPanel.add(leftSplashPanel);
        mainSplashPanel.add(rightSplashPanel);
        rightSplashPanel.add(titlePanel);
    }

    // Splash page getter
    public JPanel getSplashPanel(){
        return mainSplashPanel;
    }

    // Loads the main app GUI
    private void mainGui() throws IOException {
        // Create GUI
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

    // private method used to display the main text body for the splash page
    private void mainTextBody(){
        rightSplashPanel.add(new JTextArea("Welcome to JailBreak. Try to last an entire round without causing a riot.\n" +
                "As you move inmates around their happiness will decrease and their irritation will increase raising\n" +
                "the chance of starting a riot. Normal inmates are dressed in striped uniforms while gang leaders\n" +
                " wear sunglasses and get irritated easier than normal inmates.\n"));

        rightSplashPanel.add(new JTextArea("Location Riot Status is a composite score based on:\n" +
                "   - The average inmate happiness for that location\n" +
                "   - The happiness is raised or lowered based on where you move inmates or have them do:\n" +
                "         - Sleep increases the inmates happiness\n" +
                "         - Eat increases the inmates happiness\n" +
                "         - Work decreases the inmates happiness\n" +
                "         - Over crowding an area decreases the inmates happiness\n" +
                "   - The more inmates you add to an area the greater chance of a riot\n" +
                "   - The number of gang leaders in that location increases a chance of a riot\n"));

        rightSplashPanel.add(new JTextArea("The \"Prison Riot Status\" displays the average rating for each location:\n" +
                "   - Each location has a riot status bar\n" +
                "   - The \"Overall Status\" bar displays the overall prison's riot status\n" +
                "   - If an individual location reaches 100% riot status, your Prison status will increase by 25%\n" +
                "      for each location above 100\n"));

        rightSplashPanel.add(new JTextArea("Inmate number first digit designates their security rating and color\n" +
                "   - 1xxx is low security inmate with a green ID label\n" +
                "   - 2xxx is medium security inmate with an orange ID label\n" +
                "   - 3xxx is high security inmate with a red ID label\n" +
                "   - Note: Moving an inmate to a security unit that is lower than their security rating will increase your riot chance!"));

        rightSplashPanel.add(new JTextArea("Inmates are also dressed differently.\n" +
                "   - Normal inmates are dressed in a striped uniform and aren't irritated as easy\n" +
                "   - Gang leaders are dressed in a jacket, pants, and have sunglasses\n" +
                "         - Keep a close eye on where inmates are placed gang leaders have a higher riot chance.\n"));

        rightSplashPanel.add(new JTextArea("Calling action such as Eat, Sleep or Work move the inmates to the following locations:\n" +
                "   - Eat moves the inmate to the Kitchen by default\n" +
                "   - Sleep will return the inmate to their assigned security unit\n" +
                "   - Work moves the inmate to Work Area\n" +
                "   - When the Move action is called on an inmate you will have the following locations to pick from:\n" +
                "      - Low Security Unit\n" +
                "      - Medium Security Unit\n" +
                "      - High Security Unit\n" +
                "      - Yard\n" +
                "      - Kitchen\n" +
                "      - Work Area\n"));
    }
}