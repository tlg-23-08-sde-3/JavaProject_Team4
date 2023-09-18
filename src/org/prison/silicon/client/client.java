package org.prison.silicon.client;

import org.prison.silicon.Facility;
import org.prison.silicon.FacilityList;
import org.prison.silicon.Prison;
import org.prison.silicon.SecurityRating;
import org.prison.silicon.gui.MainGui;

import java.io.IOException;

public class client {
    public static Facility lowSecurityUnit = new Facility(FacilityList.LOW_SECURITY_UNIT, 15, SecurityRating.LOW);
    public static Facility mediumSecurityUnit = new Facility(FacilityList.MEDIUM_SECURITY_UNIT, 15, SecurityRating.MEDIUM);
    public static Facility highSecurityUnit = new Facility(FacilityList.HIGH_SECURITY_UNIT, 15, SecurityRating.HIGH);
    public static Facility yard = new Facility(FacilityList.YARD, 75, SecurityRating.MEDIUM);
    public static Facility kitchen = new Facility(FacilityList.KITCHEN, 75, SecurityRating.MEDIUM);
    public static Prison prison = new Prison("Prison", lowSecurityUnit, mediumSecurityUnit, highSecurityUnit, yard, kitchen);

    public static void main(String[] args) throws IOException {

        // launch GUI
        MainGui mainGui = new MainGui();

        // Update inmate counts in each area by passing Maps in this order
        //      [lowSecurityUnit, mediumSecurityUnit, highSecurityUnit, yard, kitchen]
        mainGui.updateCounts(lowSecurityUnit.getInmateMap(),
                mediumSecurityUnit.getInmateMap(),
                highSecurityUnit.getInmateMap(),
                yard.getInmateMap(),
                kitchen.getInmateMap());
    }
}