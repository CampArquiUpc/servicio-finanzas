package com.ponteBarbon.servicio_finanzas.finances.application.EventService;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.commands.CreateExpenseCommand;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.commands.UpdateExpenseCommandById;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.commands.DeleteExpenseCommandById;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.events.ExpenseEvent;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.queries.GetExpenseByIdQuery;
import com.ponteBarbon.servicio_finanzas.finances.domain.service.EventService;
import com.ponteBarbon.servicio_finanzas.finances.domain.service.ExpenseService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class EventServiceImpl implements EventService {
    private final ExpenseService expenseService;

    public EventServiceImpl(ExpenseService expenseService) {

        this.expenseService = expenseService;
    }

    @Override
    public void handle(ExpenseEvent event) {
        switch (event.getAction()) {
            case ADD -> {
                CreateExpenseCommand command = new CreateExpenseCommand(
                    event.getDescription(),
                    event.getPaymentMethod(), // O ajusta según corresponda el campo type
                    event.getAmount(),
                    new Date() // O ajusta si tienes la fecha en el evento
                );
                expenseService.handle(command, 1L); //TODO: Default userId, ajustar según tu lógica de negocio
            }
            case UPDATE -> {
                // Obtenemos el gasto a actualizar para comparar y actualizar
                var getExpenseByIdQuery = new GetExpenseByIdQuery(event.getId());
                var expense = expenseService.handle(getExpenseByIdQuery).get();

                // Solo actualizamos los campos no vacíos o no nulos
                var description = (event.getDescription() != null && !event.getDescription().isEmpty()) ? event.getDescription() : expense.getDescription();
                var amount = (event.getAmount() != 0) ? event.getAmount() : expense.getAmount();
                var paymentMethod = (event.getPaymentMethod() != null && !event.getPaymentMethod().isEmpty()) ? event.getPaymentMethod() : expense.getType().toString();

                var updateCommand = new UpdateExpenseCommandById(
                        event.getId(),
                        description,
                        paymentMethod,
                        amount,
                        new Date()
                );
                expenseService.handle(updateCommand);
            }
            case REMOVE -> {
                // Se asume que el evento tiene getId()
                var deleteCommand = new DeleteExpenseCommandById(event.getId());
                expenseService.hanlde(deleteCommand);
            }
        }
    }
}
