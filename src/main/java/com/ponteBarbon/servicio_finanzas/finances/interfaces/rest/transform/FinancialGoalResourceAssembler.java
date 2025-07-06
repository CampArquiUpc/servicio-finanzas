package com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.transform;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates.FinancialGoal;
import com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.resource.FinancialGoalResource;
import org.springframework.stereotype.Component;

@Component
public class FinancialGoalResourceAssembler {
    public FinancialGoalResource toResource(FinancialGoal entity) {
        FinancialGoalResource resource = new FinancialGoalResource();
        resource.setId(entity.getId());
        resource.setUserId(entity.getId());
        resource.setName(entity.getName());
        resource.setTargetAmount(entity.getTargetAmount());
        resource.setCurrentAmount(entity.getCurrentAmount());
        return resource;
    }
}

