package org.prison.silicon.population;

import org.prison.silicon.FacilityList;
import org.prison.silicon.SecurityRating;

import javax.swing.*;

public class MediumSecurityInmate extends Inmate{
    private FacilityList currentLocation;
    private final int defaultMediumSecurityHappiness = 50;

    public MediumSecurityInmate(int idNumber, String name, boolean gangLeader, SecurityRating securityRating){
        super(idNumber, name, gangLeader, securityRating);
        setHappiness(defaultMediumSecurityHappiness);
        setCurrentLocation(FacilityList.MEDIUM_SECURITY_UNIT);
    }

    @Override
    public void work() {
        // move to workshop
        setHappiness(getHappiness() -8);
        moveInmate(FacilityList.WORK_AREA);
    }

    @Override
    public void move(FacilityList location) {
        // move from current location to the passed ENUM location
        moveInmate(location);
    }

    @Override
    public void eat() {
        // move inmates from their current location to the kitchen
        setHappiness(getHappiness() + 5);
        moveInmate(FacilityList.KITCHEN);
    }

    @Override
    public void sleep() {
        // move inmates from their current location to the unit for sleep
        // by default moved to the medium security unit since they are a medium security inmate
        setHappiness(getHappiness() + 5);
        moveInmate(FacilityList.MEDIUM_SECURITY_UNIT);
    }

    // Invalid move method that generates a pop-up window
    private void InvalidMove(FacilityList requestedLocation){
        String message = "Inmate " + getIdNumber() + " is already in the " + this.getCurrentLocation() +
                "\narea. Please select a different location.";
        String title = "Invalid Move To " + requestedLocation;

        JOptionPane.showMessageDialog(null,
                message,
                title,
                JOptionPane.WARNING_MESSAGE);
    }

    // Move the inmate to a new location
    private void moveInmate(FacilityList newLocation){
        try{
            if(!this.getCurrentLocation().equals(newLocation)){
                // assign the inmate to the new location
                setCurrentLocation(newLocation);
                System.out.printf("Inmate %s has been moved to %s", getIdNumber(), getCurrentLocation());
            } else {
                throw new IllegalArgumentException("Can't move an inmate to a location they are already in.");
            }
        } catch (IllegalArgumentException e) {
            InvalidMove(newLocation);
        }
    }
}