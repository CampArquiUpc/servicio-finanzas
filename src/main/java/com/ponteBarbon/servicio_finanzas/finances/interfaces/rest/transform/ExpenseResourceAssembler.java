package com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.transform;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates.Expense;
import com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.resource.ExpenseResource;

public class ExpenseResourceAssembler {
    public static ExpenseResource toResource(Expense expense) {
        ExpenseResource resource = new ExpenseResource();
        resource.setId(expense.getId());
        resource.setDescription(expense.getDescription());
        resource.setType(expense.getType() != null ? expense.getType().name() : null);
        resource.setAmount(expense.getAmount() != null ? expense.getAmount() : 0.0);
        resource.setDateOfExpense(expense.getDateOfExpense());
        resource.setUserId(expense.getUser() != null ? expense.getUser().getId() : null);
        return resource;
    }
}
