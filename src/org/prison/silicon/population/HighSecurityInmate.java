package org.prison.silicon.population;

import org.prison.silicon.FacilityList;
import org.prison.silicon.SecurityRating;

import javax.swing.*;

public class HighSecurityInmate extends Inmate{
    private FacilityList currentLocation;
    private final int defaultHighSecurityHappiness = 30;

    public HighSecurityInmate(int idNumber, String name, boolean gangLeader, SecurityRating securityRating){
        super(idNumber, name, gangLeader, securityRating);
        setHappiness(defaultHighSecurityHappiness);
        setCurrentLocation(FacilityList.HIGH_SECURITY_UNIT);
    }

    @Override
    public void work() {
        // move to workshop
        setHappiness(getHappiness() - 10);
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
        setHappiness(getHappiness() + 5);
        // move inmates from their current location to the unit for sleep
        moveInmate(FacilityList.HIGH_SECURITY_UNIT);
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
            } else if(newLocation.equals(FacilityList.KITCHEN)) {
                System.out.printf("A High security inmate cant go to the kitchen so will food will be delivered to their ceil.");
                // TODO: make sure to adjust the "happiness" score
            } else {
                throw new IllegalArgumentException("Can't move an inmate to a location they are already in.");
            }
        } catch (IllegalArgumentException e) {
            InvalidMove(newLocation);
        }
    }
}