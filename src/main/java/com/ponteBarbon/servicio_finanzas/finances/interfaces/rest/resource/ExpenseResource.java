package com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.resource;

import java.util.Date;

public record ExpenseResource(
    Long id,
    String description,
    String type,
    Double amount,
    Date dateOfExpense,
    Long idUser
) {}

