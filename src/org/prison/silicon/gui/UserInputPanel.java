package org.prison.silicon.gui;


import org.prison.silicon.facility.Facility;
import org.prison.silicon.facility.Prison;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class UserInputPanel {

    // Instance variables
    private final JPanel mainActionMenuPanel;
    private final ScorePanel scorePanel;
    private final JPanel userInputPanel;
    private JComboBox actionSelectionMenu;
    private final JTextField inmateField;
    private JComboBox facilitiesSelection;
    private final JLabel facilitiesSelectionLabel;
    private final Prison prison;
    private final FacilityAreaPanel lowSecurityUnitPanel;
    private final FacilityAreaPanel mediumSecurityUnitPanel;
    private final FacilityAreaPanel highSecurityUnitPanel;
    private final FacilityAreaPanel workAreaPanel;
    private final FacilityAreaPanel yardPanel;
    private final FacilityAreaPanel kitchenPanel;
    private final JButton moveButton;
    private final JPanel titlePanel;
    private final JLabel title;
    private final JLabel inmateFieldLabel;
    private final JLabel actionMenuLabel;
    String[] facilities = {"LOW_SECURITY_UNIT", "MEDIUM_SECURITY_UNIT",
            "HIGH_SECURITY_UNIT", "KITCHEN", "YARD", "WORK_AREA"};

    // Constructor
    public UserInputPanel(Prison prison, ScorePanel scorePanel, FacilityAreaPanel lowSecurityUnitPanel, FacilityAreaPanel mediumSecurityUnitPanel,
                          FacilityAreaPanel highSecurityUnitPanel, FacilityAreaPanel yardPanel, FacilityAreaPanel kitchenPanel, FacilityAreaPanel workAreaPanel) {

        this.prison = prison;
        this.lowSecurityUnitPanel = lowSecurityUnitPanel;
        this.mediumSecurityUnitPanel = mediumSecurityUnitPanel;
        this.highSecurityUnitPanel = highSecurityUnitPanel;
        this.yardPanel = yardPanel;
        this.kitchenPanel = kitchenPanel;
        this.workAreaPanel = workAreaPanel;
        this.scorePanel = scorePanel;
        mainActionMenuPanel = new JPanel();
        userInputPanel = new JPanel();
        titlePanel = new JPanel();
        title = new JLabel();
        inmateField = new JFormattedTextField();
        facilitiesSelectionLabel = new JLabel("Move to:");
        moveButton = new JButton("Move Inmate");
        inmateFieldLabel = new JLabel("Inmate Id:");
        actionMenuLabel = new JLabel("Action Selection:");


        userInputPanelSetup();
        titleSetup();
        dropDownMenus();
        actionListener();
        mainActionMenuPanelSetup();
        addPanels();
    }

    // private method used to setup the drop down menu
    private void dropDownMenus(){
        final String[] actions = {"Move", "Eat", "Sleep", "Work"};
        actionSelectionMenu = new JComboBox(actions);
        facilitiesSelection = new JComboBox(facilities);
    }

    // private method used to setup the title
    private void titleSetup(){
        titlePanel.setBackground(Color.gray);
        title.setText("Action Menu");
        title.setFont(new Font("Serif", Font.BOLD, 35));
    }

    // private method used to setup the userInputPanel
    private void userInputPanelSetup(){
        userInputPanel.setLayout(new BoxLayout(userInputPanel, BoxLayout.Y_AXIS));
        userInputPanel.setBorder(BorderFactory.createLineBorder(Color.black, 5, false));
        userInputPanel.setBackground(Color.gray);
    }

    // private method used to setup the mainActionMenuPanel
    private void mainActionMenuPanelSetup(){
        mainActionMenuPanel.setBackground(Color.gray);
        mainActionMenuPanel.setLayout(new GridLayout(5, 2));
    }

    // private method used to add all the panels to the mainActionMenuPanel
    private void addPanels(){
        titlePanel.add(title);
        userInputPanel.add(titlePanel);
        userInputPanel.add(mainActionMenuPanel);
        mainActionMenuPanel.add(actionMenuLabel);
        mainActionMenuPanel.add(actionSelectionMenu);
        mainActionMenuPanel.add(facilitiesSelectionLabel);
        mainActionMenuPanel.add(facilitiesSelection);
        mainActionMenuPanel.add(inmateFieldLabel);
        mainActionMenuPanel.add(inmateField);
        mainActionMenuPanel.add(moveButton);
    }

    // moveButton, inmateField, and drop down menu action listeners
    private void actionListener(){
        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveInmate();
            }
        });

        inmateField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveInmate();
            }
        });

        actionSelectionMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Objects.equals(actionSelectionMenu.getSelectedItem(), "Move")) {
                    facilitiesSelectionLabel.setVisible(false);
                    facilitiesSelection.setVisible(false);
                } else {
                    facilitiesSelectionLabel.setVisible(true);
                    facilitiesSelection.setVisible(true);
                }
            }
        });
    }

    // private method used to move inmates
    private void moveInmate(){
        boolean riotStatus;
        String selectedAction = (String) actionSelectionMenu.getSelectedItem();
        String selectedFacility = (String) facilitiesSelection.getSelectedItem();
        int inmateId = Integer.parseInt(inmateField.getText());
        switch (Objects.requireNonNull(selectedAction)) {
            case "Eat":
                prison.locateInmateByID(inmateId).eat();
                break;
            case "Sleep":
                prison.locateInmateByID(inmateId).sleep();
                break;
            case "Work":
                prison.locateInmateByID(inmateId).work();
                break;
            case "Move":
                prison.locateInmateByID(inmateId).move(Facility.valueOf(selectedFacility));
                break;
        }
        if(prison.getRiskRating() >= 100){
            riotStatus = true;
        } else {
            riotStatus = false;
        }
        inmateField.setText("");
        scorePanel.paintProgressBars();
        lowSecurityUnitPanel.paintInmates(riotStatus);
        mediumSecurityUnitPanel.paintInmates(riotStatus);
        highSecurityUnitPanel.paintInmates(riotStatus);
        yardPanel.paintInmates(riotStatus);
        kitchenPanel.paintInmates(riotStatus);
        workAreaPanel.paintInmates(riotStatus);
    }

    // userInputPanel getter
    public JPanel getUserInputPanel() {
        return userInputPanel;

    }
}