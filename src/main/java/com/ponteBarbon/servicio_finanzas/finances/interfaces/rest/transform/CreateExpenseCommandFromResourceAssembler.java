package com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.transform;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.commands.CreateExpenseCommand;
import com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.resource.CreateExpenseResource;

public class CreateExpenseCommandFromResourceAssembler {
    public static CreateExpenseCommand toCommandFromResource(CreateExpenseResource resource){
        return new CreateExpenseCommand(
                resource.description(),
                resource.type(),
                resource.amount(),
                resource.dateOfExpense()
        );
    }
}
