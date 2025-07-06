package com.ponteBarbon.servicio_finanzas.finances.domain.model.commands;

public record CreateFinancialGoalCommand(Long userId, String name, Double targetAmount) {}

