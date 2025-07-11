package com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.transform;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.commands.CreateExpenseCommand;
import com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.resource.CreateExpenseResource;

public class CreateExpenseCommandFromResourceAssembler {
    public static CreateExpenseCommand toCommandFromResource(CreateExpenseResource resource) {
        String type = resource.type();
        // Si el tipo es un número, lo mapeamos al nombre del enum
        switch(type) {
            case "1": type = "YAPE"; break;
            case "2": type = "INTERBANK"; break;
            case "3": type = "BCP"; break;
            case "4": type = "PLIN"; break;
            case "5": type = "CAJA"; break;
            case "6": type = "BBVA"; break;
            case "7": type = "SCOTIABANK"; break;
            case "8": type = "OTROS"; break;
        }
        return new CreateExpenseCommand(
            resource.description(),
            type,
            resource.amount(),
            new java.sql.Date(resource.dateOfExpense().getTime())
        );
    }
}
