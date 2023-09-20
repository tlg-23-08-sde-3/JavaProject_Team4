package org.prison.silicon.population;

import org.junit.Before;
import org.junit.Test;
import org.prison.silicon.Facility;
import org.prison.silicon.FacilityList;
import org.prison.silicon.Prison;
import org.prison.silicon.SecurityRating;

import static org.junit.Assert.*;

public class LowSecurityInmateTest {
    Inmate in1;
    Facility kitchen, yard, work, low, med, high;
    Prison prison;

    @Before
    public void setUp() {
        kitchen = new Facility(FacilityList.KITCHEN, 15, SecurityRating.MEDIUM);
        yard = new Facility(FacilityList.YARD, 20, SecurityRating.MEDIUM);
        work = new Facility(FacilityList.WORK_AREA, 15, SecurityRating.LOW);
        low = new Facility(FacilityList.LOW_SECURITY_UNIT, 5, SecurityRating.LOW);
        med = new Facility(FacilityList.LOW_SECURITY_UNIT, 5, SecurityRating.MEDIUM);
        high = new Facility(FacilityList.HIGH_SECURITY_UNIT, 10, SecurityRating.HIGH);
        prison = new Prison("JailBreak", kitchen, yard, work, low, med, high);
        in1 = new LowSecurityInmate(1001, "Inmate1", true, SecurityRating.LOW, prison);
    }

    @Test
    public void constructor_shouldReturnAnInstanceOfLowSecurityInmate_whenPassedAllArgs() {
        assertEquals(1001, in1.getIdNumber());
        assertEquals("Inmate1", in1.getName());
        assertTrue(in1.isGangLeader());
        assertEquals(SecurityRating.LOW, in1.getSecurityRating());
    }

    // Make the inmate work and test to see if the inmate's location changes to Work_Area
    @Test
    public void work_shouldReturnTheInmatesNewLocation_when() {
        in1.work();
        assertEquals(in1.getCurrentLocation(), FacilityList.WORK_AREA);
    }

    // Move the inmate to the Yard
    @Test
    public void move_shouldReturnALocationOfYard_whenYardIsPassedToMove() {
        in1.move(FacilityList.YARD);
        assertEquals(in1.getCurrentLocation(), FacilityList.YARD);
    }

    // Move the inmate to the HighSecurityUnit
    @Test
    public void move_shouldReturnALocationOfHighSecurityUnit_whenHighSecurityUnitIsPassedToMove() {
        in1.move(FacilityList.HIGH_SECURITY_UNIT);
        assertEquals(in1.getCurrentLocation(), FacilityList.HIGH_SECURITY_UNIT);
    }

    @Test
    public void move_popupAppears_whenNewLocationIsSameAsCurrentLocation() {
        in1.move(FacilityList.LOW_SECURITY_UNIT);
        // popUp appeared will have to research how to test swing
    }
}