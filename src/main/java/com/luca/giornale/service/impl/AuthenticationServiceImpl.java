package com.luca.giornale.service.impl;

import com.luca.giornale.dto.JwtAuthenticationResponse;
import com.luca.giornale.dto.RefreshTokenRequest;
import com.luca.giornale.dto.SignUpRequest;
import com.luca.giornale.dto.SigninRequest;
import com.luca.giornale.entities.User;
import com.luca.giornale.enums.Role;
import com.luca.giornale.repository.UserRepository;
import com.luca.giornale.service.AuthenticationService;
import com.luca.giornale.service.JWTService;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
private final  JWTService jwtService;
    public User signUp(SignUpRequest signUpRequest){
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
user.setLastName(signUpRequest.getLastName());
user.setFirstName(signUpRequest.getFirstName());
user.setRole(Role.USER);
user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

return userRepository.save(user);

    }

public JwtAuthenticationResponse signin(SigninRequest signinRequest) {
	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));
	var user= userRepository.findByEmail(signinRequest.getEmail());
var jwt= jwtService.generateToken(user);
var refreshToken= jwtService.generateRefreshToken(new HashMap<>(),user);

JwtAuthenticationResponse jwtAuthenticationResponse= new JwtAuthenticationResponse();
jwtAuthenticationResponse.setToken(jwt);
jwtAuthenticationResponse.setRefreshToken(refreshToken);
return jwtAuthenticationResponse;
	
	
	
}

public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshToken) {
	String userEmail= jwtService.extractUserName(refreshToken.getToken());
	User user= (User) userRepository.findByEmail(userEmail);
	if(jwtService.isTokenValid(refreshToken.getToken(), user)) {
		var jwt= jwtService.generateToken(user);
		
		JwtAuthenticationResponse jwtAuthenticationResponse= new JwtAuthenticationResponse();
		jwtAuthenticationResponse.setToken(jwt);
		jwtAuthenticationResponse.setRefreshToken(refreshToken.getToken());
		return jwtAuthenticationResponse;
			
	}
	return null;
}



}
