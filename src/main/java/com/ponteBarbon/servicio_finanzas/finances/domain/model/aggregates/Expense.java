package com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.valueObjects.ExpenseType;
import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Expense() {}


    public Expense(String description, String type, Double amount, Date dateOfExpense, User user) {
        this.description = description;
        this.type = ExpenseType.valueOf(type) ;
        this.amount = amount;
        this.dateOfExpense = dateOfExpense;
        this.user = user;
    }

}
