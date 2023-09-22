package org.prison.silicon.gui;


import org.prison.silicon.facility.Facility;
import org.prison.silicon.facility.Prison;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class UserInputPanel {
    private final ScorePanel scorePanel;
    private final JPanel userInputPanel;
    private final JComboBox actionSelectionMenu;
    // User action inmate field
    private final JTextField inmatefield;
    private final JComboBox facilitiesSelection;
    private final JLabel facilitiesSelectionLabel;
    private final Prison prison;
    private final FacilityAreaPanel lowSecurityUnitPanel;
    private final FacilityAreaPanel mediumSecurityUnitPanel;
    private final FacilityAreaPanel highSecurityUnitPanel;
    private final FacilityAreaPanel workAreaPanel;
    private final FacilityAreaPanel yardPanel;
    private final FacilityAreaPanel kitchenPanel;
    String[] facilities = {"LOW_SECURITY_UNIT", "MEDIUM_SECURITY_UNIT",
            "HIGH_SECURITY_UNIT", "KITCHEN", "YARD", "WORK_AREA"};


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
        // Main panel
        userInputPanel = new JPanel();
        userInputPanel.setLayout(new BoxLayout(userInputPanel, BoxLayout.Y_AXIS));
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.gray);
        userInputPanel.add(titlePanel);
        JLabel title = new JLabel();
        title.setText("Action Menu");
        title.setFont(new Font("Serif", Font.BOLD, 35));
        userInputPanel.setBorder(BorderFactory.createLineBorder(Color.black, 5, false));
        userInputPanel.setBackground(Color.gray);

        // Action selection menu label
        // User action labels
        JLabel actionMenuLabel = new JLabel("Action Selection:");

        // User action selection
        String[] actions = {"Move", "Eat", "Sleep", "Work"};
        actionSelectionMenu = new JComboBox(actions);

        JLabel inmateFieldLabel = new JLabel("Inmate Id:");

        inmatefield = new JFormattedTextField();

        // User action buttons
        JButton moveButton = new JButton("Move Inmate");

        facilitiesSelectionLabel = new JLabel("Move to:");

        facilitiesSelection = new JComboBox(facilities);

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

        // Action listener for the move button
        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveInmate();
            }
        });

        inmatefield.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveInmate();
            }
        });

        JPanel mainActionMenuPanel = new JPanel();
        mainActionMenuPanel.setBackground(Color.gray);
        mainActionMenuPanel.setLayout(new GridLayout(5, 2));

        // add all the components to the userInputPanel
        titlePanel.add(title);
        userInputPanel.add(mainActionMenuPanel);
        mainActionMenuPanel.add(actionMenuLabel);
        mainActionMenuPanel.add(actionSelectionMenu);
        mainActionMenuPanel.add(facilitiesSelectionLabel);
        mainActionMenuPanel.add(facilitiesSelection);
        mainActionMenuPanel.add(inmateFieldLabel);
        mainActionMenuPanel.add(inmatefield);
        mainActionMenuPanel.add(moveButton);
    }

    private void moveInmate(){
        String selectedAction = (String) actionSelectionMenu.getSelectedItem();
        String selectedFacility = (String) facilitiesSelection.getSelectedItem();
        int inmateId = Integer.parseInt(inmatefield.getText());
        switch (selectedAction) {
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
        inmatefield.setText("");
        scorePanel.paintProgressBars();
        lowSecurityUnitPanel.paintInmates();
        mediumSecurityUnitPanel.paintInmates();
        highSecurityUnitPanel.paintInmates();
        yardPanel.paintInmates();
        kitchenPanel.paintInmates();
        workAreaPanel.paintInmates();
    }

    public JPanel getUserInputPanel() {
        return userInputPanel;

    }
}