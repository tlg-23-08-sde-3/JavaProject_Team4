package org.prison.silicon.population;

import org.prison.silicon.facility.FacilityList;
import org.prison.silicon.facility.Prison;
import org.prison.silicon.SecurityRating;

public class MediumSecurityInmate extends Inmate{
    private FacilityList currentLocation;

    public MediumSecurityInmate(int idNumber, String name, boolean gangLeader, SecurityRating securityRating, Prison prison){
        super(idNumber, name, gangLeader, securityRating, prison);
        setCurrentLocation(FacilityList.MEDIUM_SECURITY_UNIT);
        setHappiness(50);
    }

    @Override
    public void work() {
        // Attempt to move the inmate to the work area
        FacilityList location = FacilityList.WORK_AREA;
        boolean successfulMove = this.getPrison().moveInmate(this, location);
        if (successfulMove) {
            setHappiness(getHappiness() - 9);
            this.setCurrentLocation(location);
        }
    }

    @Override
    public void eat() {
        // move inmates from their current location to the kitchen
        FacilityList location = FacilityList.KITCHEN;
        boolean successfulMove = this.getPrison().moveInmate(this, location);
        if (successfulMove) {
            setHappiness(getHappiness() + 5);
            this.setCurrentLocation(location);
        }
    }

    @Override
    public void sleep() {
        // move inmates from their current location to the unit for sleep
        // by default moved to the medium security unit since they are a medium security inmate
        FacilityList location = FacilityList.MEDIUM_SECURITY_UNIT;
        boolean successfulMove = this.getPrison().moveInmate(this, location);
        if (successfulMove) {
            setHappiness(getHappiness() + 5);
            this.setCurrentLocation(location);
        }
    }
}