package org.prison.silicon;

class Facility {
    private FacilityList name;
    private int maxCapacity;
    private SecurityRating securityRating;
    Map<Integer, Inmate> currentInmates;

    public Facility(FacilityList name, int maxCapacity, SecurityRating securityRating) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.securityRating = securityRating;
    }

    public FacilityList getName() {
        return name;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public SecurityRating getSecurityRating() {
        return securityRating;
    }

    public Map<Integer, Inmate> getCurrentInmates() {
        return currentInmates;
    }

    public void setCurrentInmates(Map<Integer, Inmate> currentInmates) {
        this.currentInmates = currentInmates;
    }
}