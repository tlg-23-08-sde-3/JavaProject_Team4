package org.prison.silicon.population;

import org.prison.silicon.facility.Facility;
import org.prison.silicon.facility.Prison;
import org.prison.silicon.SecurityRating;

import static org.prison.silicon.facility.Facility.*;

public class LowSecurityInmate extends Inmate {
    private Facility currentLocation;

    public LowSecurityInmate(int idNumber, String name, boolean gangLeader, SecurityRating securityRating, Prison prison) {
        super(idNumber, name, gangLeader, securityRating, prison);
        setCurrentLocation(LOW_SECURITY_UNIT);
        setHappiness(70);
    }

    @Override
    public void work() {
        // Attempt to move the inmate to the work area
        Facility location = WORK_AREA;
        boolean successfulMove = this.getPrison().moveInmate(this, location);
        if (successfulMove) {
            setHappiness(getHappiness() - 8);
            this.setCurrentLocation(location);
        }
    }

    @Override
    public void eat() {
        // move inmates from their current location to the kitchen
        Facility location = KITCHEN;
        boolean successfulMove = this.getPrison().moveInmate(this, location);
        if (successfulMove) {
            setHappiness(getHappiness() + 5);
            this.setCurrentLocation(location);
        }
    }

    @Override
    public void sleep() {
        // move inmates from their current location to their assigned security unit
        Facility location = LOW_SECURITY_UNIT;
        boolean successfulMove = this.getPrison().moveInmate(this, location);
        if (successfulMove) {
            setHappiness(getHappiness() + 5);
            this.setCurrentLocation(location);
        }
    }
}