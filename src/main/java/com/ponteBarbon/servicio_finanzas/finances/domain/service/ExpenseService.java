package com.ponteBarbon.servicio_finanzas.finances.domain.service;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates.Expense;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.commands.CreateExpenseCommand;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.queries.GetExpenseByIdUserQuery;

import java.util.Optional;

public interface ExpenseService {
    Optional<Expense> handle(GetExpenseByIdUserQuery query);
    Long handle(CreateExpenseCommand command);
}
