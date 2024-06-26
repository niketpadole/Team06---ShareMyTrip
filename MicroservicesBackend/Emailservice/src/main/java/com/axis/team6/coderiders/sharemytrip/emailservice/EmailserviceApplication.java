package com.axis.team6.coderiders.sharemytrip.emailservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EmailserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailserviceApplication.class, args);
    }

}
