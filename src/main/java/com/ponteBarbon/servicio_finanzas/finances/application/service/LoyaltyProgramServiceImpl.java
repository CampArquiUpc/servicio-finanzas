package com.ponteBarbon.servicio_finanzas.finances.application.service;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates.LoyaltyProgram;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.commands.CreateLoyaltyProgramCommand;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.queries.GetLoyaltyProgramByUserIdQuery;
import com.ponteBarbon.servicio_finanzas.finances.domain.service.LoyaltyProgramService;
import com.ponteBarbon.servicio_finanzas.finances.infrastructure.persistance.JPA.LoyaltyProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class LoyaltyProgramServiceImpl implements LoyaltyProgramService {
    private final LoyaltyProgramRepository loyaltyProgramRepository;

    @Autowired
    public LoyaltyProgramServiceImpl(LoyaltyProgramRepository loyaltyProgramRepository) {
        this.loyaltyProgramRepository = loyaltyProgramRepository;
    }

    @Override
    public Long handle(CreateLoyaltyProgramCommand command) {
        LoyaltyProgram program = new LoyaltyProgram();
        program.setId(command.userId());
        program.setPoints(command.initialPoints());
        return loyaltyProgramRepository.save(program).getId();
    }

    @Override
    public Optional<LoyaltyProgram> handle(GetLoyaltyProgramByUserIdQuery query) {
        return loyaltyProgramRepository.findByUser_Id(query.userId());
    }
}

