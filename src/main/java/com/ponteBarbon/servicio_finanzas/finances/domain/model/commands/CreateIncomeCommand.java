package com.ponteBarbon.servicio_finanzas.finances.domain.model.commands;

public record CreateIncomeCommand(Long userId, String description, Double amount, java.util.Date dateOfIncome) {}

