package org.prison.silicon;

import org.junit.Before;
import org.junit.Test;
import org.prison.silicon.population.HighSecurityInmate;
import org.prison.silicon.population.Inmate;
import org.prison.silicon.population.LowSecurityInmate;
import org.prison.silicon.population.MediumSecurityInmate;

import static org.junit.Assert.*;

public class PrisonTest {
    Inmate in1, in2, in3, in4, in5;
    Facility kitchen = new Facility(FacilityList.KITCHEN, 15, SecurityRating.MEDIUM);
    Facility low = new Facility(FacilityList.LOW_SECURITY_UNIT, 1, SecurityRating.LOW);
    Facility high = new Facility(FacilityList.HIGH_SECURITY_UNIT, 10, SecurityRating.HIGH);

    @Before
    public void setUp() throws Exception {
        in1 = new LowSecurityInmate(1001, "Inmate1", false, SecurityRating.LOW);
        in2 = new MediumSecurityInmate(1002, "Inmate2", false, SecurityRating.MEDIUM);
        in3 = new HighSecurityInmate(1003, "Inmate3", false, SecurityRating.HIGH);
        in4 = new HighSecurityInmate(1004, "Inmate4", true, SecurityRating.HIGH);
        in5 = new HighSecurityInmate(1005, "Inmate5", false, SecurityRating.LOW);
    }

    @Test
    public void prison_validPrisonCreated_withSingleFacilityPassed() {
        Prison prison = new Prison("JailBreak", kitchen);
        assertEquals("JailBreak", prison.getName());
        assertEquals(1, prison.getPrisonMap().size());
    }

    @Test
    public void prison_validPrisonCreated_withMultipleFacilitiesPassed() {
        Prison prison = new Prison("JailBreak", kitchen, low, high);
        assertEquals("JailBreak", prison.getName());
        assertEquals(3, prison.getPrisonMap().size());
    }

    @Test
    public void getCount_correctInmateCount_whenPrisonHasMultipleFacilities() {
        Prison prison = new Prison("JailBreak", kitchen, low, high);
        kitchen.addInmate(in1, in2);
        low.addInmate(in5);
        high.addInmate(in3, in4);
        assertEquals(5, prison.getCount());
    }

    @Test
    public void getMaxCapacity_correctMaxCapacity_whenPrisonHasMultipleFacilities() {
        Prison prison = new Prison("JailBreak", kitchen, low, high);
        assertEquals(26, prison.getMaxCapacity());
    }

    @Test
    public void displayInmates_inmatesDisplayed_whenCurrentInmateMapPopulatedForFacilities() {
        Prison prison = new Prison("JailBreak", kitchen, low, high);
        kitchen.addInmate(in1, in2);
        low.addInmate(in5);
        high.addInmate(in3, in4);
        prison.displayInmates();
    }
}