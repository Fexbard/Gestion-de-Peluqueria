package com.sandrapeinados.pelugestion.persistence.repositories;

import com.sandrapeinados.pelugestion.persistence.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByUsername(String username);
    Boolean existsByUsername(String username);

}
