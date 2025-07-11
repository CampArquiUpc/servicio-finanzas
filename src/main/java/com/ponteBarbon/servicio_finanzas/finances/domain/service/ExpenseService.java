package com.ponteBarbon.servicio_finanzas.finances.domain.service;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates.Expense;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.commands.*;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.queries.GetExpenseByIdQuery;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.queries.GetExpenseByIdUserQuery;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ExpenseService {
    List<Expense> handle(GetExpenseByIdUserQuery query);

    Long handle(CreateExpenseCommand command, Long userId);

    Optional<Expense> handle(GetExpenseByIdQuery query);


    void hanlde(DeleteExpenseCommandById command);

    Optional<Expense> handle(UpdateExpenseCommandById command);

    List<Expense> handle(com.ponteBarbon.servicio_finanzas.finances.domain.model.queries.GetExpensesByUserIdQuery query);
}
