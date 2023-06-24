package com.sandrapeinados.pelugestion.controllers;

import com.sandrapeinados.pelugestion.models.JwtAuthenticationResponse;
import com.sandrapeinados.pelugestion.models.SignInRequest;
import com.sandrapeinados.pelugestion.models.SignUpRequest;
import com.sandrapeinados.pelugestion.services.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final IAuthenticationService authenticationService;
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}
