package org.prison.silicon.facility;

import org.prison.silicon.SecurityRating;
import org.prison.silicon.population.Inmate;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Prison {
    private Map<Facility, SecurityRating> prisonMap;
    private String name;
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
        boolean facilityRiot = false;
        int riotCount = 0;
        for (Facility facility : prisonMap.keySet()) {
            if (facility.getRiskRating() >= 100) {
                facilityRiot = true;
                riotCount++;
            }
            facility.calculateRiskRating();
            sum += facility.getRiskRating();
        }
        int avgRisk = sum / prisonMap.size();

        this.riskRating = facilityRiot ? avgRisk + (riotCount * 25) : avgRisk;
    }

    public void displayInmates() {
        for (Facility facility : getPrisonMap().keySet()) {
            System.out.println(facility.getName());
            System.out.println("===========================================================");
            for (Inmate inmate : facility.getInmateMap().values()) {
                System.out.print(inmate);
            }
            System.out.println("===========================================================");
        }
    }

    private Facility parseFacility(Facility listName) {
        Facility result = null;
        for (Facility facility : getPrisonMap().keySet()) {
            if (facility.equals(listName)) {
                result = facility;
            }
        }
        return result;
    }

    private Facility parseFacility(String displayName) {
        Facility result = null;
        for (Facility facility : getPrisonMap().keySet()) {
            if (facility.getName().equals(displayName)) {
                result = facility;
            }
        }
        return result;
    }

    public boolean moveInmate(Inmate inmate, Facility newLocation) {
        boolean successfulMove = false;
        try {
            if (!inmate.getCurrentLocation().equals(newLocation)) {
                Facility currentFacility = parseFacility(inmate.getCurrentLocation());
                Facility newFacility = parseFacility(newLocation);
                // Move the inmate to a new location
                currentFacility.removeInmate(inmate.getIdNumber());
                // move inmate to the new location and update currentLocation
                newFacility.addInmate(inmate);
                System.out.printf("Inmate %s has been moved to %s\n", inmate.getIdNumber(), newFacility.getName());
                successfulMove = true;
                currentFacility.calculateRiskRating();
                newFacility.calculateRiskRating();
                calculateRiskRating();
            } else {
                throw new IllegalArgumentException("Can't move an inmate to a location they are already in.");
            }
        } catch (IllegalArgumentException e) {
            String message = "Inmate " + inmate.getIdNumber() + " is already in the "
                    + inmate.getCurrentLocation().getName() + "\narea. Please select a different location.";
            String title = "Invalid Move To " + newLocation.getName();
            InvalidChoice(title, message);
        }

        return successfulMove;
    }

    // Invalid move method that generates a pop-up window
    private void InvalidChoice(String title, String message) {
        JOptionPane.showMessageDialog(null,
                message,
                title,
                JOptionPane.WARNING_MESSAGE);
    }

    public Inmate locateInmateByID(int id) {
        Inmate desiredInmate = null;
        for (Facility facility : getPrisonMap().keySet()) {
            for (Integer i : facility.getInmateMap().keySet()) {
                if (i == id) {
                    desiredInmate = facility.getInmateMap().get(i);
                }
            }
        }
        if (Objects.equals(desiredInmate, null)) {
            String message = "Inmate ID \"" + id + "\" was not found ";
            String title = "Inmate ID Not Found";
            InvalidChoice(title, message);
        }
        return desiredInmate;
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
        System.out.println(this.riskRating);
        return riskRating;
    }

    public Map<Facility, SecurityRating> getPrisonMap() {
        return prisonMap;
    }

}