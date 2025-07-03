package com.ponteBarbon.servicio_finanzas.finances.domain.model.commands;

import java.util.Date;

public record UpdateExpenseCommandById(
        Long id,
        String description,
        String type,
        Double amount,
        Date dateOfExpense

) {
}
