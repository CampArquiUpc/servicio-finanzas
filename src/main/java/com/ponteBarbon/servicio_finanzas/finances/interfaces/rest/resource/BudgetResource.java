package com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.resource;

public class BudgetResource {
    private Long id;
    private Long userId;
    private Double totalAmount;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
}

