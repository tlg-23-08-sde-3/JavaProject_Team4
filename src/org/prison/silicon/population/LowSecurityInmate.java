package org.prison.silicon.population;

import org.prison.silicon.FacilityList;
import org.prison.silicon.Prison;
import org.prison.silicon.SecurityRating;

public class LowSecurityInmate extends Inmate{
    private FacilityList currentLocation;

    public LowSecurityInmate(int idNumber, String name, boolean gangLeader, SecurityRating securityRating, Prison prison){
        super(idNumber, name, gangLeader, securityRating, prison);
        setCurrentLocation(FacilityList.LOW_SECURITY_UNIT);
        setHappiness(70);
    }

    @Override
    public void work() {
        // Attempt to move the inmate to the work area
        FacilityList location = FacilityList.WORK_AREA;
        boolean successfulMove = this.getPrison().moveInmate(this, location);
        if (successfulMove) {
            setHappiness(getHappiness() - 8);
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
        // move inmates from their current location to their assigned security unit
        FacilityList location = FacilityList.LOW_SECURITY_UNIT;
        boolean successfulMove = this.getPrison().moveInmate(this, location);
        if (successfulMove) {
            setHappiness(getHappiness() + 5);
            this.setCurrentLocation(location);
        }
    }
}