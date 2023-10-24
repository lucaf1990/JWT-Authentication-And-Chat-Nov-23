package com.luca.giornale.controller;

import com.luca.giornale.dto.JwtAuthenticationResponse;
import com.luca.giornale.dto.RefreshTokenRequest;
import com.luca.giornale.dto.SignUpRequest;
import com.luca.giornale.dto.SigninRequest;
import com.luca.giornale.entities.User;
import com.luca.giornale.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(allowedHeaders ="*")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
    }
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SigninRequest signInRequest){
        return ResponseEntity.ok(authenticationService.signin(signInRequest));
    }
    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshToken){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshToken));
    }
}
