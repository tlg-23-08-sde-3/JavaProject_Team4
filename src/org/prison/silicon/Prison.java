package org.prison.silicon;

import org.prison.silicon.population.Inmate;

import java.util.HashMap;
import java.util.Map;

public class Prison {
    private final String name;
    private final Map<Facility, SecurityRating> prisonMap;
    private boolean lockDown;
    private int funds;
    private int totalDays;
    private int riskRating;

    public Prison(String name, Facility facility, Facility... facilities) {
        this.name = name;
        prisonMap = new HashMap<>();
        this.prisonMap.put(facility, facility.getSecurityRating());
        for (Facility fac : facilities) {
            this.prisonMap.put(fac, fac.getSecurityRating());
        }
    }

    // Business methods
    public void lockdownSwitch() {
        this.lockDown = !lockDown;
    }

    public int getCount() {
        int count = 0;
        for (Facility facility : prisonMap.keySet()) {
            count += facility.getCount();
        }
        return count;
    }

    public int getMaxCapacity() {
        int maxCap = 0;
        for (Facility facility : prisonMap.keySet()) {
            maxCap += facility.getMaxCapacity();
        }
        return maxCap;
    }

    public void calculateRiskRating() {
        int sum = 0;
        for (Facility facility : prisonMap.keySet()) {
            sum = facility.getRiskRating();
        }
        this.riskRating = sum / prisonMap.size();
    }

    public void displayInmates() {
        for (Facility facility : getPrisonMap().keySet()) {
            System.out.println(facility.getName().getDisplayName());
            System.out.println("===========================================================");
            for (Inmate inmate : facility.getInmateMap().values()) {
                System.out.printf("ID: %d, Name: %s, Security Rating: %s, Gang Leader: %s, Happiness: int\n",
                        inmate.getIdNumber(), inmate.getName(), inmate.getSecurityRating().getDisplayName(), inmate.isGangLeader());
            }
            System.out.println("===========================================================");
        }
    }

    // Getters
    public String getName() {
        return name;
    }

    public boolean isLockDown() {
        return lockDown;
    }

    public void setLockDown(boolean lockDown) {
        this.lockDown = lockDown;
    }

    public int getFunds() {
        return funds;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public int getRiskRating() {
        return riskRating;
    }

    public Map<Facility, SecurityRating> getPrisonMap() {
        return prisonMap;
    }
}