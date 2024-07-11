package com.axis.team6.coderiders.sharemytrip.authserver.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axis.team6.coderiders.sharemytrip.authserver.entity.UserCredential;

@Repository
public interface UserCredentialRepository  extends JpaRepository<UserCredential,Integer> {
 //    Optional<UserCredential> findByEmail(String email);
 //    Optional<UserCredential> deleteByEmail(String email);
	// List<UserCredential> findAllByEmail(String email);
    Optional<UserCredential> findByEmailAndUserType(String email, String userType);
    Optional<UserCredential> deleteByEmailAndUserType(String email, String userType);
}
