package com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.transform;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates.Expense;
import com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.resource.ExpenseResource;

public class ExpenseResourceFromEntityAssembler {
    public static ExpenseResource toResourceFromEntity(Expense entity) {
        return new ExpenseResource(
                entity.getId(),
                entity.getDescription(),
                entity.getType() != null ? entity.getType().toString() : null,
                entity.getAmount(),
                entity.getDateOfExpense(),
                entity.getIdUser()
        );

    }
}
