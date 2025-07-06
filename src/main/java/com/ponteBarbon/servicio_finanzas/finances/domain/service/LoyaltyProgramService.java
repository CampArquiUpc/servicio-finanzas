package com.ponteBarbon.servicio_finanzas.finances.domain.service;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates.LoyaltyProgram;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.commands.CreateLoyaltyProgramCommand;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.queries.GetLoyaltyProgramByUserIdQuery;
import java.util.Optional;

public interface LoyaltyProgramService {
    Long handle(CreateLoyaltyProgramCommand command);
    Optional<LoyaltyProgram> handle(GetLoyaltyProgramByUserIdQuery query);
}

