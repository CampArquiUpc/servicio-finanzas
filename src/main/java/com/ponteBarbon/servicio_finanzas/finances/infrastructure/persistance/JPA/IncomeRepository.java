package com.ponteBarbon.servicio_finanzas.finances.infrastructure.persistance.JPA;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findByUser_Id(Long userId);
}
