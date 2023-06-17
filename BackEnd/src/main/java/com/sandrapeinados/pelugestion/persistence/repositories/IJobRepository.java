package com.sandrapeinados.pelugestion.persistence.repositories;

import com.sandrapeinados.pelugestion.persistence.entities.JobEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IJobRepository extends JpaRepository<JobEntity,Long> {

    @EntityGraph(attributePaths = "subJobs")
    List<JobEntity> findAll();

    @EntityGraph(attributePaths = "subJobs")
    Page<JobEntity> findAll(Pageable pageable);

}
