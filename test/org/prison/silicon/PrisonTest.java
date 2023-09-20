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
    Facility yard = new Facility(FacilityList.YARD, 20, SecurityRating.MEDIUM);
    Facility work = new Facility(FacilityList.WORK_AREA, 15, SecurityRating.LOW);
    Facility low = new Facility(FacilityList.LOW_SECURITY_UNIT, 5, SecurityRating.LOW);
    Facility med = new Facility(FacilityList.LOW_SECURITY_UNIT, 5, SecurityRating.MEDIUM);
    Facility high = new Facility(FacilityList.HIGH_SECURITY_UNIT, 10, SecurityRating.HIGH);

    @Before
    public void setUp() throws Exception {
        Prison prison = new Prison("JailBreak", kitchen, yard, work, low, med, high);
        in1 = new LowSecurityInmate(1001, "Inmate1", true, SecurityRating.LOW, prison);
        in2 = new MediumSecurityInmate(1002, "Inmate2", false, SecurityRating.MEDIUM, prison);
        in3 = new HighSecurityInmate(1003, "Inmate3", false, SecurityRating.HIGH, prison);
        in4 = new HighSecurityInmate(1004, "Inmate4", true, SecurityRating.HIGH, prison);
        in5 = new MediumSecurityInmate(1005, "Inmate5", false, SecurityRating.MEDIUM, prison);
        kitchen.addInmate(in5, in2);
        low.addInmate(in1);
        high.addInmate(in3, in4);
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
        assertEquals(5, prison.getCount());
    }

    @Test
    public void getMaxCapacity_correctMaxCapacity_whenPrisonHasMultipleFacilities() {
        Prison prison = new Prison("JailBreak", kitchen, low, high);
        assertEquals(30, prison.getMaxCapacity());
    }

    @Test
    public void calculateRiskRating_validRatingReturned_whenPrisonHasSingleFacility() {
        Prison prison = new Prison("JailBreak", kitchen);
        kitchen.addInmate(in1, in2, in3, in4, in5);
        kitchen.calculateRiskRating();
        assertEquals(93, kitchen.getRiskRating());
    }

    @Test
    public void calculateRiskRating_validRatingReturned_whenPrisonHasMultipleFacilities() {
        Prison prison = new Prison("JailBreak", kitchen, low, high);
        kitchen.calculateRiskRating();
        low.calculateRiskRating();
        high.calculateRiskRating();
        prison.calculateRiskRating();
        assertEquals(50, prison.getRiskRating());
    }

    @Test
    public void displayInmates_allInmatesForPrisonDisplayed() {
        Prison prison = new Prison("JailBreak", kitchen, low, high);
        prison.displayInmates();
    }

    @Test
    public void moveInmate_facilitySizesCorrect_afterInmateMove() {
        Prison prison = new Prison("JailBreak", kitchen, low, high);
        in2.setCurrentLocation(FacilityList.KITCHEN);
        prison.moveInmate(in2, FacilityList.LOW_SECURITY_UNIT);
        assertEquals(1, kitchen.currentInmates.size());
        assertEquals(2, low.currentInmates.size());

    }

    @Test
    public void moveInmate_returnTrue_whenInmateMoveSuccessful() {
        Prison prison = new Prison("JailBreak", kitchen, low, high);
        in2.setCurrentLocation(FacilityList.KITCHEN);
        assertTrue(prison.moveInmate(in2, FacilityList.LOW_SECURITY_UNIT));
    }

    @Test
    public void moveInmate_returnFalseAndPopUpDisplays_whenInmateMoveNotSuccessful() {
        Prison prison = new Prison("JailBreak", kitchen, low, high);
        assertFalse(prison.moveInmate(in1, FacilityList.LOW_SECURITY_UNIT));
        // popup displays. Will need to research how to test
    }

    @Test
    public void findInmateById_returnsCorrectInmate_whenValidInmateIdPassed() {
        Prison prison = new Prison("JailBreak", kitchen, low, high);
        assertEquals(in4, prison.locateInmateByID(1004));
        assertEquals("Inmate4", prison.locateInmateByID(1004).getName());
    }

    @Test
    public void findInmateById_returnsPopup_whenIdNotFound() {
        Prison prison = new Prison("JailBreak", kitchen, low, high);
        prison.locateInmateByID(1010);
    }
}