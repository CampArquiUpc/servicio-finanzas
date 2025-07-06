package com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.transform;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates.Budget;
import com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.resource.BudgetResource;
import org.springframework.stereotype.Component;

@Component
public class BudgetResourceAssembler {
    public BudgetResource toResource(Budget entity) {
        BudgetResource resource = new BudgetResource();
        resource.setId(entity.getId());
        resource.setUserId(entity.getId());
        resource.setTotalAmount(entity.getTotalAmount());
        return resource;
    }
}

