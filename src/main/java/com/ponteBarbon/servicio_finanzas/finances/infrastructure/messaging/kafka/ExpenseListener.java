package com.ponteBarbon.servicio_finanzas.finances.infrastructure.messaging.kafka;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates.Expense;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.events.ExpenseEvent;
import com.ponteBarbon.servicio_finanzas.finances.domain.service.ExpenseService;
import com.ponteBarbon.servicio_finanzas.finances.domain.service.EventService;
import com.ponteBarbon.servicio_finanzas.finances.infrastructure.messaging.transform.JsonFromEntityAssembler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ExpenseListener {

    private final EventService eventService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ExpenseListener( EventService eventService) {

        this.eventService = eventService;
    }

    @KafkaListener(topics = "expense-topic")
    public void handleCreateExpense(String message) {
        System.out.println("\u001B[34m"+ message + "\u001B[0m");
        try {
            ExpenseEvent event = objectMapper.readValue(message, ExpenseEvent.class);
            // Imprimir los datos del evento en rojo
            System.out.println("\u001B[31mAction: " + event.getAction() + "\u001B[0m");
            System.out.println("\u001B[31mDescription: " + event.getDescription() + "\u001B[0m");
            System.out.println("\u001B[31mAmount: " + event.getAmount() + "\u001B[0m");
            System.out.println("\u001B[31mPaymentMethod: " + event.getPaymentMethod() + "\u001B[0m");
            System.out.println("\u001B[31mId: " + event.getId() + "\u001B[0m");
            System.out.println("\u001B[31mIdUser: " + event.getIdUser() + "\u001B[0m");
            eventService.handle(event);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
