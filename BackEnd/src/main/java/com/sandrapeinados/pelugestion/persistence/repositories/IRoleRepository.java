package com.sandrapeinados.pelugestion.persistence.repositories;

import com.sandrapeinados.pelugestion.persistence.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);

}
