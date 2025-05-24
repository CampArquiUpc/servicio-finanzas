package com.ponteBarbon.servicio_finanzas.finances.domain.model.commands;

public record CreateExpenseCommand(String description, String type, Double amount) {
}
