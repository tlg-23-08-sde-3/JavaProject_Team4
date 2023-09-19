package org.prison.silicon.population;

import org.prison.silicon.FacilityList;
import org.prison.silicon.SecurityRating;

import javax.swing.*;

public class HighSecurityInmate extends Inmate{
    private FacilityList currentLocation;

    public HighSecurityInmate(int idNumber, String name, boolean gangLeader, SecurityRating securityRating){
        super(idNumber, name, gangLeader, securityRating);
        setCurrentLocation(FacilityList.HIGH_SECURITY_UNIT);
        setHappiness(30);
    }

    @Override
    public void work() {
        // move to workshop
        moveInmate(FacilityList.WORK_AREA);
        setHappiness(getHappiness() - 10);
    }

    @Override
    public void move(FacilityList location) {
        // move from current location to the passed ENUM location
        moveInmate(location);
    }

    @Override
    public void eat() {
        // move inmates from their current location to the kitchen
        moveInmate(FacilityList.KITCHEN);
        setHappiness(getHappiness() + 5);
    }

    @Override
    public void sleep() {
        // move inmates from their current location to the unit for sleep
        moveInmate(FacilityList.HIGH_SECURITY_UNIT);
        setHappiness(getHappiness() + 5);
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