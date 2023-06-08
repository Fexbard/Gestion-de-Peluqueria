package com.sandrapeinados.pelugestion.persistence.repositories;

import com.sandrapeinados.pelugestion.persistence.entities.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExpenseRepository extends JpaRepository<ExpenseEntity,Long> {
}
