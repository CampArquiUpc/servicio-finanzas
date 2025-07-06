package com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.transform;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates.Income;
import com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.resource.IncomeResource;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;

@Component
public class IncomeResourceAssembler {
    public IncomeResource toResource(Income entity) {
        IncomeResource resource = new IncomeResource();
        resource.setId(entity.getId());
        resource.setUserId(entity.getId());
        resource.setDescription(entity.getDescription());
        resource.setAmount(entity.getAmount());
        if (entity.getDateOfIncome() != null) {
            resource.setDateOfIncome(new SimpleDateFormat("yyyy-MM-dd").format(entity.getDateOfIncome()));
        }
        return resource;
    }
}

