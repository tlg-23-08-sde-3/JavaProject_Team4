package org.prison.silicon.population;

import org.junit.Before;
import org.junit.Test;
import org.prison.silicon.facility.Facility;
import org.prison.silicon.facility.Prison;
import org.prison.silicon.SecurityRating;

import static org.junit.Assert.*;
import static org.prison.silicon.facility.Facility.*;

public class MediumSecurityInmateTest {
    Inmate in1;
    Facility kitchen, yard, work, low, med, high;
    Prison prison;


    @Before
    public void setUp() {
        prison = new Prison("JailBreak", LOW_SECURITY_UNIT, MEDIUM_SECURITY_UNIT, HIGH_SECURITY_UNIT, YARD, KITCHEN, WORK_AREA);
        in1 = new MediumSecurityInmate(1001, "Inmate1", true, SecurityRating.MEDIUM, prison);
    }

    @Test
    public void constructor_shouldReturnAMediumSecurityInmateObject_whenPassedAllTheRequiredArgs() {
        assertEquals(1001, in1.getIdNumber());
        assertEquals("Inmate1", in1.getName());
        assertTrue(in1.isGangLeader());
        assertEquals(SecurityRating.MEDIUM, in1.getSecurityRating());
    }

    @Test
    public void work_shouldReturnACurrentLocationOfWorkArea_whenWorkIsCalledOnAnInmate() {
        in1.work();
        assertEquals(in1.getCurrentLocation(), WORK_AREA);
    }

    @Test
    public void move_shouldReturnYardAsTheCurrentLocation_whenYardIsPassedToMove() {
        in1.move(YARD);
        assertEquals(YARD, in1.getCurrentLocation());
    }

    @Test
    public void eat_shouldReturnACurrentLocationOfKitchen_whenEatIsCalledOnAnInmate() {
        in1.eat();
        assertEquals(KITCHEN, in1.getCurrentLocation());
    }

    @Test
    public void sleep_shouldReturnACurrentLocationOfTheInmatesOriginalUnit_whenSleepIsCalledOnAnInmate() {
        // need to move the inmate out of the original unit first by calling work if not the JOption popup will trigger
        in1.work();
        // call sleep to move the inmate to the original unit
        in1.sleep();
        assertEquals(MEDIUM_SECURITY_UNIT, in1.getCurrentLocation());
    }

    @Test
    public void setHappiness_shouldSetAs50_whenInmateInstantiated() {
        assertEquals(50, in1.getHappiness());
    }
}