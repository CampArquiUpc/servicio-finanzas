package com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.valueObjects.ExpenseType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa un gasto del usuario
 * Una Expense es una entidad
 */

@Entity
@Getter
@Setter
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private ExpenseType type;

    private Double amount;

    private Long idUser;

    public Expense() {}

    public Expense(String description, String type, Double amount) {
        this.description = description;
        this.type = ExpenseType.valueOf(type) ;
        this.amount = amount;
    }

}
