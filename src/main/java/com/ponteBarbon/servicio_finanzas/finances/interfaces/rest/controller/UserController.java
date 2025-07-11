package com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.controller;

import com.ponteBarbon.servicio_finanzas.finances.application.ExpenseService.ExpenseServiceImpl;
import com.ponteBarbon.servicio_finanzas.finances.application.service.*;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates.User;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.commands.CreateUserCommand;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.queries.*;
import com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.resource.*;
import com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.transform.*;
import com.ponteBarbon.servicio_finanzas.finances.domain.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final IncomeServiceImpl incomeService;
    private final FinancialGoalServiceImpl goalService;
    private final BudgetServiceImpl budgetService;
    private final LoyaltyProgramServiceImpl loyaltyService;
    private final UserInsightServiceImpl insightService;
    private final ExpenseServiceImpl expenseService;
    private final IncomeResourceAssembler incomeAssembler;
    private final FinancialGoalResourceAssembler goalAssembler;
    private final BudgetResourceAssembler budgetAssembler;
    private final LoyaltyProgramResourceAssembler loyaltyAssembler;
    private final UserInsightResourceAssembler insightAssembler;
    private final UserService userService;

    @Autowired
    public UserController(
            IncomeServiceImpl incomeService,
            FinancialGoalServiceImpl goalService,
            BudgetServiceImpl budgetService,
            LoyaltyProgramServiceImpl loyaltyService,
            UserInsightServiceImpl insightService,
            ExpenseServiceImpl expenseService,
            IncomeResourceAssembler incomeAssembler,
            FinancialGoalResourceAssembler goalAssembler,
            BudgetResourceAssembler budgetAssembler,
            LoyaltyProgramResourceAssembler loyaltyAssembler,
            UserInsightResourceAssembler insightAssembler,
            UserService userService) {
        this.incomeService = incomeService;
        this.goalService = goalService;
        this.budgetService = budgetService;
        this.loyaltyService = loyaltyService;
        this.insightService = insightService;
        this.expenseService = expenseService;
        this.incomeAssembler = incomeAssembler;
        this.goalAssembler = goalAssembler;
        this.budgetAssembler = budgetAssembler;
        this.loyaltyAssembler = loyaltyAssembler;
        this.insightAssembler = insightAssembler;
        this.userService = userService;
    }

    @GetMapping("/{userId}/incomes")
    public ResponseEntity<List<IncomeResource>> getIncomes(@PathVariable Long userId) {
        var incomes = incomeService.handle(new GetIncomesByUserIdQuery(userId));
        var resources = incomes.stream().map(incomeAssembler::toResource).collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{userId}/goals")
    public ResponseEntity<List<FinancialGoalResource>> getGoals(@PathVariable Long userId) {
        var goals = goalService.handle(new GetFinancialGoalsByUserIdQuery(userId));
        var resources = goals.stream().map(goalAssembler::toResource).collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{userId}/budget")
    public ResponseEntity<BudgetResource> getBudget(@PathVariable Long userId) {
        return budgetService.handle(new GetBudgetByUserIdQuery(userId))
                .map(budgetAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{userId}/loyalty-program")
    public ResponseEntity<LoyaltyProgramResource> getLoyaltyProgram(@PathVariable Long userId) {
        return loyaltyService.handle(new GetLoyaltyProgramByUserIdQuery(userId))
                .map(loyaltyAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{userId}/insights")
    public ResponseEntity<UserInsightResource> getUserInsight(@PathVariable Long userId) {
        return insightService.handle(new GetUserInsightByUserIdQuery(userId))
                .map(insightAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registro de usuario (sign up)", description = "Registra un nuevo usuario con name y email.")
    @ApiResponse(responseCode = "201", description = "Usuario registrado exitosamente")
    @PostMapping("/signup")
    public ResponseEntity<Long> signUp(@RequestBody SignUpResource resource) {
        // Solo se usa name y email, el id es autogenerado
        Long id = userService.handle(new CreateUserCommand(resource.getName(), resource.getEmail()));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @Operation(summary = "Inicio de sesión (sign in)", description = "Inicia sesión con name y email. Si existe, retorna el usuario.")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    @PostMapping("/signin")
    public ResponseEntity<UserResource> signIn(@RequestBody SignUpResource resource) {
        // Búsqueda simple por email y name
        var userOpt = userService.findByEmail(resource.getEmail());
        if (userOpt.isPresent() && userOpt.get().getName().equals(resource.getName())) {
            User user = userOpt.get();
            UserResource res = new UserResource();
            res.setId(user.getId());
            res.setName(user.getName());
            res.setEmail(user.getEmail());
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Operation(summary = "Registrar ingreso", description = "Registra un ingreso para un usuario.")
    @ApiResponse(responseCode = "201", description = "Ingreso registrado exitosamente")
    @PostMapping("/{userId}/incomes")
    public ResponseEntity<Long> addIncome(@PathVariable Long userId, @RequestBody IncomeResource resource) {
        var command = new com.ponteBarbon.servicio_finanzas.finances.domain.model.commands.CreateIncomeCommand(
                userId,
                resource.getDescription(),
                resource.getAmount(),
                java.sql.Date.valueOf(resource.getDateOfIncome())
        );
        Long id = incomeService.handle(command);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @Operation(summary = "Registrar gasto (expense)", description = "Registra un gasto para un usuario.")
    @ApiResponse(responseCode = "201", description = "Gasto registrado exitosamente")
    @PostMapping("/{userId}/expenses")
    public ResponseEntity<Long> addExpense(@PathVariable Long userId, @RequestBody CreateExpenseResource resource) {
        var command = CreateExpenseCommandFromResourceAssembler.toCommandFromResource(resource);
        Long id = expenseService.handle(command, userId);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener gastos por usuario", description = "Devuelve la lista de gastos (expenses) para el usuario indicado.")
    @ApiResponse(responseCode = "200", description = "Lista de gastos obtenida exitosamente")
    @GetMapping("/{userId}/expenses")
    public ResponseEntity<List<ExpenseResource>> getExpenses(@PathVariable Long userId) {
        var expenses = expenseService.handle(new GetExpensesByUserIdQuery(userId));
        var resources = expenses.stream().map(ExpenseResourceAssembler::toResource).collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }
}
