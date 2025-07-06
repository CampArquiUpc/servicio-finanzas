package com.ponteBarbon.servicio_finanzas.finances.domain.service;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates.FinancialGoal;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.commands.CreateFinancialGoalCommand;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.queries.GetFinancialGoalsByUserIdQuery;
import java.util.List;

public interface FinancialGoalService {
    Long handle(CreateFinancialGoalCommand command);
    List<FinancialGoal> handle(GetFinancialGoalsByUserIdQuery query);
}

