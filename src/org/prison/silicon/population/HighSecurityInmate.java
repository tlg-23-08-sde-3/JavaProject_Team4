package org.prison.silicon.population;

import java.time.LocalDate;

class HighSecurityInmate extends Inmate{

    public HighSecurityInmate(int idNumber, String name, LocalDate sentenceLength, boolean gangLeader, SecurityRating securityRating){
        super(idNumber, name, sentenceLength, gangLeader, securityRating);
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