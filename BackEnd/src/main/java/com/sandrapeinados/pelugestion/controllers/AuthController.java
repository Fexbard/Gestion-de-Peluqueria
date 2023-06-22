package com.sandrapeinados.pelugestion.controllers;

import com.sandrapeinados.pelugestion.models.AuthResponse;
import com.sandrapeinados.pelugestion.models.Login;
import com.sandrapeinados.pelugestion.models.Register;
import com.sandrapeinados.pelugestion.persistence.entities.Role;
import com.sandrapeinados.pelugestion.persistence.entities.Usuario;
import com.sandrapeinados.pelugestion.persistence.repositories.IRoleRepository;
import com.sandrapeinados.pelugestion.persistence.repositories.IUserRepository;
import com.sandrapeinados.pelugestion.security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private IRoleRepository roleRepository;
    private IUserRepository userRepository;
    private JwtGenerator jwtGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, IRoleRepository roleRepository, IUserRepository userRepository, JwtGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("registerAdm")
    public ResponseEntity<String> registrar(@RequestBody Register register) {
        if (userRepository.existsByUsername(register.getUsername())) {
            return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
        }
        Usuario usuario = new Usuario();
        usuario.setUsername(register.getUsername());
        usuario.setPassword(passwordEncoder.encode(register.getPassword()));
        Role roles = roleRepository.findByName("ADMIN").get();
        usuario.setRoles(Collections.singletonList(roles));
        userRepository.save(usuario);
        return new ResponseEntity<>("Successful registration", HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody Login login) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                login.getUsername(), login.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponse(token), HttpStatus.OK);
    }
}
