package com.axis.team6.coderiders.sharemytrip.authserver.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.axis.team6.coderiders.sharemytrip.authserver.entity.UserCredential;
import com.axis.team6.coderiders.sharemytrip.authserver.repository.UserCredentialRepository;

import jakarta.transaction.Transactional;

@Service
public class UserCredentialServiceImpl{
	@Autowired
	private UserCredentialRepository userCredentialRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	 public void saveUserCredential(UserCredential userCredential) {
	        userCredentialRepository.save(userCredential);
	    }

	// @Transactional
	// public String updateUserCredential(UserCredential user) {
	// 	Optional<UserCredential> existingUserOpt = userCredentialRepository.findByEmail(user.getEmail());
	// 	existingUserOpt.ifPresent(userCredentialRepository::delete);
	// 	UserCredential updatedUser = new UserCredential(); // Correct initialization
	// 	updatedUser.setPassword(passwordEncoder.encode(user.getPassword()));
	// 	updatedUser.setEmail(user.getEmail());
	// 	userCredentialRepository.save(updatedUser);
	// 	return "User Update Sucess";
	// }
		@Transactional
	public String updateUserCredential(UserCredential user) {
		Optional<UserCredential> existingUserOpt = userCredentialRepository.findByEmailAndUserType(user.getEmail(), user.getUserType());
		existingUserOpt.ifPresent(userCredentialRepository::delete);
		UserCredential updatedUser = new UserCredential();
		updatedUser.setPassword(passwordEncoder.encode(user.getPassword()));
		updatedUser.setEmail(user.getEmail());
		updatedUser.setUserType(user.getUserType());
		userCredentialRepository.save(updatedUser);
		return "User Update Success";
	}

	// public List<UserCredential> findAllByEmail(String email) {
	// return userCredentialRepository.findAllByEmail(email);
	// }
}
