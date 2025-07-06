package com.ponteBarbon.servicio_finanzas.finances.domain.service;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates.UserInsight;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.commands.CreateUserInsightCommand;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.queries.GetUserInsightByUserIdQuery;
import java.util.Optional;

public interface UserInsightService {
    Long handle(CreateUserInsightCommand command);
    Optional<UserInsight> handle(GetUserInsightByUserIdQuery query);
}

