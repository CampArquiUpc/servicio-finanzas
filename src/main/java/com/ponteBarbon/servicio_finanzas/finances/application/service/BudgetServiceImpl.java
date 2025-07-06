package com.ponteBarbon.servicio_finanzas.finances.application.service;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates.Budget;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.commands.CreateBudgetCommand;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.queries.GetBudgetByUserIdQuery;
import com.ponteBarbon.servicio_finanzas.finances.domain.service.BudgetService;
import com.ponteBarbon.servicio_finanzas.finances.infrastructure.persistance.JPA.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class BudgetServiceImpl implements BudgetService {
    private final BudgetRepository budgetRepository;

    @Autowired
    public BudgetServiceImpl(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @Override
    public Long handle(CreateBudgetCommand command) {
        Budget budget = new Budget();
        budget.setId(command.userId());
        budget.setTotalAmount(command.totalAmount());
        return budgetRepository.save(budget).getId();
    }

    @Override
    public Optional<Budget> handle(GetBudgetByUserIdQuery query) {
        return budgetRepository.findByUser_Id(query.userId());
    }
}

