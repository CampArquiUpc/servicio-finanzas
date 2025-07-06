package com.ponteBarbon.servicio_finanzas.finances.infrastructure.persistance.JPA;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates.FinancialGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FinancialGoalRepository extends JpaRepository<FinancialGoal, Long> {
    List<FinancialGoal> findByUser_Id(Long userId);
}
