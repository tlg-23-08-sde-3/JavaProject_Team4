package org.prison.silicon.gui;

import org.junit.Test;

import org.prison.silicon.SecurityRating;
import org.prison.silicon.facility.Facility;
import org.prison.silicon.facility.FacilityList;
import org.prison.silicon.facility.Prison;

import java.io.IOException;

public class MainGuiTest {
    public static Facility lowSecurityUnit = new Facility(FacilityList.LOW_SECURITY_UNIT, 15, SecurityRating.LOW);
    public static Facility mediumSecurityUnit = new Facility(FacilityList.MEDIUM_SECURITY_UNIT, 15, SecurityRating.MEDIUM);
    public static Facility highSecurityUnit = new Facility(FacilityList.HIGH_SECURITY_UNIT, 15, SecurityRating.HIGH);
    public static Facility yard = new Facility(FacilityList.YARD, 75, SecurityRating.MEDIUM);
    public static Facility kitchen = new Facility(FacilityList.KITCHEN, 75, SecurityRating.MEDIUM);
    public static Facility workArea = new Facility(FacilityList.WORK_AREA, 75, SecurityRating.MEDIUM);
    public static Prison prison = new Prison("Prison", lowSecurityUnit, mediumSecurityUnit, highSecurityUnit, yard, kitchen, workArea);

    @Test
    public void mainTest() throws IOException {
        new MainGui(prison, lowSecurityUnit, mediumSecurityUnit, highSecurityUnit, yard, kitchen, workArea);
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}