package org.prison.silicon.population;

import org.prison.silicon.FacilityList;
import org.prison.silicon.SecurityRating;

import javax.swing.*;

public class MediumSecurityInmate extends Inmate{
    private FacilityList currentLocation;

    public MediumSecurityInmate(int idNumber, String name, boolean gangLeader, SecurityRating securityRating){
        super(idNumber, name, gangLeader, securityRating);
        setCurrentLocation(FacilityList.MEDIUM_SECURITY_UNIT);
        setHappiness(50);
    }

    @Override
    public void work() {
        // move to workshop
        moveInmate(FacilityList.WORK_AREA);
        setHappiness(getHappiness() - 9);
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
        // by default moved to the medium security unit since they are a medium security inmate
        moveInmate(FacilityList.MEDIUM_SECURITY_UNIT);
        setHappiness(getHappiness() + 5);
    }
}