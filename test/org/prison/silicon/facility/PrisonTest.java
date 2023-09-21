package org.prison.silicon.facility;

import org.junit.Before;
import org.junit.Test;
import org.prison.silicon.SecurityRating;
import org.prison.silicon.facility.Facility;
import org.prison.silicon.facility.Prison;
import org.prison.silicon.population.HighSecurityInmate;
import org.prison.silicon.population.Inmate;
import org.prison.silicon.population.LowSecurityInmate;
import org.prison.silicon.population.MediumSecurityInmate;

import static org.prison.silicon.facility.Facility.*;

import static org.junit.Assert.*;

public class PrisonTest {
    Inmate in1, in2, in3, in4, in5;
    Prison prison;

    @Before
    public void setUp() throws Exception {
        prison = new Prison("JailBreak", KITCHEN, LOW_SECURITY_UNIT, HIGH_SECURITY_UNIT);
        in1 = new LowSecurityInmate(1001, "Inmate1", true, SecurityRating.LOW, prison);
        in2 = new MediumSecurityInmate(1002, "Inmate2", false, SecurityRating.MEDIUM, prison);
        in3 = new HighSecurityInmate(1003, "Inmate3", false, SecurityRating.HIGH, prison);
        in4 = new HighSecurityInmate(1004, "Inmate4", true, SecurityRating.HIGH, prison);
        in5 = new MediumSecurityInmate(1005, "Inmate5", false, SecurityRating.MEDIUM, prison);
        // Test passed. currentInmates changed to private. Commenting out calls
        // KITCHEN.currentInmates.clear();
        // LOW_SECURITY_UNIT.currentInmates.clear();
        // HIGH_SECURITY_UNIT.currentInmates.clear();
        KITCHEN.addInmate(in5, in2);
        LOW_SECURITY_UNIT.addInmate(in1);
        HIGH_SECURITY_UNIT.addInmate(in3, in4);
    }

    @Test
    public void prison_validPrisonCreated_withSingleFacilityPassed() {
        Prison prison = new Prison("JailBreak", KITCHEN);
        assertEquals("JailBreak", prison.getName());
        assertEquals(1, prison.getPrisonMap().size());
    }

    @Test
    public void prison_validPrisonCreated_withMultipleFacilitiesPassed() {
        assertEquals("JailBreak", prison.getName());
        assertEquals(3, prison.getPrisonMap().size());
    }

    @Test
    public void getCount_correctInmateCount_whenPrisonHasMultipleFacilities() {
        assertEquals(5, prison.getCount());
    }

    @Test
    public void getMaxCapacity_correctMaxCapacity_whenPrisonHasMultipleFacilities() {
        assertEquals(80, prison.getMaxCapacity());
    }

    @Test
    public void calculateRiskRating_validRatingReturned_whenPrisonHasSingleFacility() {
        Prison prison = new Prison("JailBreak", KITCHEN);
        KITCHEN.getCurrentInmates().clear();
        KITCHEN.addInmate(in1, in2, in3, in4, in5);
        KITCHEN.calculateRiskRating();
        assertEquals(59, KITCHEN.getRiskRating());
    }

    @Test
    public void calculateRiskRating_validRatingReturned_whenPrisonHasMultipleFacilities() {
        KITCHEN.calculateRiskRating();
        LOW_SECURITY_UNIT.calculateRiskRating();
        LOW_SECURITY_UNIT.calculateRiskRating();
        prison.calculateRiskRating();
        assertEquals(19, prison.getRiskRating());
    }

    @Test
    public void displayInmates_allInmatesForPrisonDisplayed() {
        prison.displayInmates();
    }

    @Test
    public void moveInmate_facilitySizesCorrect_afterInmateMove() {
        in2.setCurrentLocation(KITCHEN);
        prison.moveInmate(in2, LOW_SECURITY_UNIT);
        assertEquals(1, KITCHEN.getCurrentInmates().size());
        assertEquals(2, LOW_SECURITY_UNIT.getCurrentInmates().size());

    }

    @Test
    public void moveInmate_returnTrue_whenInmateMoveSuccessful() {
        in2.setCurrentLocation(KITCHEN);
        assertTrue(prison.moveInmate(in2, LOW_SECURITY_UNIT));
    }

    @Test
    public void moveInmate_returnFalseAndPopUpDisplays_whenInmateMoveNotSuccessful() {
        assertFalse(prison.moveInmate(in1, LOW_SECURITY_UNIT));
        // popup displays. Will need to research how to test
    }

    @Test
    public void findInmateById_returnsCorrectInmate_whenValidInmateIdPassed() {
        assertEquals(in4, prison.locateInmateByID(1004));
        assertEquals("Inmate4", prison.locateInmateByID(1004).getName());
    }

    @Test
    public void findInmateById_returnsPopup_whenIdNotFound() {
        prison.locateInmateByID(1010);
    }
}