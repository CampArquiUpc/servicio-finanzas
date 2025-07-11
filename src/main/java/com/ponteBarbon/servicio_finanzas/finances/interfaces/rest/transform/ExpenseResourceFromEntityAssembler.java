package com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.transform;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates.Expense;
import com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.resource.ExpenseResource;

public class ExpenseResourceFromEntityAssembler {
    public static ExpenseResource toResourceFromEntity(Expense entity) {
        ExpenseResource resource = new ExpenseResource();
        resource.setId(entity.getId());
        resource.setDescription(entity.getDescription());
        resource.setType(entity.getType() != null ? entity.getType().toString() : null);
        resource.setAmount(entity.getAmount());
        resource.setDateOfExpense(entity.getDateOfExpense());
        resource.setUserId(entity.getUser().getId());
        return resource;
    }
}
