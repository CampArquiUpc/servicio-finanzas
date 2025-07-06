package com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.transform;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates.LoyaltyProgram;
import com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.resource.LoyaltyProgramResource;
import org.springframework.stereotype.Component;

@Component
public class LoyaltyProgramResourceAssembler {
    public LoyaltyProgramResource toResource(LoyaltyProgram entity) {
        LoyaltyProgramResource resource = new LoyaltyProgramResource();
        resource.setId(entity.getId());
        resource.setUserId(entity.getId());
        resource.setPoints(entity.getPoints());
        return resource;
    }
}

