package org.prison.silicon.facility;

import org.junit.Before;
import org.junit.Test;
import org.prison.silicon.SecurityRating;
import org.prison.silicon.population.HighSecurityInmate;
import org.prison.silicon.population.Inmate;
import org.prison.silicon.population.LowSecurityInmate;
import org.prison.silicon.population.MediumSecurityInmate;

import static org.prison.silicon.facility.Facility.*;

import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class FacilityTest {
    Map<Integer, Inmate> testMap;
    Inmate in1, in2, in3;
    Prison prison;

    @Before
    public void setUp() throws Exception {
        prison = new Prison("JailBreak", KITCHEN);
        in1 = new LowSecurityInmate(1001, "Inmate1", false, SecurityRating.LOW, prison);
        in2 = new MediumSecurityInmate(1002, "Inmate2", false, SecurityRating.MEDIUM, prison);
        in3 = new HighSecurityInmate(1003, "Inmate3", true, SecurityRating.HIGH, prison);
    }

    @Test
    public void facility_validFacilityCreated_withThreeArgContructor() {
        assertEquals("Kitchen", KITCHEN.getName());
        assertEquals(30, KITCHEN.getMaxCapacity());
        assertEquals(SecurityRating.MEDIUM, KITCHEN.getSecurityRating());
    }

    @Test
    public void facility_emptyInmateMapCreated_whenFacilityCreated() {
        assertEquals(0, KITCHEN.getInmateMap().size());
    }

    @Test
    public void addInmate_inmateAddedToCurrentInmatesMap_whenSingleInmatePassed() {
        KITCHEN.currentInmates.clear();
        KITCHEN.addInmate(in1);
        assertEquals(1, KITCHEN.getInmateMap().size());
        assertArrayEquals(new Integer[]{1001}, KITCHEN.getInmatesIds());
    }

    @Test
    public void addInmate_inmatesAddedToCurrentInmatesMap_whenMultipleInmatesPassed() {
        KITCHEN.currentInmates.clear();
        KITCHEN.addInmate(in1, in2, in3);
        assertEquals(3, KITCHEN.getInmateMap().size());
        assertArrayEquals(new Integer[]{1001, 1002, 1003}, KITCHEN.getInmatesIds());
    }

    @Test
    public void removeInmate_inmateRemovedFromCurrentInmatesMap_whenSingleInmatePassed() {
        KITCHEN.currentInmates.clear();
        KITCHEN.addInmate(in1);
        KITCHEN.removeInmate(1001);
        assertEquals(0, KITCHEN.getInmateMap().size());
        assertArrayEquals(new Integer[]{}, KITCHEN.getInmatesIds());
    }

    @Test
    public void removeInmate_inmateRemovedFromCurrentInmatesMap_whenMultipleInmatePassed() {
        KITCHEN.currentInmates.clear();
        KITCHEN.addInmate(in1, in2, in3);
        assertEquals(3, KITCHEN.getInmateMap().size());
        assertArrayEquals(new Integer[]{1001, 1002, 1003}, KITCHEN.getInmatesIds());
        KITCHEN.removeInmate(1001, 1002);
        assertEquals(1, KITCHEN.getInmateMap().size());
        assertArrayEquals(new Integer[]{1003}, KITCHEN.getInmatesIds());
    }

    @Test
    public void calculateRiskRating_providesValidRiskRating_whenCurrentInmateMapPopulated() {
        KITCHEN.addInmate(in1, in2, in3);
        KITCHEN.calculateRiskRating();
        assertEquals(42, KITCHEN.getRiskRating());
    }

    @Test
    public void displayCurrentInmates_currentInmatesDisplayProperly() {
        KITCHEN.addInmate(in1, in2, in3);
        KITCHEN.displayCurrentInmates();
    }
}