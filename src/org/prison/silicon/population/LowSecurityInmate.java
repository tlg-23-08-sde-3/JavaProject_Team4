package org.prison.silicon.population;

import org.prison.silicon.FacilityList;
import org.prison.silicon.SecurityRating;

import javax.swing.*;

class LowSecurityInmate extends Inmate{
    private FacilityList currentLocation;

    public LowSecurityInmate(int idNumber, String name, boolean gangLeader, SecurityRating securityRating){
        super(idNumber, name, gangLeader, securityRating);
        setCurrentLocation(FacilityList.LOW_SECURITY_UNIT);
    }

    @Override
    public void work() {
        // Attempt to move the inmate to the work area
        moveInmate(FacilityList.WORK_AREA);
    }

    @Override
    public void move(FacilityList location) {
        // Attempt to move the inmate to the passed location
        moveInmate(location);
    }

    @Override
    public void eat() {
        // move inmates from their current location to the kitchen
        moveInmate(FacilityList.KITCHEN);
    }

    @Override
    public void sleep() {
        // move inmates from their current location to their assigned security unit
        moveInmate(FacilityList.LOW_SECURITY_UNIT);
    }

    public FacilityList getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(FacilityList currentLocation) {
        this.currentLocation = currentLocation;
    }
    // Invalid move method that generates a pop-up window
    public void InvalidMove(FacilityList requestedLocation){
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