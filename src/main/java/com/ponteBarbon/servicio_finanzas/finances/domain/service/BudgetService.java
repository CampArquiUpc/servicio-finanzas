package com.ponteBarbon.servicio_finanzas.finances.domain.service;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates.Budget;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.commands.CreateBudgetCommand;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.queries.GetBudgetByUserIdQuery;
import java.util.Optional;

public interface BudgetService {
    Long handle(CreateBudgetCommand command);
    Optional<Budget> handle(GetBudgetByUserIdQuery query);
}

