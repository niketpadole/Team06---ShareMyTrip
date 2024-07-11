package com.axis.team6.coderiders.sharemytrip.authserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

import com.axis.team6.coderiders.sharemytrip.authserver.dto.AuthRequest;
import com.axis.team6.coderiders.sharemytrip.authserver.entity.UserCredential;
import com.axis.team6.coderiders.sharemytrip.authserver.service.AuthService;
import com.axis.team6.coderiders.sharemytrip.authserver.service.UserCredentialServiceImpl;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserCredentialServiceImpl userCredentialService;

    @PostMapping("/register")
    public String addNewUser(@RequestBody UserCredential user) {
    	userCredentialService.saveUserCredential(user);
        return service.saveUser(user);
    }

    // @PostMapping("/token")
    // public String getToken(@RequestBody AuthRequest authRequest) {
    //     Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
    //     if (authenticate.isAuthenticated()) {
    //         return service.generateToken(authRequest.getEmail());
    //     } else {
    //         throw new RuntimeException("invalid access");
    //     }
    // }
    // @PostMapping("/token")
    // public String getToken(@RequestBody AuthRequest authRequest) {
    //     List<UserCredential> users = userCredentialService.findAllByEmail(authRequest.getEmail());
    //     if (users.isEmpty()) {
    //         throw new RuntimeException("User not found");
    //     }

    //     for (UserCredential user : users) {
    //         Authentication authenticate = authenticationManager.authenticate(
    //             new UsernamePasswordAuthenticationToken(user.getEmail(), authRequest.getPassword()));
    //         if (authenticate.isAuthenticated()) {
    //             return service.generateToken(user.getEmail());
    //         }
    //     }
    //     throw new RuntimeException("Invalid access");
    // }
     @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest) {
        String usernameWithUserType = authRequest.getEmail() + ":" + authRequest.getUserType();
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usernameWithUserType, authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return service.generateToken(authRequest.getEmail(), authRequest.getUserType());
        } else {
            throw new RuntimeException("Invalid access");
        }
    }

    @PostMapping("/update")
    public String updateUser(@RequestBody UserCredential user)
    {
        return userCredentialService.updateUserCredential(user);
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        service.validateToken(token);
        return "Token is valid";
    }
}
