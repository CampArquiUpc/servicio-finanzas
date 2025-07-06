package com.ponteBarbon.servicio_finanzas.finances.domain.model.commands;

public record CreateBudgetCommand(Long userId, Double totalAmount) {}

