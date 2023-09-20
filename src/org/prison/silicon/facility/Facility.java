package org.prison.silicon.facility;

import org.prison.silicon.SecurityRating;
import org.prison.silicon.population.Inmate;

import java.util.Map;
import java.util.TreeMap;

public class Facility {
    private final FacilityList name;
    private final int maxCapacity;
    private final SecurityRating securityRating;
    Map<Integer, Inmate> currentInmates;
    int riskRating;
    Prison prison;

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
        if (!currentInmates.isEmpty()) {
            currentInmates.remove(id);
            for (int i : ids) {
                currentInmates.remove(i);
            }
        }
    }

    public  void calculateRiskRating() {
        // Calculate avgHappiness and ratingAdjHappiness for currentInmateMap
        double avgHappiness = 100.0;
        if (!currentInmates.isEmpty()) {
            avgHappiness = currentInmates.values().stream()
                    .mapToInt(Inmate::getHappiness)
                    .average().getAsDouble();
        }
        int ratingAdjHappiness = (int) (100 - avgHappiness) / 2;
        // Facility being at max capacity will increase riskRating by 50
        double ratingAdjCapacity = ((double) currentInmates.size() / getMaxCapacity()) * 50;
        // Add a certain amount for each gangLeader
        int ratingAdjGang =
                (int) currentInmates.values().stream().filter(Inmate::isGangLeader).count() * 25;
        this.riskRating = ratingAdjHappiness + (int) ratingAdjCapacity + ratingAdjGang;
    }

    public void displayCurrentInmates() {
        for (Inmate inmate : getInmateMap().values()) {
            System.out.printf(inmate.toString());
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