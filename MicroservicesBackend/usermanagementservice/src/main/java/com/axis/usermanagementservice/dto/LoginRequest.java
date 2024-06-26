package com.axis.usermanagementservice.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginRequest {
	@NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;
	@NotBlank(message = "Password cannot be blank")
    private String password;
}
