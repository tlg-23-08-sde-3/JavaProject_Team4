package org.prison.silicon.population;

import org.prison.silicon.facility.Facility;
import org.prison.silicon.facility.Prison;
import org.prison.silicon.SecurityRating;

import static org.prison.silicon.facility.Facility.*;

public class HighSecurityInmate extends Inmate {

    public HighSecurityInmate(int idNumber, String name, boolean gangLeader, SecurityRating securityRating, Prison prison) {
        super(idNumber, name, gangLeader, securityRating, prison);
        setCurrentLocation(HIGH_SECURITY_UNIT);
        setHappiness(30);
    }

    @Override
    public void work() {
        // move to workshop
        Facility location = WORK_AREA;
        boolean successfulMove = this.getPrison().moveInmate(this, location);
        if (successfulMove) {
            setHappiness(getHappiness() - 10);
            this.setCurrentLocation(location);
        }
    }

    @Override
    public void sleep() {
        // move inmates from their current location to the unit for sleep
        Facility location = HIGH_SECURITY_UNIT;
        boolean successfulMove = this.getPrison().moveInmate(this, location);
        if (successfulMove) {
            setHappiness(getHappiness() + 5);
            this.setCurrentLocation(location);
        }
    }
}