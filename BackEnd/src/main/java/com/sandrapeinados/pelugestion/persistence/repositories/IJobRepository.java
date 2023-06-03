package com.sandrapeinados.pelugestion.persistence.repositories;

import com.sandrapeinados.pelugestion.persistence.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IJobRepository extends JpaRepository<JobEntity,Long> {
}
