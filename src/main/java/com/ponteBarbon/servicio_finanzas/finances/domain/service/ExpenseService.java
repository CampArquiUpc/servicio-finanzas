package com.ponteBarbon.servicio_finanzas.finances.domain.service;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates.Expense;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.commands.CreateExpenseCommand;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.commands.CreateExpensebyAudioCommand;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.queries.GetExpenseByIdQuery;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.queries.GetExpenseByIdUserQuery;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ExpenseService {
    List<Expense> handle(GetExpenseByIdUserQuery query);
    Long handle(CreateExpenseCommand command);
    Optional<Expense> handle(GetExpenseByIdQuery query);
    String handle(CreateExpensebyAudioCommand command) throws IOException;
}
