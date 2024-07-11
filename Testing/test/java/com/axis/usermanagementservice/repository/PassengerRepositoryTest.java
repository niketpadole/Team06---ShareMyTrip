//package com.axis.usermanagementservice.repository;
//
//import com.axis.usermanagementservice.entity.Passenger;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//class PassengerRepositoryTest {
//
//    @Autowired
//    private PassengerRepository passengerRepository;
//
//    @Test
//    void findByEmail() {
//        // Given
//        Passenger passenger = new Passenger();
//        passenger.setEmail("jane.doe@example.com");
//        passenger.setPassword("password123");
//        passenger.setFirstName("Jane");
//        passenger.setLastName("Doe");
//        passengerRepository.save(passenger);
//
//        // When
//        Passenger foundPassenger = passengerRepository.findByEmail("jane.doe@example.com");
//
//        // Then
//        assertNotNull(foundPassenger);
//        assertEquals("jane.doe@example.com", foundPassenger.getEmail());
//        assertEquals("password123", foundPassenger.getPassword());
//        assertEquals("Jane", foundPassenger.getFirstName());
//        assertEquals("Doe", foundPassenger.getLastName());
//    }
//}
