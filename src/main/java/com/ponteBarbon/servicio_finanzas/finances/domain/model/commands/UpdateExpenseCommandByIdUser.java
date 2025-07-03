package com.ponteBarbon.servicio_finanzas.finances.domain.model.commands;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.valueObjects.ExpenseType;

import java.util.Date;

public record UpdateExpenseCommandByIdUser(
        String description,
        String type,
        Double amount,
        Date dateOfExpense,
        Long idUser
) {
}
