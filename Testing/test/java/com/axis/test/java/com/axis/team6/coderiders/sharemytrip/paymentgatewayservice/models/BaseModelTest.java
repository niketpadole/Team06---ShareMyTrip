package com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseModelTest {

    @Test
    void getId() {
        BaseModel baseModel = new BaseModel();
        long expectedId = 1L;
        baseModel.setId(expectedId);
        assertEquals(expectedId, baseModel.getId(), "The getId method did not return the expected value.");
    }

    @Test
    void setId() {
        BaseModel baseModel = new BaseModel();
        long expectedId = 2L;
        baseModel.setId(expectedId);
        assertEquals(expectedId, baseModel.getId(), "The setId method did not set the id correctly.");
    }
}
