package com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.transform;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates.UserInsight;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.commands.CreateUserInsightCommand;
import com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.resource.UserInsightResource;
import org.springframework.stereotype.Component;

@Component
public class UserInsightResourceAssembler {
    public UserInsightResource toResource(UserInsight entity) {
        UserInsightResource resource = new UserInsightResource();
        resource.setId(entity.getId());
        resource.setUserId(entity.getUser() != null ? entity.getUser().getId() : null);
        resource.setInsightData(entity.getInsightData());
        return resource;
    }

    public CreateUserInsightCommand toCommand(UserInsightResource resource) {
        return new CreateUserInsightCommand(resource.getUserId(), resource.getInsightData());
    }
}
