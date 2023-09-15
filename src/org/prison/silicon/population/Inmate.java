package org.prison.silicon.population;

import org.prison.silicon.FacilityList;
import org.prison.silicon.SecurityRating;

import java.time.LocalDate;

public abstract class Inmate {
    private int idNumber;
    private String name;
    private boolean gangLeader;
    private SecurityRating securityRating; // created in the org.prison.silicon package by Chris

    // private no-args constructor
    private Inmate(){};

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
}