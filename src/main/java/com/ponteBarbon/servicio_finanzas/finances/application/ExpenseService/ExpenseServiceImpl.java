package com.ponteBarbon.servicio_finanzas.finances.application.ExpenseService;



import com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates.Expense;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.commands.*;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.queries.GetExpenseByIdQuery;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.queries.GetExpenseByIdUserQuery;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.valueObjects.ExpenseType;
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
    // Verificamos si el usuario existe
        return expenseRepository.getExpenseByIdUser(query.idUser());
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

    @Override
    public void hanlde(DeleteExpenseCommandById command) {
        if(!expenseRepository.existsById(command.idExpense())) {
            throw new IllegalArgumentException("Expense with id " + command.idExpense() + " does not exist.");
        }
        expenseRepository.deleteById(command.idExpense());
    }

    @Override
    public Optional<Expense> handle(UpdateExpenseCommandById command) {
        if(!expenseRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Expense with id " + command.id() + " does not exist.");
        }
        //Nueva instancia de Expense con los datos actualizados
        var expense = expenseRepository.getExpenseById(command.id()).get();

        expense.setDescription(command.description());
        expense.setType(ExpenseType.valueOf(command.type()));
        expense.setAmount(command.amount());
        expense.setDateOfExpense(command.dateOfExpense());

        var result = expenseRepository.save(expense);


        return Optional.of(result);
    }




}
