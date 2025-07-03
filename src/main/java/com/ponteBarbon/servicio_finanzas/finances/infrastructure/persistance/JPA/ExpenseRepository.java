package com.ponteBarbon.servicio_finanzas.finances.infrastructure.persistance.JPA;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    List<Expense> getExpenseByIdUser(Long idUser);

    Optional<Expense> getExpenseById(Long id);

    boolean existsById(Long id);

    boolean existsByIdUser(Long idUser);
}
