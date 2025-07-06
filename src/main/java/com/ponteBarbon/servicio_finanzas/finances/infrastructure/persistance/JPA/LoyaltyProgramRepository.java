package com.ponteBarbon.servicio_finanzas.finances.infrastructure.persistance.JPA;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates.LoyaltyProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LoyaltyProgramRepository extends JpaRepository<LoyaltyProgram, Long> {
    Optional<LoyaltyProgram> findByUser_Id(Long userId);
}
