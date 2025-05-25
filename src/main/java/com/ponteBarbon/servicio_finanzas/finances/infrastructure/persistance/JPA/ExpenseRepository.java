package com.ponteBarbon.servicio_finanzas.finances.infrastructure.persistance.JPA;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    Collection<Expense> getExpenseByIdUser(Long idUser);
}
