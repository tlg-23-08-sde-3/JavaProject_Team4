package org.prison.silicon;

import org.prison.silicon.population.Inmate;

import java.util.Map;
import java.util.TreeMap;

class Facility {
    private final FacilityList name;
    private final int maxCapacity;
    private final SecurityRating securityRating;
    Map<Integer, Inmate> currentInmates;
    int riskRating;

    public Facility(FacilityList name, int maxCapacity, SecurityRating securityRating) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.securityRating = securityRating;
        this.currentInmates = new TreeMap<>();
    }

    // Business methods
    public void addInmate(Inmate inmate, Inmate... inmates) {
        currentInmates.put(inmate.getIdNumber(), inmate);
        for (Inmate in : inmates) {
            currentInmates.put(in.getIdNumber(), in);
        }
    }

    public void removeInmate(int id, int... ids) {
        currentInmates.remove(id);
        for (int i : ids) {
            currentInmates.remove(i);
        }
    }

    public  void calculateRiskRating() {
        // TODO: Add happiness value to inmates. This will average the happiness of the current inmates
        int avgHappiness = 50;
        int ratingAdjHappiness = (100 - avgHappiness) / 2;
        // Facility being at max capacity will increase riskRating by 50
        int ratingAdjCapacity = (currentInmates.size() / getMaxCapacity()) * 50;
        // Add a certain amount for each gangLeader
        int ratingAdjGang =
                (int) currentInmates.values().stream().filter(Inmate::isGangLeader).count() * 25;
        this.riskRating = ratingAdjHappiness + ratingAdjCapacity + ratingAdjHappiness;
    }

    public void displayCurrentInmates() {
        for (Inmate inmate : getInmateMap().values()) {
            System.out.printf("ID: %d, Name: %s, Security Rating: %s, Gang Leader: %s, Happiness: int\n",
                    inmate.getIdNumber(), inmate.getName(), inmate.getSecurityRating().getDisplayName(), inmate.isGangLeader());
        }
    }

    // Getters
    public FacilityList getName() {
        return name;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public SecurityRating getSecurityRating() {
        return securityRating;
    }

    public  int getCount() {
        return currentInmates.size();
    }

    public Integer[] getInmatesIds() {
        return currentInmates.keySet().toArray(new Integer[0]);
    }

    public Map<Integer, Inmate> getInmateMap() {
        return currentInmates;
    }

    public int getRiskRating() {
        return riskRating;
    }
}