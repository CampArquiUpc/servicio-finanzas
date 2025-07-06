package com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.resource;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IncomeResource {
    private Long id;
    private Long userId;
    private String description;
    private Double amount;
    private String dateOfIncome;

}

