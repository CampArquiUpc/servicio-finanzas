package com.ponteBarbon.servicio_finanzas.finances.infrastructure.persistance.JPA;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates.UserInsight;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserInsightRepository extends JpaRepository<UserInsight, Long> {
    Optional<UserInsight> findByUser_Id(Long userId);
}
