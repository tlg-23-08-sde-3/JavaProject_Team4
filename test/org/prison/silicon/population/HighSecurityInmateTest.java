package org.prison.silicon.population;

import org.junit.Before;
import org.junit.Test;
import org.prison.silicon.FacilityList;
import org.prison.silicon.SecurityRating;

import static org.junit.Assert.*;

public class HighSecurityInmateTest {
    HighSecurityInmate testInmate;

    @Before
    public void setUp() {
        testInmate = new HighSecurityInmate(
                123456,
                "Johnny",
                true,
                SecurityRating.HIGH);
    }

    @Test
    public void constructor_shouldReturnAHighSecurityInmateObject_whenPassedAllTheRequiredArgs() {
        assertEquals(123456, testInmate.getIdNumber());
        assertEquals("Johnny", testInmate.getName());
        assertEquals(true, testInmate.isGangLeader());
        assertEquals(SecurityRating.HIGH, testInmate.getSecurityRating());
    }

    @Test
    public void work_shouldReturnACurrentLocationOfWorkArea_whenWorkIsCalledOnAnInmate() {
        testInmate.work();
        assertEquals(FacilityList.WORK_AREA, testInmate.getCurrentLocation());
    }

    @Test
    public void move_shouldReturnYardAsTheCurrentLocation_whenYardIsPassedToMove() {
        testInmate.move(FacilityList.YARD);
        assertEquals(FacilityList.YARD, testInmate.getCurrentLocation());
    }

    @Test
    public void eat_shouldReturnACurrentLocationOfKitchen_whenEatIsCalledOnAnInmate() {
        testInmate.eat();
        assertEquals(FacilityList.KITCHEN, testInmate.getCurrentLocation());
    }

    @Test
    public void sleep_shouldReturnACurrentLocationOfTheInmatesOriginalUnit_whenSleepIsCalledOnAnInmate() {
        // need to move the inmate out of the original unit first by calling work if not the JOption popup will trigger
        testInmate.work();
        // call sleep to move the inmate to the original unit
        testInmate.sleep();
        assertEquals(FacilityList.HIGH_SECURITY_UNIT, testInmate.getCurrentLocation());
    }
}