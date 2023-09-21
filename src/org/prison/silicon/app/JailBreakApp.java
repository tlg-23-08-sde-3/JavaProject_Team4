package org.prison.silicon.app;

import org.prison.silicon.facility.Prison;
import org.prison.silicon.gui.MainGui;
import org.prison.silicon.population.Inmate;
import org.prison.silicon.population.InmateLoader;

import static org.prison.silicon.facility.Facility.*;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class JailBreakApp {
    private static final int winTimeLimit = 100_000;
    public static Prison prison = new Prison("Prison", LOW_SECURITY_UNIT, MEDIUM_SECURITY_UNIT, HIGH_SECURITY_UNIT, YARD, KITCHEN, WORK_AREA);

    public void execute() throws IOException {
        loadInmates();
        updateInitalRiskRating();
        loadGUI();
        startWinTimer();
    }

    private void startWinTimer() {
        WinTimer winTimer = new WinTimer(winTimeLimit);
        winTimer.start();
    }

    private void updateInitalRiskRating() {
        LOW_SECURITY_UNIT.calculateRiskRating();
        MEDIUM_SECURITY_UNIT.calculateRiskRating();
        HIGH_SECURITY_UNIT.calculateRiskRating();
        YARD.calculateRiskRating();
        WORK_AREA.calculateRiskRating();
        KITCHEN.calculateRiskRating();
        prison.calculateRiskRating();
    }

    private void loadGUI() throws IOException {
        // launch GUI
        MainGui mainGui = new MainGui(prison, LOW_SECURITY_UNIT, MEDIUM_SECURITY_UNIT, HIGH_SECURITY_UNIT, YARD, KITCHEN, WORK_AREA);


        // Update inmate counts in each area by passing Maps in this order
        //      [lowSecurityUnit, mediumSecurityUnit, highSecurityUnit, yard, kitchen]
        mainGui.updateCounts(LOW_SECURITY_UNIT.getInmateMap(),
                MEDIUM_SECURITY_UNIT.getInmateMap(),
                HIGH_SECURITY_UNIT.getInmateMap(),
                YARD.getInmateMap(),
                KITCHEN.getInmateMap(),
                WORK_AREA.getInmateMap());
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
                            LOW_SECURITY_UNIT.addInmate(inmate);
                            inmate.setCurrentLocation(LOW_SECURITY_UNIT);
                            break;
                        case MEDIUM:
                            MEDIUM_SECURITY_UNIT.addInmate(inmate);
                            inmate.setCurrentLocation(MEDIUM_SECURITY_UNIT);
                            break;
                        case HIGH:
                            HIGH_SECURITY_UNIT.addInmate(inmate);
                            inmate.setCurrentLocation(HIGH_SECURITY_UNIT);
                            break;
                    }
                    break;
                case 2:
                    KITCHEN.addInmate(inmate);
                    inmate.setCurrentLocation(KITCHEN);
                    break;
                case 3:
                    YARD.addInmate(inmate);
                    inmate.setCurrentLocation(YARD);
                    break;
                case 4:
                    WORK_AREA.addInmate(inmate);
                    inmate.setCurrentLocation(WORK_AREA);
                    break;
            }
        }
    }
}