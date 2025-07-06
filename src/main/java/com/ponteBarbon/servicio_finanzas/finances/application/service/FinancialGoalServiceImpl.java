package com.ponteBarbon.servicio_finanzas.finances.application.service;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates.FinancialGoal;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.commands.CreateFinancialGoalCommand;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.queries.GetFinancialGoalsByUserIdQuery;
import com.ponteBarbon.servicio_finanzas.finances.domain.service.FinancialGoalService;
import com.ponteBarbon.servicio_finanzas.finances.infrastructure.persistance.JPA.FinancialGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FinancialGoalServiceImpl implements FinancialGoalService {
    private final FinancialGoalRepository financialGoalRepository;

    @Autowired
    public FinancialGoalServiceImpl(FinancialGoalRepository financialGoalRepository) {
        this.financialGoalRepository = financialGoalRepository;
    }

    @Override
    public Long handle(CreateFinancialGoalCommand command) {
        FinancialGoal goal = new FinancialGoal();
        goal.setId(command.userId());
        goal.setName(command.name());
        goal.setTargetAmount(command.targetAmount());
        goal.setCurrentAmount(0.0);
        return financialGoalRepository.save(goal).getId();
    }

    @Override
    public List<FinancialGoal> handle(GetFinancialGoalsByUserIdQuery query) {
        return financialGoalRepository.findByUser_Id(query.userId());
    }
}

