package org.prison.silicon.population;

import org.junit.Before;
import org.junit.Test;
import org.prison.silicon.Facility;
import org.prison.silicon.FacilityList;
import org.prison.silicon.Prison;
import org.prison.silicon.SecurityRating;

import static org.junit.Assert.*;

public class MediumSecurityInmateTest {
    Inmate in1;
    Facility kitchen, yard, work, low, med, high;
    Prison prison;


    @Before
    public void setUp() {
        kitchen = new Facility(FacilityList.KITCHEN, 15, SecurityRating.MEDIUM);
        yard = new Facility(FacilityList.YARD, 20, SecurityRating.MEDIUM);
        work = new Facility(FacilityList.WORK_AREA, 15, SecurityRating.LOW);
        low = new Facility(FacilityList.LOW_SECURITY_UNIT, 5, SecurityRating.LOW);
        med = new Facility(FacilityList.MEDIUM_SECURITY_UNIT, 5, SecurityRating.MEDIUM);
        high = new Facility(FacilityList.HIGH_SECURITY_UNIT, 10, SecurityRating.HIGH);
        prison = new Prison("JailBreak", kitchen, yard, work, low, med, high);
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
        assertEquals(in1.getCurrentLocation(), FacilityList.WORK_AREA);
    }

    @Test
    public void move_shouldReturnYardAsTheCurrentLocation_whenYardIsPassedToMove() {
        in1.move(FacilityList.YARD);
        assertEquals(FacilityList.YARD, in1.getCurrentLocation());
    }

    @Test
    public void eat_shouldReturnACurrentLocationOfKitchen_whenEatIsCalledOnAnInmate() {
        in1.eat();
        assertEquals(FacilityList.KITCHEN, in1.getCurrentLocation());
    }

    @Test
    public void sleep_shouldReturnACurrentLocationOfTheInmatesOriginalUnit_whenSleepIsCalledOnAnInmate() {
        // need to move the inmate out of the original unit first by calling work if not the JOption popup will trigger
        in1.work();
        // call sleep to move the inmate to the original unit
        in1.sleep();
        assertEquals(FacilityList.MEDIUM_SECURITY_UNIT, in1.getCurrentLocation());
    }
}