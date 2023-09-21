package org.prison.silicon.population;

import org.junit.Before;
import org.junit.Test;
import org.prison.silicon.facility.Prison;
import org.prison.silicon.SecurityRating;

import static org.junit.Assert.*;
import static org.prison.silicon.facility.Facility.*;
import static org.prison.silicon.facility.Facility.WORK_AREA;

public class LowSecurityInmateTest {
    Inmate in1;
    Prison prison;

    @Before
    public void setUp() {
        prison = new Prison("JailBreak", LOW_SECURITY_UNIT, MEDIUM_SECURITY_UNIT, HIGH_SECURITY_UNIT, YARD, KITCHEN, WORK_AREA);
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
        assertEquals(in1.getCurrentLocation(), WORK_AREA);
    }

    // Move the inmate to the Yard
    @Test
    public void move_shouldReturnALocationOfYard_whenYardIsPassedToMove() {
        in1.move(YARD);
        assertEquals(in1.getCurrentLocation(), YARD);
    }

    // Move the inmate to the HighSecurityUnit
    @Test
    public void move_shouldReturnALocationOfHighSecurityUnit_whenHighSecurityUnitIsPassedToMove() {
        in1.move(HIGH_SECURITY_UNIT);
        assertEquals(in1.getCurrentLocation(), HIGH_SECURITY_UNIT);
    }

    @Test
    public void move_popupAppears_whenNewLocationIsSameAsCurrentLocation() {
        in1.move(LOW_SECURITY_UNIT);
        // popUp appeared will have to research how to test swing
    }
}