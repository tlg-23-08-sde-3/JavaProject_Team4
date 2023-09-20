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
        MainGui mainGui = new MainGui(prison);


        // Update inmate counts in each area by passing Maps in this order
        //      [lowSecurityUnit, mediumSecurityUnit, highSecurityUnit, yard, kitchen]
        mainGui.updateCounts(lowSecurityUnit.getInmateMap(),
                mediumSecurityUnit.getInmateMap(),
                highSecurityUnit.getInmateMap(),
                yard.getInmateMap(),
                kitchen.getInmateMap());
    }
    private void loadInmates() throws IOException {
        // Adding inmateLoadList to random areas
        Random ran = new Random();
        int ranLoc = 0;
        InmateLoader inLoader = new InmateLoader(prison);
        List<Inmate> inmateLoadList = inLoader.load();
        for (Inmate inmate : inmateLoadList) {
            ranLoc = ran.nextInt(6);
            switch (ranLoc) {
                case 0:
                    lowSecurityUnit.addInmate(inmate);
                    break;
                case 1:
                    mediumSecurityUnit.addInmate(inmate);
                    break;
                case 2:
                    highSecurityUnit.addInmate(inmate);
                    break;
                case 3:
                    kitchen.addInmate(inmate);
                    break;
                case 4:
                    yard.addInmate(inmate);
                    break;
                case 5:
                    workArea.addInmate(inmate);
                    break;
            }
        }
    }
}