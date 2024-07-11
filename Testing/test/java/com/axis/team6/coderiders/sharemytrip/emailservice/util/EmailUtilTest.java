package com.axis.team6.coderiders.sharemytrip.emailservice.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmailUtilTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailUtil emailUtil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void sendOtp() throws MessagingException {
        String email = "test@example.com";
        String otp = "123456";

        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        doNothing().when(mailSender).send(mimeMessage);

        emailUtil.sendOtp(email, otp);

        ArgumentCaptor<MimeMessage> mimeMessageArgumentCaptor = ArgumentCaptor.forClass(MimeMessage.class);
        verify(mailSender, times(1)).send(mimeMessageArgumentCaptor.capture());

        MimeMessage capturedMessage = mimeMessageArgumentCaptor.getValue();
        MimeMessageHelper capturedHelper = new MimeMessageHelper(capturedMessage, true);
//
//        assertEquals(email, capturedHelper.getTo()[0]);
//        assertEquals("Your OTP Code", capturedHelper.getSubject());
//        assertEquals("Your OTP code is: " + otp, capturedHelper.getText());
    }
}
