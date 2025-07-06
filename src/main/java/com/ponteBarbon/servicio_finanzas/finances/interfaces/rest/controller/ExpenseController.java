package com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.controller;


import com.ponteBarbon.servicio_finanzas.finances.domain.model.commands.CreateExpensebyAudioCommand;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.commands.DeleteExpenseCommandById;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.commands.UpdateExpenseCommandById;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.queries.GetExpenseByIdQuery;
import com.ponteBarbon.servicio_finanzas.finances.domain.service.ExpenseService;
import com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.resource.CreateExpenseResource;
import com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.resource.ExpenseResource;
import com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.resource.UpdateExpenseResource;
import com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.transform.CreateExpenseCommandFromResourceAssembler;
import com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.transform.ExpenseResourceFromEntityAssembler;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/expense", produces = APPLICATION_JSON_VALUE)
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }


    @PostMapping
    public ResponseEntity<ExpenseResource> AddedExpense(@RequestBody CreateExpenseResource resource){
        var createExpenseCommand =
                CreateExpenseCommandFromResourceAssembler.toCommandFromResource(resource);
        var expenseId = expenseService.handle(createExpenseCommand, 1L);

        if (expenseId == 0L) return ResponseEntity.notFound().build();

        var getExpenseByIdQuery = new GetExpenseByIdQuery(expenseId);
        var expense = expenseService.handle(getExpenseByIdQuery);

        var expenseResource = ExpenseResourceFromEntityAssembler
                .toResourceFromEntity(expense.get());

        return ResponseEntity.ok(expenseResource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResource> GetExpenseById(@PathVariable Long id){
        var getExpenseByIdQuery = new GetExpenseByIdQuery(id);
        var expense = expenseService.handle(getExpenseByIdQuery);

        if (expense.isEmpty()) return ResponseEntity.notFound().build();

        var expenseResource = ExpenseResourceFromEntityAssembler.toResourceFromEntity(expense.get());

        return ResponseEntity.ok(expenseResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResource> UpdateExpenseById(@PathVariable Long id, @RequestBody UpdateExpenseResource resource){
        var updateExpenseCommandById = new UpdateExpenseCommandById(
                id,
                resource.description(),
                resource.type(),
                resource.amount(),
                resource.dateOfExpense());

        var updatedExpense = expenseService.handle(updateExpenseCommandById);
        if (updatedExpense.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var expenseResource = ExpenseResourceFromEntityAssembler.toResourceFromEntity(updatedExpense.get());
        return ResponseEntity.ok(expenseResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteExpenseById(@PathVariable Long id){
        var deleteExpenseCommandById = new DeleteExpenseCommandById(id);
        expenseService.hanlde(deleteExpenseCommandById);
        return ResponseEntity.ok("Expense delete given id successfully");
    }
}
