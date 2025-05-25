package com.ponteBarbon.servicio_finanzas.finances.application;


import com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates.Expense;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.commands.CreateExpenseCommand;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.queries.GetExpenseByIdQuery;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.queries.GetExpenseByIdUserQuery;
import com.ponteBarbon.servicio_finanzas.finances.domain.service.ExpenseService;
import com.ponteBarbon.servicio_finanzas.finances.infrastructure.persistance.JPA.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }


    @Override
    public List<Expense> handle(GetExpenseByIdUserQuery query) {
        return expenseRepository.findAll();
    }

    @Override
    public Long handle(CreateExpenseCommand command) {




        try{
            Expense expense = new Expense(
                    command.description(),
                    command.type(),
                    command.amount(),
                    command.dateOfExpense());
            expenseRepository.save(expense);
            return expense.getId();
        } catch(Exception e){
            throw new IllegalArgumentException("Error while saving expense" + e.getMessage());
        }




    }

    @Override
    public Optional<Expense> handle(GetExpenseByIdQuery query) {
        return expenseRepository.getExpenseById(query.id());
    }
}
