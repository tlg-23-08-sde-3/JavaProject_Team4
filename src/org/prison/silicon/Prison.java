package org.prison.silicon;

import org.prison.silicon.population.Inmate;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class Prison {
    private String name;
    public Map<Facility, SecurityRating> prisonMap;
    private int funds;
    private int totalDays;
    private int riskRating;

    public Prison() {
    }

    public Prison(String name, Facility facility, Facility... facilities) {
        this.name = name;
        prisonMap = new HashMap<>();
        this.prisonMap.put(facility, facility.getSecurityRating());
        for (Facility fac : facilities) {
            this.prisonMap.put(fac, fac.getSecurityRating());
        }
    }

    // Business methods
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
            sum += facility.getRiskRating();
        }
        this.riskRating = sum / prisonMap.size();
    }

    public void displayInmates() {
        for (Facility facility : getPrisonMap().keySet()) {
            System.out.println(facility.getName().getDisplayName());
            System.out.println("===========================================================");
            for (Inmate inmate : facility.getInmateMap().values()) {
                System.out.print(inmate);
            }
            System.out.println("===========================================================");
        }
    }

    // Getters
    public String getName() {
        return name;
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

    private Facility parseFacility(FacilityList listName) {
        Facility result = null;
        for (Facility facility : getPrisonMap().keySet()) {
            if (facility.getName().equals(listName)) {
                result = facility;
            }
        }
        return result;
    }

    public boolean moveInmate(Inmate inmate, FacilityList newLocation){
        boolean successfulMove = false;
        try{
            if(!inmate.getCurrentLocation().equals(newLocation)){
        Facility currentFacility = parseFacility(inmate.getCurrentLocation());
        Facility newFacility = parseFacility(newLocation);
        // Move the inmate to a new location
        currentFacility.removeInmate(inmate.getIdNumber());
        // move inmate to the new location and update currentLocation
        newFacility.addInmate(inmate);
        System.out.printf("Inmate %s has been moved to %s\n", inmate.getIdNumber(), inmate.getCurrentLocation());
        successfulMove = true;
        currentFacility.calculateRiskRating();
        newFacility.calculateRiskRating();
        calculateRiskRating();
            } else {
                throw new IllegalArgumentException("Can't move an inmate to a location they are already in.");
            }
        } catch (IllegalArgumentException e) {
            InvalidMove(inmate, newLocation);
        }
        return successfulMove;
    }

    // Invalid move method that generates a pop-up window
    private void InvalidMove(Inmate inmate, FacilityList requestedLocation){
        String message = "Inmate " + inmate.getIdNumber() + " is already in the " + inmate.getCurrentLocation().getDisplayName() +
                "\narea. Please select a different location.";
        String title = "Invalid Move To " + requestedLocation;

        JOptionPane.showMessageDialog(null,
                message,
                title,
                JOptionPane.WARNING_MESSAGE);
    }
}