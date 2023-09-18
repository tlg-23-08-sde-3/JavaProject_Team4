package org.prison.silicon.population;

import org.junit.Test;
import org.prison.silicon.FacilityList;
import org.prison.silicon.SecurityRating;

import java.io.IOException;
import java.util.List;
import static org.junit.Assert.*;

public class InmateLoaderTest {

    @Test
    public void load_shouldReturnPopulatedList() throws IOException {
        InmateLoader inLoader = new InmateLoader("src/org/prison/silicon/data/inmate-data.csv");
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
        assertTrue(in1.isGangLeader());
        assertEquals(FacilityList.HIGH_SECURITY_UNIT, in1.getCurrentLocation());
        assertEquals(30, in1.getHappiness());
    }
}