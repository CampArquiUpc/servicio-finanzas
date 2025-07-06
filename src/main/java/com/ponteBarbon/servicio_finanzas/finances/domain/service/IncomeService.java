package com.ponteBarbon.servicio_finanzas.finances.domain.service;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates.Income;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.commands.CreateIncomeCommand;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.queries.GetIncomesByUserIdQuery;
import java.util.List;

public interface IncomeService {
    Long handle(CreateIncomeCommand command);
    List<Income> handle(GetIncomesByUserIdQuery query);
}

