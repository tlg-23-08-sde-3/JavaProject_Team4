package org.prison.silicon.population;

import org.prison.silicon.FacilityList;
import org.prison.silicon.SecurityRating;

import javax.swing.*;

public abstract class Inmate implements Comparable<Inmate> {
    private int idNumber;
    private String name;
    private boolean gangLeader;
    private SecurityRating securityRating;

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
}