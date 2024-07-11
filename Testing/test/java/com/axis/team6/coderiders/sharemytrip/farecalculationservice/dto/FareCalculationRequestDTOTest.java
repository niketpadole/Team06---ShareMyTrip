package com.axis.team6.coderiders.sharemytrip.farecalculationservice.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FareCalculationRequestDTOTest {

    @Test
    void getFromLocation() {
        FareCalculationRequestDTO requestDTO = new FareCalculationRequestDTO();
        requestDTO.setFromLocation("New York");
        assertEquals("New York", requestDTO.getFromLocation());
    }

    @Test
    void getToLocation() {
        FareCalculationRequestDTO requestDTO = new FareCalculationRequestDTO();
        requestDTO.setToLocation("Los Angeles");
        assertEquals("Los Angeles", requestDTO.getToLocation());
    }

    @Test
    void setFromLocation() {
        FareCalculationRequestDTO requestDTO = new FareCalculationRequestDTO();
        requestDTO.setFromLocation("San Francisco");
        assertEquals("San Francisco", requestDTO.getFromLocation());
    }

    @Test
    void setToLocation() {
        FareCalculationRequestDTO requestDTO = new FareCalculationRequestDTO();
        requestDTO.setToLocation("Chicago");
        assertEquals("Chicago", requestDTO.getToLocation());
    }

    @Test
    void testEquals() {
        FareCalculationRequestDTO requestDTO1 = new FareCalculationRequestDTO();
        requestDTO1.setFromLocation("Miami");
        requestDTO1.setToLocation("Orlando");

        FareCalculationRequestDTO requestDTO2 = new FareCalculationRequestDTO();
        requestDTO2.setFromLocation("Miami");
        requestDTO2.setToLocation("Orlando");

        FareCalculationRequestDTO requestDTO3 = new FareCalculationRequestDTO();
        requestDTO3.setFromLocation("Dallas");
        requestDTO3.setToLocation("Houston");

        assertEquals(requestDTO1, requestDTO2);
        assertNotEquals(requestDTO1, requestDTO3);
    }

    @Test
    void canEqual() {
        FareCalculationRequestDTO requestDTO1 = new FareCalculationRequestDTO();
        FareCalculationRequestDTO requestDTO2 = new FareCalculationRequestDTO();

        assertTrue(requestDTO1.canEqual(requestDTO2));
        assertFalse(requestDTO1.canEqual(new Object()));
    }

    @Test
    void testHashCode() {
        FareCalculationRequestDTO requestDTO1 = new FareCalculationRequestDTO();
        requestDTO1.setFromLocation("Phoenix");
        requestDTO1.setToLocation("Tucson");

        FareCalculationRequestDTO requestDTO2 = new FareCalculationRequestDTO();
        requestDTO2.setFromLocation("Phoenix");
        requestDTO2.setToLocation("Tucson");

        assertEquals(requestDTO1.hashCode(), requestDTO2.hashCode());
    }

    @Test
    void testToString() {
        FareCalculationRequestDTO requestDTO = new FareCalculationRequestDTO();
        requestDTO.setFromLocation("Seattle");
        requestDTO.setToLocation("Portland");

        String expected = "FareCalculationRequestDTO(fromLocation=Seattle, toLocation=Portland)";
        assertEquals(expected, requestDTO.toString());
    }
}
