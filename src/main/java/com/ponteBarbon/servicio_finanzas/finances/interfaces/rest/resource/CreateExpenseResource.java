package com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.resource;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public record CreateExpenseResource(
        String description,
        String type,
        Double amount,
        @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfExpense) {
}
