package com.sandrapeinados.pelugestion.persistence.repositories;

import com.sandrapeinados.pelugestion.persistence.entities.SubJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISubJobRepository extends JpaRepository<SubJobEntity,Long> {
}
