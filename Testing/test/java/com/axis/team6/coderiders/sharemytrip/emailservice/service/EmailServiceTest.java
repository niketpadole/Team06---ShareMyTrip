package com.axis.team6.coderiders.sharemytrip.emailservice.service;

import com.axis.team6.coderiders.sharemytrip.emailservice.util.EmailUtil;
import com.axis.team6.coderiders.sharemytrip.emailservice.util.OtpUtil;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmailServiceTest {

    @Mock
    private OtpUtil otpUtil;

    @Mock
    private EmailUtil emailUtil;

    @InjectMocks
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void otpVerification() throws MessagingException {
//        String email = "test@example.com";
//        String otp = "123456";
//
//        // Mock the generateOtp method to return a fixed OTP
//        OngoingStubbing<T> otpSentSuccessfully = when(emailUtil.sendOtp(email, otp)).thenReturn("Otp sent successfully");
//
//        // Call the otpVerification method
//        String result = emailService.otpVerification(email);
//
//        // Verify the result
//        assertEquals("Otp sent successfully", result);
//
//        // Verify that the sendOtp method was called
//        verify(emailUtil, times(1)).sendOtp(email, otp);
//
//        // Verify that the OTP and timestamp were stored
//        assertNotNull(emailService.otpStorage.get(email));
//        assertNotNull(emailService.otpTimestamps.get(email));
//    }

    @Test
    void verifyOtp() {
        String email = "test@example.com";
        String otp = "123456";

        // Mock the OTP storage
        emailService.otpStorage.put(email, otp);
        emailService.otpTimestamps.put(email, System.currentTimeMillis());

        // Call the verifyOtp method
        String result = emailService.verifyOtp(email, otp);

        // Verify the result
        assertEquals("OTP verified successfully", result);

        // Verify that the OTP and timestamp were removed
        assertNull(emailService.otpStorage.get(email));
        assertNull(emailService.otpTimestamps.get(email));
    }

    @Test
    void verifyOtpExpired() {
        String email = "test@example.com";
        String otp = "123456";

        // Mock the OTP storage with expired timestamp
        emailService.otpStorage.put(email, otp);
        emailService.otpTimestamps.put(email, System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(11));

        // Call the verifyOtp method and expect an exception
        RuntimeException exception = assertThrows(RuntimeException.class, () -> emailService.verifyOtp(email, otp));

        // Verify the exception message
        assertEquals("OTP has expired", exception.getMessage());

        // Verify that the OTP and timestamp were removed
        assertNull(emailService.otpStorage.get(email));
        assertNull(emailService.otpTimestamps.get(email));
    }

    @Test
    void verifyOtpInvalid() {
        String email = "test@example.com";
        String otp = "123456";
        String invalidOtp = "654321";

        // Mock the OTP storage
        emailService.otpStorage.put(email, otp);
        emailService.otpTimestamps.put(email, System.currentTimeMillis());

        // Call the verifyOtp method and expect an exception
        RuntimeException exception = assertThrows(RuntimeException.class, () -> emailService.verifyOtp(email, invalidOtp));

        // Verify the exception message
        assertEquals("Invalid OTP", exception.getMessage());

        // Verify that the OTP and timestamp were not removed
        assertNotNull(emailService.otpStorage.get(email));
        assertNotNull(emailService.otpTimestamps.get(email));
    }
}
