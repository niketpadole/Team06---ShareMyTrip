package com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentStatusTest {

    @Test
    void values() {
        PaymentStatus[] expectedValues = {PaymentStatus.SUCCESS, PaymentStatus.FAILURE, PaymentStatus.INITIATED};
        PaymentStatus[] actualValues = PaymentStatus.values();
        assertArrayEquals(expectedValues, actualValues, "The values method did not return the expected values.");
    }

    @Test
    void valueOf() {
        assertEquals(PaymentStatus.SUCCESS, PaymentStatus.valueOf("SUCCESS"), "The valueOf method did not return the expected value for SUCCESS.");
        assertEquals(PaymentStatus.FAILURE, PaymentStatus.valueOf("FAILURE"), "The valueOf method did not return the expected value for FAILURE.");
        assertEquals(PaymentStatus.INITIATED, PaymentStatus.valueOf("INITIATED"), "The valueOf method did not return the expected value for INITIATED.");
    }
}
