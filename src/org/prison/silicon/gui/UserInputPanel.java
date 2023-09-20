package org.prison.silicon.gui;

import org.prison.silicon.Prison;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInputPanel {
    private final JPanel userInputPanel;
    private final JLabel title;
    // User action labels
    private final JLabel actionMenuLabel;
    private final JLabel inmateFieldLabel;
    // User action selection
    private final String[] actions = {"Move", "Eat", "Sleep", "Work"};
    private final JComboBox actionSelectionMenu;
    // User action inmate field
    private final JTextField inmatefield;
    // User action buttons
    private final JButton moveButton;

    String[] facilities = {"Low Security Area", "Medium Security Area",
            "High Security Area", "Kitchen", "Yard", "Work Area"};
    private final JComboBox facilitiesSelection;
    private final JLabel facilitiesSelectionLabel;


    public UserInputPanel(Prison prison) {

        // Main panel
        userInputPanel = new JPanel();
        userInputPanel.setLayout(null);
        title = new JLabel();
        title.setText("Action Menu");
        title.setFont(new Font("Serif", Font.BOLD, 35));
        title.setBounds(60, 5, 300, 40);
        userInputPanel.add(title);
        userInputPanel.setBorder(BorderFactory.createLineBorder(Color.black, 5, false));
        userInputPanel.setBackground(Color.gray);

        // Action selection menu label
        actionMenuLabel = new JLabel("Action Selection: ");
        actionMenuLabel.setBounds(10, 55, 125, 25);
        // Action selection menu
        actionSelectionMenu = new JComboBox(actions);
        actionSelectionMenu.setBounds(120, 18, 100, 100);
        // Inmate input text field label
        inmateFieldLabel = new JLabel("Inmate Id: ");
        inmateFieldLabel.setBounds(10, 200, 120, 25);
        // Inmate input text field
        inmatefield = new JFormattedTextField();
        inmatefield.setBounds(80, 200, 50, 25);
        // Move inmate button (submit button)
        moveButton = new JButton("Move Inmate");
        moveButton.setBounds(10, 250, 130, 25);

        // Facilities seleciton menu label
        facilitiesSelectionLabel = new JLabel("Move to: ");
        facilitiesSelectionLabel.setBounds(10, 113, 75, 25);
        // Facilities selection menu
        facilitiesSelection = new JComboBox(facilities);
        facilitiesSelection.setBounds(80, 75, 200, 100);

        // Action listener for the facilities seleciton box
        actionSelectionMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!actionSelectionMenu.getSelectedItem().equals("Move")) {
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
                System.out.println(selectedAction + " - " + selectedFacility + " - " + inmateId);
            }
        });

        // add all the components to the userInputPanel
        userInputPanel.add(actionMenuLabel);
        userInputPanel.add(actionSelectionMenu);
        userInputPanel.add(facilitiesSelectionLabel);
        userInputPanel.add(facilitiesSelection);
        userInputPanel.add(inmateFieldLabel);
        userInputPanel.add(inmatefield);
        userInputPanel.add(moveButton);

        System.out.println("from user input: ");
        System.out.println(prison.getCount());
    }

    public JPanel getUserInputPanel() {
        return userInputPanel;

    }
}