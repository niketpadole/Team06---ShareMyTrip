package com.axis.team6.coderiders.sharemytrip.authserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axis.team6.coderiders.sharemytrip.authserver.entity.UserCredential;
import com.axis.team6.coderiders.sharemytrip.authserver.repository.UserCredentialRepository;

@Service
public class UserCredentialServiceImpl{
	@Autowired
	private UserCredentialRepository userCredentialRepository;
	
	 public void saveUserCredential(UserCredential userCredential) {
	        userCredentialRepository.save(userCredential);
	    }
}
