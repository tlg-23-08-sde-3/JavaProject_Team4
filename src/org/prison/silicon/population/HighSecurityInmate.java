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
}