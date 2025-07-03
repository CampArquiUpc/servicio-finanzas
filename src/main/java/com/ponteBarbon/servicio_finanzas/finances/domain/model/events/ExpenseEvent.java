package com.ponteBarbon.servicio_finanzas.finances.domain.model.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.valueObjects.Action;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpenseEvent {
    private Action action;
    private String description;
    private Double amount;
    private String paymentMethod;
    private Long id;
    private Long idUser;

    public ExpenseEvent(){}

    @JsonCreator
    public ExpenseEvent(
            @JsonProperty("action") String action,
            @JsonProperty("description") String description,
            @JsonProperty("amount") Double amount,
            @JsonProperty("payment_method") String paymentMethod,
            @JsonProperty("id") Long id,
            @JsonProperty("idUser") Long idUser) {

        this.action = Action.valueOf(action);
        this.description = description;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.id = id;
        this.idUser = idUser;
    }
}
