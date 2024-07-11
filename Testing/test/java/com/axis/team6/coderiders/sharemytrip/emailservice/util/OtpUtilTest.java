package com.axis.team6.coderiders.sharemytrip.emailservice.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OtpUtilTest {

    private OtpUtil otpUtil;

    @BeforeEach
    void setUp() {
        otpUtil = new OtpUtil();
    }

    @Test
    void generateOtp() {
        String otp = otpUtil.generateOtp();

        // Assert the OTP is not null
        assertNotNull(otp);

        // Assert the OTP is a 6-digit number
        assertEquals(6, otp.length());
        assertTrue(otp.matches("\\d{6}"), "OTP should be a 6-digit number");
    }
}
