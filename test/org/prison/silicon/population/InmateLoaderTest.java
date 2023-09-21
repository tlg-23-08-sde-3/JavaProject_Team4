package org.prison.silicon.population;

import org.junit.Before;
import org.junit.Test;
import org.prison.silicon.facility.Prison;
import org.prison.silicon.SecurityRating;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;
import static org.prison.silicon.facility.Facility.*;
import static org.prison.silicon.facility.Facility.WORK_AREA;

public class InmateLoaderTest {
    Prison prison = new Prison("JailBreak", LOW_SECURITY_UNIT, MEDIUM_SECURITY_UNIT, HIGH_SECURITY_UNIT, YARD, KITCHEN, WORK_AREA);
    InmateLoader inLoader;
    List<Inmate> inmates;

    @Before
    public void setUp() throws Exception {
        inLoader = new InmateLoader(prison);
        inmates = inLoader.load();
    }

    @Test
    public void load_shouldReturnPopulatedList() throws IOException {
        assertEquals(75, inmates.size());
    }

    @Test
    public void loadInmates_valuesShouldAlign_whenIn0ComparedFromLoadedList() {
        Inmate in0 = inmates.get(0);
        assertEquals(1001, in0.getIdNumber());
        assertEquals("Blaine", in0.getName());
        assertFalse(in0.isGangLeader());
        assertEquals(SecurityRating.LOW, in0.getSecurityRating());
        assertEquals(LOW_SECURITY_UNIT, in0.getCurrentLocation());
        assertEquals(70, in0.getHappiness());
    }

    @Test
    public void loadInmates_valuesShouldAlign_whenIn1ComparedFromLoadedList() {
    Inmate in1 = inmates.get(42);
        assertEquals(2013, in1.getIdNumber());
        assertEquals("Karson", in1.getName());
        assertEquals(SecurityRating.MEDIUM, in1.getSecurityRating());
        assertTrue(in1.isGangLeader());
        assertEquals(MEDIUM_SECURITY_UNIT, in1.getCurrentLocation());
        assertEquals(50, in1.getHappiness());
    }
}