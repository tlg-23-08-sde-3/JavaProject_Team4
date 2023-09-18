package org.prison.silicon.population;

import org.prison.silicon.FacilityList;
import org.prison.silicon.SecurityRating;

public abstract class Inmate implements Comparable<Inmate> {
    private int idNumber;
    private String name;
    private boolean gangLeader;
    private SecurityRating securityRating;
    private int happiness;
    private FacilityList currentLocation;

    public Inmate(int idNumber, String name, boolean gangLeader, SecurityRating securityRating) {
        setIdNumber(idNumber);
        setName(name);
        setGangLeader(gangLeader);
        setSecurityRating(securityRating);
    }

    // Business Abstract Methods - delegated to the subclasses
    public abstract void work();

    public abstract void move(FacilityList location);

    public abstract void eat();

    public abstract void sleep();

    @Override
    public int compareTo(Inmate other){
        return this.getIdNumber() - other.getIdNumber();
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

    public FacilityList getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(FacilityList currentLocation) {
        this.currentLocation = currentLocation;
    }

    @Override
    public String toString() {
        String result = String.format("ID: %d, Name: %s, Current Location: %s, Security Rating: %s, Gang Leader: %s, Happiness: %d\n",
                this.getIdNumber(), this.getName(), this.getCurrentLocation().getDisplayName(), this.getSecurityRating().getDisplayName(), this.isGangLeader(), this.getHappiness());
        return result;
    }
}