package com.axis.team6.coderiders.sharemytrip.farecalculationservice.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistanceDTOTest {

    @Test
    void getDistance() {
        DistanceDTO distanceDTO = new DistanceDTO();
        distanceDTO.setDistance(100.0f);
        assertEquals(100.0f, distanceDTO.getDistance());
    }

    @Test
    void setDistance() {
        DistanceDTO distanceDTO = new DistanceDTO();
        distanceDTO.setDistance(200.0f);
        assertEquals(200.0f, distanceDTO.getDistance());
    }

    @Test
    void testEquals() {
        DistanceDTO distanceDTO1 = new DistanceDTO(100.0f);
        DistanceDTO distanceDTO2 = new DistanceDTO(100.0f);
        DistanceDTO distanceDTO3 = new DistanceDTO(200.0f);

        assertEquals(distanceDTO1, distanceDTO2);
        assertNotEquals(distanceDTO1, distanceDTO3);
    }

    @Test
    void canEqual() {
        DistanceDTO distanceDTO1 = new DistanceDTO(100.0f);
        DistanceDTO distanceDTO2 = new DistanceDTO(100.0f);

        assertTrue(distanceDTO1.canEqual(distanceDTO2));
        assertFalse(distanceDTO1.canEqual(new Object()));
    }

    @Test
    void testHashCode() {
        DistanceDTO distanceDTO1 = new DistanceDTO(100.0f);
        DistanceDTO distanceDTO2 = new DistanceDTO(100.0f);

        assertEquals(distanceDTO1.hashCode(), distanceDTO2.hashCode());
    }

    @Test
    void testToString() {
        DistanceDTO distanceDTO = new DistanceDTO(100.0f);
        String expected = "DistanceDTO(distance=100.0)";
        assertEquals(expected, distanceDTO.toString());
    }
}
