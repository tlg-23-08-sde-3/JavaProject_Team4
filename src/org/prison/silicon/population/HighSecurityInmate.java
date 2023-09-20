package org.prison.silicon.population;

import org.prison.silicon.facility.FacilityList;
import org.prison.silicon.facility.Prison;
import org.prison.silicon.SecurityRating;

public class HighSecurityInmate extends Inmate{
    private FacilityList currentLocation;
    private Prison prison;

    public HighSecurityInmate(int idNumber, String name, boolean gangLeader, SecurityRating securityRating, Prison prison){
        super(idNumber, name, gangLeader, securityRating, prison);
        setCurrentLocation(FacilityList.HIGH_SECURITY_UNIT);
        setHappiness(30);
    }

    @Override
    public void work() {
        // move to workshop
        FacilityList location = FacilityList.WORK_AREA;
        boolean successfulMove = this.getPrison().moveInmate(this, location);
        if (successfulMove) {
            setHappiness(getHappiness() - 10);
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
        FacilityList location = FacilityList.HIGH_SECURITY_UNIT;
        boolean successfulMove = this.getPrison().moveInmate(this, location);
        if (successfulMove) {
            setHappiness(getHappiness() + 5);
            this.setCurrentLocation(location);
        }
    }
}