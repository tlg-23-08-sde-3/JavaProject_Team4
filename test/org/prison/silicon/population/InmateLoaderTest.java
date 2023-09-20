package org.prison.silicon.population;

import org.junit.Test;
import org.prison.silicon.Facility;
import org.prison.silicon.FacilityList;
import org.prison.silicon.Prison;
import org.prison.silicon.SecurityRating;

import java.io.IOException;
import java.util.List;
import static org.junit.Assert.*;

public class InmateLoaderTest {
    Facility kitchen = new Facility(FacilityList.KITCHEN, 15, SecurityRating.MEDIUM);
    Facility yard = new Facility(FacilityList.YARD, 20, SecurityRating.MEDIUM);
    Facility work = new Facility(FacilityList.WORK_AREA, 15, SecurityRating.LOW);
    Facility low = new Facility(FacilityList.LOW_SECURITY_UNIT, 5, SecurityRating.LOW);
    Facility med = new Facility(FacilityList.LOW_SECURITY_UNIT, 5, SecurityRating.MEDIUM);
    Facility high = new Facility(FacilityList.HIGH_SECURITY_UNIT, 10, SecurityRating.HIGH);
    Prison prison = new Prison("JailBreak", kitchen, yard, work, low, med, high);

    @Test
    public void load_shouldReturnPopulatedList() throws IOException {
        InmateLoader inLoader = new InmateLoader("resources/data/inmate-data.csv", prison);
        List<Inmate> inmates = inLoader.load();
        assertEquals(50, inmates.size());

        Inmate in0 = inmates.get(0);
        assertEquals(1001, in0.getIdNumber());
        assertEquals("Blaine", in0.getName());
        assertFalse(in0.isGangLeader());
        assertEquals(SecurityRating.LOW, in0.getSecurityRating());
        assertEquals(FacilityList.LOW_SECURITY_UNIT, in0.getCurrentLocation());
        assertEquals(70, in0.getHappiness());

        Inmate in1 = inmates.get(11);
        assertEquals(1012, in1.getIdNumber());
        assertEquals("Darwin", in1.getName());
        assertEquals(SecurityRating.HIGH, in1.getSecurityRating());
        assertFalse(in1.isGangLeader());
        assertEquals(FacilityList.HIGH_SECURITY_UNIT, in1.getCurrentLocation());
        assertEquals(30, in1.getHappiness());
    }
}