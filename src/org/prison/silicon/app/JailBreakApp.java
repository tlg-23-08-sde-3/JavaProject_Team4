package org.prison.silicon.app;

import org.prison.silicon.facility.Facility;
import org.prison.silicon.facility.FacilityList;
import org.prison.silicon.facility.Prison;
import org.prison.silicon.SecurityRating;
import org.prison.silicon.gui.MainGui;
import org.prison.silicon.population.Inmate;
import org.prison.silicon.population.InmateLoader;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class JailBreakApp {
    private static final int winTimeLimit = 100_000;
    public static Facility lowSecurityUnit = new Facility(FacilityList.LOW_SECURITY_UNIT, 15, SecurityRating.LOW);
    public static Facility mediumSecurityUnit = new Facility(FacilityList.MEDIUM_SECURITY_UNIT, 15, SecurityRating.MEDIUM);
    public static Facility highSecurityUnit = new Facility(FacilityList.HIGH_SECURITY_UNIT, 15, SecurityRating.HIGH);
    public static Facility yard = new Facility(FacilityList.YARD, 75, SecurityRating.MEDIUM);
    public static Facility kitchen = new Facility(FacilityList.KITCHEN, 75, SecurityRating.MEDIUM);
    public static Facility workArea = new Facility(FacilityList.WORK_AREA, 75, SecurityRating.MEDIUM);
    public static Prison prison = new Prison("Prison", lowSecurityUnit, mediumSecurityUnit, highSecurityUnit, yard, kitchen, workArea);

    public void execute() throws IOException {
        loadInmates();
        loadGUI();
        startWinTimer();
    }

    private void startWinTimer() {
        WinTimer winTimer = new WinTimer(winTimeLimit);
        winTimer.start();
    }

    private void loadGUI() throws IOException {
        // launch GUI
        MainGui mainGui = new MainGui(prison, lowSecurityUnit, mediumSecurityUnit, highSecurityUnit, yard, kitchen, workArea);


        // Update inmate counts in each area by passing Maps in this order
        //      [lowSecurityUnit, mediumSecurityUnit, highSecurityUnit, yard, kitchen]
        mainGui.updateCounts(lowSecurityUnit.getInmateMap(),
                mediumSecurityUnit.getInmateMap(),
                highSecurityUnit.getInmateMap(),
                yard.getInmateMap(),
                kitchen.getInmateMap(),
                workArea.getInmateMap());
    }

    private void loadInmates() throws IOException {
        // 40% chance to be in their home unit based upon security rating
        // 20% chance to be in one of the other 3 areas
        Random ran = new Random();
        int ranLoc = 0;
        InmateLoader inLoader = new InmateLoader(prison);
        List<Inmate> inmateLoadList = inLoader.load();
        for (Inmate inmate : inmateLoadList) {
            ranLoc = ran.nextInt(5);
            switch (ranLoc) {
                case 0:
                case 1:
                    switch (inmate.getSecurityRating()) {
                        case LOW:
                            lowSecurityUnit.addInmate(inmate);
                            break;
                        case MEDIUM:
                            mediumSecurityUnit.addInmate(inmate);
                            break;
                        case HIGH:
                            highSecurityUnit.addInmate(inmate);
                            break;
                    }
                    break;
                case 2:
                    kitchen.addInmate(inmate);
                    break;
                case 3:
                    yard.addInmate(inmate);
                    break;
                case 4:
                    workArea.addInmate(inmate);
                    break;
            }
        }
    }
}