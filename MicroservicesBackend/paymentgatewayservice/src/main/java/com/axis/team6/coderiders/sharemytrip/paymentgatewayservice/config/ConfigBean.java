package com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Configuration
public class ConfigBean {

    @Value("${razorpay.key}")
    private String key;
    @Value("${razorpay.secret}")
    private String secret;
    @Bean
    public RazorpayClient getRazorpayClient(){
        try {
            return new RazorpayClient(key, secret);
        } catch (RazorpayException e) {
            System.out.println("Unable to create client for razorpay");
            throw new RuntimeException("Failed to instantiate razorpay");
        }
    }
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
