package org.prison.silicon.population;

import org.prison.silicon.FacilityList;
import org.prison.silicon.SecurityRating;

import javax.swing.*;

public class LowSecurityInmate extends Inmate{
    private FacilityList currentLocation;

    public LowSecurityInmate(int idNumber, String name, boolean gangLeader, SecurityRating securityRating){
        super(idNumber, name, gangLeader, securityRating);
        setCurrentLocation(FacilityList.LOW_SECURITY_UNIT);
        setHappiness(70);
    }

    @Override
    public void work() {
        // Attempt to move the inmate to the work area
        moveInmate(FacilityList.WORK_AREA);
        setHappiness(getHappiness() -8);
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
        setHappiness(getHappiness() + 5);
    }

    @Override
    public void sleep() {
        // move inmates from their current location to their assigned security unit
        moveInmate(FacilityList.LOW_SECURITY_UNIT);
        setHappiness(getHappiness() + 5);
    }
}