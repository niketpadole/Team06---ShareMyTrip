package com.axis.team6.coderiders.sharemytrip.authserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.axis.team6.coderiders.sharemytrip.authserver.entity.UserCredential;
import com.axis.team6.coderiders.sharemytrip.authserver.repository.UserCredentialRepository;

@Service
public class AuthService {

    @Autowired
    private UserCredentialRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String saveUser(UserCredential credential) {
    	//System.out.println("creadential email"+credential.getEmail());
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        repository.save(credential);
        return "user added to the system";
    }

    // public String generateToken(String username) {
    //     return jwtService.generateToken(username);
    // }
    public String generateToken(String email, String userType) {
        return jwtService.generateToken(email + ":" + userType);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }


}
