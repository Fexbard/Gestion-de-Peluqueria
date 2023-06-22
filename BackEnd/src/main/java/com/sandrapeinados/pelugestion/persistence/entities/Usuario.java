package com.sandrapeinados.pelugestion.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    , inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "id_role"))
    private List<Role> roles = new ArrayList<>();

    public Usuario(String username, String password, Collection<GrantedAuthority> grantedAuthorities) {

    }
}
