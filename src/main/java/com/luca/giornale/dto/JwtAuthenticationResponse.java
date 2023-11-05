package com.luca.giornale.dto;

import org.springframework.security.core.userdetails.UserDetails;

import com.luca.giornale.entities.User;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
	private String token;
	private String refreshToken;
	private UserDetails user;
	private Long id;
}
