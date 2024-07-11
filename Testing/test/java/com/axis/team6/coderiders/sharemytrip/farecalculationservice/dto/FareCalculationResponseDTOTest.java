package com.axis.team6.coderiders.sharemytrip.farecalculationservice.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FareCalculationResponseDTOTest {

    @Test
    void getTotalFare() {
        FareCalculationResponseDTO responseDTO = new FareCalculationResponseDTO();
        responseDTO.setTotalFare(100.0f);
        assertEquals(100.0f, responseDTO.getTotalFare());
    }

    @Test
    void setTotalFare() {
        FareCalculationResponseDTO responseDTO = new FareCalculationResponseDTO();
        responseDTO.setTotalFare(200.0f);
        assertEquals(200.0f, responseDTO.getTotalFare());
    }

    @Test
    void testEquals() {
        FareCalculationResponseDTO responseDTO1 = new FareCalculationResponseDTO();
        responseDTO1.setTotalFare(100.0f);

        FareCalculationResponseDTO responseDTO2 = new FareCalculationResponseDTO();
        responseDTO2.setTotalFare(100.0f);

        FareCalculationResponseDTO responseDTO3 = new FareCalculationResponseDTO();
        responseDTO3.setTotalFare(200.0f);

        assertEquals(responseDTO1, responseDTO2);
        assertNotEquals(responseDTO1, responseDTO3);
    }

    @Test
    void canEqual() {
        FareCalculationResponseDTO responseDTO1 = new FareCalculationResponseDTO();
        FareCalculationResponseDTO responseDTO2 = new FareCalculationResponseDTO();

        assertTrue(responseDTO1.canEqual(responseDTO2));
        assertFalse(responseDTO1.canEqual(new Object()));
    }

    @Test
    void testHashCode() {
        FareCalculationResponseDTO responseDTO1 = new FareCalculationResponseDTO();
        responseDTO1.setTotalFare(100.0f);

        FareCalculationResponseDTO responseDTO2 = new FareCalculationResponseDTO();
        responseDTO2.setTotalFare(100.0f);

        assertEquals(responseDTO1.hashCode(), responseDTO2.hashCode());
    }

    @Test
    void testToString() {
        FareCalculationResponseDTO responseDTO = new FareCalculationResponseDTO();
        responseDTO.setTotalFare(100.0f);

        String expected = "FareCalculationResponseDTO(totalFare=100.0)";
        assertEquals(expected, responseDTO.toString());
    }
}
