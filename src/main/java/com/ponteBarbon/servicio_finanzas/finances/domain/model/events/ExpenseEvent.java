package com.ponteBarbon.servicio_finanzas.finances.domain.model.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.valueObjects.Action;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.valueObjects.ExpenseType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExpenseEvent {
    private String action;
    private String description;
    private Double amount;
    private String paymentMethod;

    public ExpenseEvent(){}

    @JsonCreator
    public ExpenseEvent(
            @JsonProperty("action") String action,
            @JsonProperty("description") String description,
            @JsonProperty("amount") Double amount,
            @JsonProperty("payment_method") String paymentMethod) {

        this.action = action;
        this.description = description;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }
}
