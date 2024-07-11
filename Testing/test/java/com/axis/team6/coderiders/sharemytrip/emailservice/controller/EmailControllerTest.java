package com.axis.team6.coderiders.sharemytrip.emailservice.controller;

import com.axis.team6.coderiders.sharemytrip.emailservice.service.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmailController.class)
class EmailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmailService emailService;

    @Test
    void sendOtp() throws Exception {
        String email = "test@example.com";
        String responseMessage = "OTP sent successfully";

        when(emailService.otpVerification(email)).thenReturn(responseMessage);

        mockMvc.perform(post("/email/send-otp/{email}", email)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(responseMessage));
    }

    @Test
    void verifyOtp() throws Exception {
        String email = "test@example.com";
        String otp = "123456";
        String responseMessage = "OTP verified successfully";

        when(emailService.verifyOtp(email, otp)).thenReturn(responseMessage);

        mockMvc.perform(post("/email/verify-otp/{email}/{otp}", email, otp)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(responseMessage));
    }
}
