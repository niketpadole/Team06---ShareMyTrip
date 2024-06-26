package com.axis.team6.coderiders.sharemytrip.farecalculationservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.axis.team6.coderiders.sharemytrip.farecalculationservice.entity.Distance;

public interface DistanceRepository extends JpaRepository<Distance, Integer> {
    Distance findByFromLocationAndToLocation(String fromLocation, String toLocation);
}
