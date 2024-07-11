package com.axis.team6.coderiders.sharemytrip.farecalculationservice.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistanceTest {

    @Test
    void getDistanceId() {
        Distance distance = new Distance();
        distance.setDistanceId(1);
        assertEquals(1, distance.getDistanceId());
    }

    @Test
    void getFromLocation() {
        Distance distance = new Distance();
        distance.setFromLocation("New York");
        assertEquals("New York", distance.getFromLocation());
    }

    @Test
    void getToLocation() {
        Distance distance = new Distance();
        distance.setToLocation("Los Angeles");
        assertEquals("Los Angeles", distance.getToLocation());
    }

    @Test
    void getDistance() {
        Distance distance = new Distance();
        distance.setDistance(100.0f);
        assertEquals(100.0f, distance.getDistance());
    }

    @Test
    void getFarePerSeats() {
        Distance distance = new Distance();
        distance.setFarePerSeats(50.0f);
        assertEquals(50.0f, distance.getFarePerSeats());
    }

    @Test
    void setDistanceId() {
        Distance distance = new Distance();
        distance.setDistanceId(2);
        assertEquals(2, distance.getDistanceId());
    }

    @Test
    void setFromLocation() {
        Distance distance = new Distance();
        distance.setFromLocation("San Francisco");
        assertEquals("San Francisco", distance.getFromLocation());
    }

    @Test
    void setToLocation() {
        Distance distance = new Distance();
        distance.setToLocation("Chicago");
        assertEquals("Chicago", distance.getToLocation());
    }

    @Test
    void setDistance() {
        Distance distance = new Distance();
        distance.setDistance(200.0f);
        assertEquals(200.0f, distance.getDistance());
    }

    @Test
    void setFarePerSeats() {
        Distance distance = new Distance();
        distance.setFarePerSeats(60.0f);
        assertEquals(60.0f, distance.getFarePerSeats());
    }

    @Test
    void testEquals() {
        Distance distance1 = new Distance(1, "Miami", "Orlando", 250.0f, 100.0f);
        Distance distance2 = new Distance(1, "Miami", "Orlando", 250.0f, 100.0f);
        Distance distance3 = new Distance(2, "Dallas", "Houston", 300.0f, 150.0f);

        assertEquals(distance1, distance2);
        assertNotEquals(distance1, distance3);
    }

    @Test
    void canEqual() {
        Distance distance1 = new Distance();
        Distance distance2 = new Distance();

        assertTrue(distance1.canEqual(distance2));
        assertFalse(distance1.canEqual(new Object()));
    }

    @Test
    void testHashCode() {
        Distance distance1 = new Distance(1, "Phoenix", "Tucson", 350.0f, 200.0f);
        Distance distance2 = new Distance(1, "Phoenix", "Tucson", 350.0f, 200.0f);

        assertEquals(distance1.hashCode(), distance2.hashCode());
    }

    @Test
    void testToString() {
        Distance distance = new Distance(1, "Seattle", "Portland", 400.0f, 250.0f);
        String expected = "Distance(distanceId=1, fromLocation=Seattle, toLocation=Portland, distance=400.0, farePerSeats=250.0)";
        assertEquals(expected, distance.toString());
    }
}
