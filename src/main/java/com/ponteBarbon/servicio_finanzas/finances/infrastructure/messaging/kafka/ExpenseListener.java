package com.ponteBarbon.servicio_finanzas.finances.infrastructure.messaging.kafka;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates.Expense;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.events.ExpenseEvent;
import com.ponteBarbon.servicio_finanzas.finances.domain.service.ExpenseService;
import com.ponteBarbon.servicio_finanzas.finances.infrastructure.messaging.transform.JsonFromEntityAssembler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ExpenseListener {
    private final ExpenseService expenseService;

    public ExpenseListener(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @KafkaListener(topics = "expense-topic")
    public void handleCreateExpense(String message) {
        System.out.println("\u001B[32m"+ message + "\u001B[0m");
        var response = JsonFromEntityAssembler.fromJson(message, ExpenseEvent.class);
        System.out.println("\u001B[32m"+ response + "\u001B[0m");
    }


}
