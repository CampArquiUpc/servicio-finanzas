package com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.resource;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseResource {
    private Long id;
    private String description;
    private double amount;
    private LocalDate dateOfExpense;
    private String type;
    private Long userId;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public LocalDate getDateOfExpense() { return dateOfExpense; }
    public void setDateOfExpense(java.util.Date dateOfExpense) {
        if (dateOfExpense != null) {
            this.dateOfExpense = new java.sql.Date(dateOfExpense.getTime()).toLocalDate();
        } else {
            this.dateOfExpense = null;
        }
    }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}
