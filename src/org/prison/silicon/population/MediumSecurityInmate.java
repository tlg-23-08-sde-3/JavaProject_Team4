package org.prison.silicon.population;

import org.prison.silicon.FacilityList;
import org.prison.silicon.SecurityRating;

import java.time.LocalDate;

class MediumSecurityInmate extends Inmate{

    public MediumSecurityInmate(int idNumber, String name, boolean gangLeader, SecurityRating securityRating){
        super(idNumber, name, gangLeader, securityRating);
    }

    @Override
    public void work() {
        // move to workshop
    }

    @Override
    public void move(FacilityList location) {
        // move from current location to the passed ENUM location
    }

    @Override
    public void eat() {
        // move inmates from their current location to the kitchen
    }

    @Override
    public void sleep() {
        // move inmates from their current location to the unit for sleep
    }
}