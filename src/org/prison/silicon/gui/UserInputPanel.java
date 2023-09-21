package org.prison.silicon.gui;


import org.prison.silicon.facility.Facility;
import org.prison.silicon.facility.FacilityList;
import org.prison.silicon.facility.Prison;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInputPanel {
    private final ScorePanel scorePanel;
    private final JPanel userInputPanel;
    private final JLabel title;
    // User action labels
    private final JLabel actionMenuLabel;
    private final JLabel inmateFieldLabel;
    private final JPanel titlePanel;
    // User action selection
    private final String[] actions = {"Move", "Eat", "Sleep", "Work"};
    private final JComboBox actionSelectionMenu;
    // User action inmate field
    private final JTextField inmatefield;
    // User action buttons
    private final JButton moveButton;
    private final JComboBox facilitiesSelection;
    private final JLabel facilitiesSelectionLabel;
    String[] facilities = {"LOW_SECURITY_UNIT", "MEDIUM_SECURITY_UNIT",
            "HIGH_SECURITY_UNIT", "KITCHEN", "YARD", "WORK_AREA"};


    public UserInputPanel(Prison prison, ScorePanel scorePanel, LowSecurityUnitPanel lowSecurityUnitpanel, MediumSecurityUnitPanel mediumSecurityUnitPanel,
                          HighSecurityUnitPanel highSecurityUnitPanel, YardPanel yardPanel, KitchenPanel kitchenPanel, WorkAreaPanel workAreaPanel) {

        this.scorePanel = scorePanel;
        // Main panel
        userInputPanel = new JPanel();
        userInputPanel.setLayout(new BoxLayout(userInputPanel, BoxLayout.Y_AXIS));
        titlePanel = new JPanel();
        titlePanel.setBackground(Color.gray);
        userInputPanel.add(titlePanel);
        title = new JLabel();
        title.setText("Action Menu");
        title.setFont(new Font("Serif", Font.BOLD, 35));
        userInputPanel.setBorder(BorderFactory.createLineBorder(Color.black, 5, false));
        userInputPanel.setBackground(Color.gray);

        // Action selection menu label
        actionMenuLabel = new JLabel("Action Selection:");

        actionSelectionMenu = new JComboBox(actions);

        inmateFieldLabel = new JLabel("Inmate Id:");

        inmatefield = new JFormattedTextField();

        moveButton = new JButton("Move Inmate");

        facilitiesSelectionLabel = new JLabel("Move to:");

        facilitiesSelection = new JComboBox(facilities);

        actionSelectionMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!actionSelectionMenu.getSelectedItem().equals("Move")) {
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
                        prison.locateInmateByID(inmateId).move(FacilityList.valueOf(selectedFacility));
                        break;
                }
                System.out.println(prison.locateInmateByID(inmateId));
                inmatefield.setText("");
                scorePanel.paintProgressBars();
                lowSecurityUnitpanel.paintInmates();
                mediumSecurityUnitPanel.paintInmates();
                highSecurityUnitPanel.paintInmates();
                yardPanel.paintInmates();
                kitchenPanel.paintInmates();
                workAreaPanel.paintInmates();
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

    public JPanel getUserInputPanel() {
        return userInputPanel;

    }
}