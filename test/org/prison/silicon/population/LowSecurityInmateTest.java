package org.prison.silicon.population;

import org.junit.Before;
import org.junit.Test;
import org.prison.silicon.FacilityList;
import org.prison.silicon.SecurityRating;

import static org.junit.Assert.*;

public class LowSecurityInmateTest {
    LowSecurityInmate testInmate;

    @Before
    public void setUp() {
        testInmate = new LowSecurityInmate(
                123456,
                "Johnny",
                true,
                SecurityRating.LOW);
    }

    @Test
    public void constructor_shouldReturnAnInstanceOfLowSecurityInmate_whenPassedAllArgs() {
        assertEquals(123456, testInmate.getIdNumber());
        assertEquals("Johnny", testInmate.getName());
        assertTrue(testInmate.isGangLeader());
        assertEquals(SecurityRating.LOW, testInmate.getSecurityRating());
    }

    // Make the inmate work and test to see if the inmate's location changes to Work_Area
    @Test
    public void work_shouldReturnTheInmatesNewLocation_when() {
        testInmate.work();
        assertTrue(testInmate.getCurrentLocation().equals(FacilityList.WORK_AREA));
    }

    // Move the inmate to the Yard
    @Test
    public void move_shouldReturnALocationOfYard_whenYardIsPassedToMove() {
        testInmate.move(FacilityList.YARD);
        assertTrue(testInmate.getCurrentLocation().equals(FacilityList.YARD));
    }

    // Move the inmate to the HighSecurityUnit
    @Test
    public void move_shouldReturnALocationOfHighSecurityUnit_whenHighSecurityUnitIsPassedToMove() {
        testInmate.move(FacilityList.HIGH_SECURITY_UNIT);
        assertTrue(testInmate.getCurrentLocation().equals(FacilityList.HIGH_SECURITY_UNIT));
    }
}