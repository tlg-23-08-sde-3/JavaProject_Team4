package org.prison.silicon.facility;

import org.prison.silicon.SecurityRating;
import org.prison.silicon.population.Inmate;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import static java.util.stream.Collectors.toMap;

public enum Facility {
    LOW_SECURITY_UNIT("Low Security Unit", 30, SecurityRating.LOW),
    MEDIUM_SECURITY_UNIT("Medium Security Unit", 30, SecurityRating.MEDIUM),
    HIGH_SECURITY_UNIT("High Security Unit", 30, SecurityRating.HIGH),
    YARD("Yard", 30, SecurityRating.HIGH),
    KITCHEN("Kitchen", 30, SecurityRating.HIGH),
    WORK_AREA("Work Area", 30, SecurityRating.HIGH);

    // fields
    private final String name;
    private final int maxCapacity;
    private final SecurityRating securityRating;
    private Map<Integer, Inmate> currentInmates;
    private int riskRating;

    // static initializer
    private static Map<Integer, Facility> idMap = Arrays.stream(Facility.values())
                    .collect(toMap(Enum::ordinal, facility -> facility));

    public static Map<Integer, Facility> getIdMap() {
        return idMap;
    }

    Facility(String name, int maxCapacity, SecurityRating securityRating) {
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

    private int securityRatingViolation(Inmate inmate) {
        int penalty = 0;
            if (inmate.getSecurityRating().ordinal() > getSecurityRating().ordinal()) {
                penalty += 10;
            }
        return penalty;
    }
    public void calculateRiskRating() {
        // Calculate avgHappiness and ratingAdjHappiness for currentInmateMap
        double avgHappiness = 100.0;
        int adjSecurityViolation = 0;
        if (!currentInmates.isEmpty()) {
            avgHappiness = currentInmates.values().stream()
                    .mapToInt(Inmate::getHappiness)
                    .average().getAsDouble();
            for (Inmate inmate : currentInmates.values()) {
                adjSecurityViolation += securityRatingViolation(inmate);
            }
        }
        int ratingAdjHappiness = (int) (100 - avgHappiness) / 2;
        // Facility being at max capacity will increase riskRating by 50
        double ratingAdjCapacity = ((double) currentInmates.size() / getMaxCapacity()) * 75;
        // Add a certain amount for each gangLeader
        int ratingAdjGang =
                (int) currentInmates.values().stream().filter(Inmate::isGangLeader).count() * 10;
        // calculate rating for facility
        this.riskRating = ratingAdjHappiness + (int) ratingAdjCapacity + ratingAdjGang + adjSecurityViolation;
    }

    public void displayCurrentInmates() {
        for (Inmate inmate : getInmateMap().values()) {
            System.out.print(inmate.toString());
        }
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public SecurityRating getSecurityRating() {
        return securityRating;
    }

    public int getCount() {
        return currentInmates.size();
    }

    public Integer[] getInmatesIds() {
        return currentInmates.keySet().toArray(new Integer[0]);
    }

    public Map<Integer, Inmate> getInmateMap() {
        return currentInmates;
    }

    public int getRiskRating() {
        calculateRiskRating();
        return riskRating;
    }

    public Map<Integer, Inmate> getCurrentInmates() {
        return currentInmates;
    }
}