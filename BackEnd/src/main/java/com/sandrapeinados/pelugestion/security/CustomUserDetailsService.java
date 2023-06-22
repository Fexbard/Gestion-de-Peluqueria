package com.sandrapeinados.pelugestion.security;

import com.sandrapeinados.pelugestion.persistence.entities.Role;
import com.sandrapeinados.pelugestion.persistence.entities.Usuario;
import com.sandrapeinados.pelugestion.persistence.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private IUserRepository userRepo;
    @Autowired
    public CustomUserDetailsService(IUserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public Collection<GrantedAuthority> mapToAuthorities(List<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = userRepo.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return new User(usuario.getUsername(), usuario.getPassword(), mapToAuthorities(usuario.getRoles()));
    }
}
