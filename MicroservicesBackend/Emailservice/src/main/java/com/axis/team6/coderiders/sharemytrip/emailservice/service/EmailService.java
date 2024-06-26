package com.axis.team6.coderiders.sharemytrip.emailservice.service;


import com.axis.team6.coderiders.sharemytrip.emailservice.util.EmailUtil;
import com.axis.team6.coderiders.sharemytrip.emailservice.util.OtpUtil;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;


@Service
public class EmailService {

    @Autowired
    private OtpUtil otpUtil;
    @Autowired
    private EmailUtil emailUtil;



    private final ConcurrentHashMap<String, String> otpStorage = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Long> otpTimestamps = new ConcurrentHashMap<>();

    public String otpVerification(String email) {

        // Generate OTP
        String otp = generateOtp();

        // Store OTP in memory with timestamp
        otpStorage.put(email, otp);
        otpTimestamps.put(email, System.currentTimeMillis());

        // Send OTP email
        try {
            emailUtil.sendOtp(email, otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Error sending OTP email: " + email);
        }

        return "Otp sent successfully";
    }

    private String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // 6-digit OTP
        return String.valueOf(otp);
    }

    public String verifyOtp(String email, String otp) {
        String storedOtp = otpStorage.get(email);
        Long timestamp = otpTimestamps.get(email);

        if (storedOtp == null || timestamp == null) {
            throw new RuntimeException("Invalid OTP or email");
        }

        // Check if OTP is expired (10 minutes)
        long currentTime = System.currentTimeMillis();
        if (currentTime - timestamp > TimeUnit.MINUTES.toMillis(10)) {
            otpStorage.remove(email);
            otpTimestamps.remove(email);
            throw new RuntimeException("OTP has expired");
        }

        // Validate OTP
        if (!storedOtp.equals(otp)) {
            throw new RuntimeException("Invalid OTP");
        }

        // OTP is valid, clean up
        otpStorage.remove(email);
        otpTimestamps.remove(email);

        return "OTP verified successfully";
    }
}

