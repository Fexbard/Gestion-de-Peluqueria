package com.sandrapeinados.pelugestion.persistence.repositories;

import com.sandrapeinados.pelugestion.persistence.entities.CustomerEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ICustomerRepository extends JpaRepository <CustomerEntity, Long> {

    @EntityGraph(attributePaths = "jobs")
    List<CustomerEntity> findAll();

    @Query("SELECT new com.sandrapeinados.pelugestion.persistence.entities.CustomerEntity(c.id, c.name, c.surname, c.cellphone) FROM CustomerEntity c WHERE c.id =?1")
    Optional<CustomerEntity> getOnlyDetailsCustomer(Long id);

}
