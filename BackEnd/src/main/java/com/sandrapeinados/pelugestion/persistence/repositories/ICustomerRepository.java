package com.sandrapeinados.pelugestion.persistence.repositories;

import com.sandrapeinados.pelugestion.persistence.entities.CustomerEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICustomerRepository extends JpaRepository <CustomerEntity, Long> {

    @EntityGraph(attributePaths = "jobs")
    List<CustomerEntity> findAll();
}
