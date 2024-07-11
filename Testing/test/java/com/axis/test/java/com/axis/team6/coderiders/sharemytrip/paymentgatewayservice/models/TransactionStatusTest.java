package com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionStatusTest {

    @Test
    void values() {
        TransactionStatus[] expectedValues = {TransactionStatus.SUCCESS, TransactionStatus.FAILURE, TransactionStatus.INITIATED};
        TransactionStatus[] actualValues = TransactionStatus.values();
        assertArrayEquals(expectedValues, actualValues, "The values method did not return the expected values.");
    }

    @Test
    void valueOf() {
        assertEquals(TransactionStatus.SUCCESS, TransactionStatus.valueOf("SUCCESS"), "The valueOf method did not return the expected value for SUCCESS.");
        assertEquals(TransactionStatus.FAILURE, TransactionStatus.valueOf("FAILURE"), "The valueOf method did not return the expected value for FAILURE.");
        assertEquals(TransactionStatus.INITIATED, TransactionStatus.valueOf("INITIATED"), "The valueOf method did not return the expected value for INITIATED.");
    }
}
