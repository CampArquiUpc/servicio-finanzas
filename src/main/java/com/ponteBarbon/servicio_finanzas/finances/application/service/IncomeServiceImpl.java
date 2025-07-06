package com.ponteBarbon.servicio_finanzas.finances.application.service;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates.Income;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.commands.CreateIncomeCommand;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.queries.GetIncomesByUserIdQuery;
import com.ponteBarbon.servicio_finanzas.finances.domain.service.IncomeService;
import com.ponteBarbon.servicio_finanzas.finances.infrastructure.persistance.JPA.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class IncomeServiceImpl implements IncomeService {
    private final IncomeRepository incomeRepository;

    @Autowired
    public IncomeServiceImpl(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    @Override
    public Long handle(CreateIncomeCommand command) {
        Income income = new Income();
        income.setId(command.userId());
        income.setDescription(command.description());
        income.setAmount(command.amount());
        income.setDateOfIncome(command.dateOfIncome());
        return incomeRepository.save(income).getId();
    }

    @Override
    public List<Income> handle(GetIncomesByUserIdQuery query) {
        return incomeRepository.findByUser_Id(query.userId());
    }
}

