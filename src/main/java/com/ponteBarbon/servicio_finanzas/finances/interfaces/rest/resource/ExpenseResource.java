package com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.resource;

import java.util.Date;

public record ExpenseResource(
        Long Id,
        Long idUser,
        String description,
        String type,
        Double amount,
        Date dateOfExpense
) {
}
