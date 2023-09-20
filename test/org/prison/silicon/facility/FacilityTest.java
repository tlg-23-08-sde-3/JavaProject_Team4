package org.prison.silicon.facility;

import org.junit.Before;
import org.junit.Test;
import org.prison.silicon.SecurityRating;
import org.prison.silicon.facility.Facility;
import org.prison.silicon.facility.FacilityList;
import org.prison.silicon.facility.Prison;
import org.prison.silicon.population.HighSecurityInmate;
import org.prison.silicon.population.Inmate;
import org.prison.silicon.population.LowSecurityInmate;
import org.prison.silicon.population.MediumSecurityInmate;

import java.util.Map;

import static org.junit.Assert.*;

public class FacilityTest {
    Map<Integer, Inmate> testMap;
    Inmate in1, in2, in3;
    Prison prison;

    @Before
    public void setUp() throws Exception {
        in1 = new LowSecurityInmate(1001, "Inmate1", false, SecurityRating.LOW, prison);
        in2 = new MediumSecurityInmate(1002, "Inmate2", false, SecurityRating.MEDIUM, prison);
        in3 = new HighSecurityInmate(1003, "Inmate3", true, SecurityRating.HIGH, prison);
    }

    @Test
    public void facility_validFacilityCreated_withThreeArgContructor() {
        Facility kitchen = new Facility(FacilityList.KITCHEN, 15, SecurityRating.LOW);
        assertEquals(FacilityList.KITCHEN, kitchen.getName());
        assertEquals(15, kitchen.getMaxCapacity());
        assertEquals(SecurityRating.LOW, kitchen.getSecurityRating());
    }

    @Test
    public void facility_emptyInmateMapCreated_whenFacilityCreated() {
        Facility kitchen = new Facility(FacilityList.KITCHEN, 15, SecurityRating.LOW);
        assertEquals(0, kitchen.getInmateMap().size());
    }

    @Test
    public void addInmate_inmateAddedToCurrentInmatesMap_whenSingleInmatePassed() {
        Facility kitchen = new Facility(FacilityList.KITCHEN, 15, SecurityRating.LOW);
        prison = new Prison("JailBreak", kitchen);
        kitchen.addInmate(in1);
        assertEquals(1, kitchen.getInmateMap().size());
        assertArrayEquals(new Integer[]{1001}, kitchen.getInmatesIds());
    }

    @Test
    public void addInmate_inmatesAddedToCurrentInmatesMap_whenMultipleInmatesPassed() {
        Facility kitchen = new Facility(FacilityList.KITCHEN, 15, SecurityRating.LOW);
        prison = new Prison("JailBreak", kitchen);
        kitchen.addInmate(in1, in2, in3);
        assertEquals(3, kitchen.getInmateMap().size());
        assertArrayEquals(new Integer[]{1001, 1002, 1003}, kitchen.getInmatesIds());
    }

    @Test
    public void removeInmate_inmateRemovedFromCurrentInmatesMap_whenSingleInmatePassed() {
        Facility kitchen = new Facility(FacilityList.KITCHEN, 15, SecurityRating.LOW);
        kitchen.addInmate(in1);
        kitchen.removeInmate(1001);
        assertEquals(0, kitchen.getInmateMap().size());
        assertArrayEquals(new Integer[]{}, kitchen.getInmatesIds());
    }

    @Test
    public void removeInmate_inmateRemovedFromCurrentInmatesMap_whenMultipleInmatePassed() {
        Facility kitchen = new Facility(FacilityList.KITCHEN, 15, SecurityRating.LOW);
        kitchen.addInmate(in1, in2, in3);
        assertEquals(3, kitchen.getInmateMap().size());
        assertArrayEquals(new Integer[]{1001, 1002, 1003}, kitchen.getInmatesIds());
        kitchen.removeInmate(1001, 1002);
        assertEquals(1, kitchen.getInmateMap().size());
        assertArrayEquals(new Integer[]{1003}, kitchen.getInmatesIds());
    }

    @Test
    public void calculateRiskRating_providesValidRiskRating_whenCurrentInmateMapPopulated() {
        Facility kitchen = new Facility(FacilityList.KITCHEN, 15, SecurityRating.LOW);
        kitchen.addInmate(in1, in2, in3);
        kitchen.calculateRiskRating();
        assertEquals(60, kitchen.getRiskRating());
    }

    @Test
    public void displayCurrentInmates_currentInmatesDisplayProperly() {
        Facility kitchen = new Facility(FacilityList.KITCHEN, 15, SecurityRating.LOW);
        kitchen.addInmate(in1, in2, in3);
        kitchen.displayCurrentInmates();
    }
}