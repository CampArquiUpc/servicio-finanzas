package com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.valueObjects.ExpenseType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Representa un gasto del usuario
 * Una Expense es una entidad
 */

@Getter
@Entity

public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String description;
    @Setter
    private ExpenseType type;
    @Setter
    private Double amount;
    @Setter
    private Date dateOfExpense;

    private Long idUser;

    public Expense() {}


    public Expense(String description, String type, Double amount, Date dateOfExpense) {
        this.description = description;
        this.type = ExpenseType.valueOf(type) ;
        this.amount = amount;
        this.dateOfExpense = dateOfExpense;
        this.idUser = 0L;
    }

}
