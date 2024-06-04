package com.axis.team6.coderiders.sharemytrip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axis.team6.coderiders.sharemytrip.entity.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

    Publisher findByEmailAndPassword(String email, String password);
}
