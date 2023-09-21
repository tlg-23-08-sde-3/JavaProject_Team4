package org.prison.silicon.population;

import org.prison.silicon.facility.Facility;
import org.prison.silicon.facility.Prison;
import org.prison.silicon.SecurityRating;

import static org.prison.silicon.facility.Facility.*;

public abstract class Inmate implements Comparable<Inmate> {
    private int idNumber;
    private String name;
    private boolean gangLeader;
    private SecurityRating securityRating;
    private int happiness;
    private Facility currentLocation;
    private Prison prison;

    public Inmate(int idNumber, String name, boolean gangLeader, SecurityRating securityRating, Prison prison) {
        setIdNumber(idNumber);
        setName(name);
        setGangLeader(gangLeader);
        setSecurityRating(securityRating);
        this.prison = prison;
    }

    // Business Abstract Methods - delegated to the subclasses
    public abstract void work();
    public abstract void sleep();

    @Override
    public int compareTo(Inmate other) {
        return this.getIdNumber() - other.getIdNumber();
    }

    // Business methods
    public void move(Facility location) {
        // Attempt to move the inmate to the passed location
        boolean successfulMove = prison.moveInmate(this, location);
        if (successfulMove) {
            this.setCurrentLocation(location);
        }
    }

    // Future functionality may have inmates eat() differently. This may be moved to the subclasses
    public void eat() {
        Facility location = KITCHEN;
        boolean successfulMove = this.getPrison().moveInmate(this, location);
        if (successfulMove) {
            setHappiness(getHappiness() + 5);
            this.setCurrentLocation(location);
        }
    }

    // Accessor Methods
    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGangLeader() {
        return gangLeader;
    }

    public void setGangLeader(boolean gangLeader) {
        this.gangLeader = gangLeader;
    }

    public SecurityRating getSecurityRating() {
        return securityRating;
    }

    public void setSecurityRating(SecurityRating securityRating) {
        this.securityRating = securityRating;
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public Facility getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Facility currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Prison getPrison() {
        return prison;
    }

    @Override
    public String toString() {
        String result = String.format("ID: %d, Name: %s, Current Location: %s, Security Rating: %s, Gang Leader: %s, Happiness: %d\n",
                this.getIdNumber(), this.getName(), this.getCurrentLocation().getName(), this.getSecurityRating().getDisplayName(), this.isGangLeader(), this.getHappiness());
        return result;
    }
}