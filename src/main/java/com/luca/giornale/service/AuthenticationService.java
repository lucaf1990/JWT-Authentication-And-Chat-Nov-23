package com.luca.giornale.service;

import com.luca.giornale.dto.JwtAuthenticationResponse;
import com.luca.giornale.dto.RefreshTokenRequest;
import com.luca.giornale.dto.SignUpRequest;
import com.luca.giornale.dto.SigninRequest;
import com.luca.giornale.entities.User;
import org.springframework.stereotype.Service;


public interface AuthenticationService {
    User signUp(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signin(SigninRequest signinRequest);
    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshToken);

}
