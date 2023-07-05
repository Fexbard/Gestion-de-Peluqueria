package com.sandrapeinados.pelugestion.controllers;

import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.sandrapeinados.pelugestion.models.JwtAuthenticationResponse;
import com.sandrapeinados.pelugestion.models.SignInRequest;
import com.sandrapeinados.pelugestion.models.SignUpRequest;
import com.sandrapeinados.pelugestion.services.IAuthenticationService;
import com.sandrapeinados.pelugestion.services.IJwtService;
import com.sandrapeinados.pelugestion.services.IUserService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final IAuthenticationService authenticationService;

    @Autowired
    private IJwtService jwtService;

    @Autowired
    private IUserService userService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }

    @PostMapping("/validate-token")
    @ResponseBody
    public ResponseEntity<?> validateToken(@RequestBody String token) {
        boolean isValid;
        try {
        String username = jwtService.extractUserName(token);
        UserDetails user = userService.userDetailsService().loadUserByUsername(username);

            isValid = jwtService.isTokenValid(token, user);
        } catch (ExpiredJwtException e) {
            isValid = false;
        }

        return ResponseEntity.ok(isValid);
        //return ResponseEntity.ok(Collections.singletonMap("isValid", true));
    }
}
